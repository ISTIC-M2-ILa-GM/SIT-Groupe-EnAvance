# coding=utf-8
import math
import time
import os
import subprocess
import shlex

from dto.dto import PointDeBase, ResultDTO
from scripts.google_earth_fly_link_file import write_coord_xml
from scripts.screenshot import take_screenshot
from service.drone_service import DroneService
from service.retreive_last_mission import get_last_mission, send_photo


def dist(latd1, longd1, latd2, longd2):
    """
    Calcul la distance entre deux positions gps en metre
    """
    lat1 = math.radians(latd1)
    lat2 = math.radians(latd2)
    long1 = math.radians(longd1)
    long2 = math.radians(longd2)

    radius = 6371009

    delta_lat = lat1-lat2
    delta_long = long1-long2
    cos_lat_moy = math.cos((lat1+lat2)/2)

    to_return = radius*math.pow(math.pow(delta_lat, 2) + math.pow(cos_lat_moy*delta_long, 2), 0.5)

    return to_return


def retrieving_mission():
    """
    Boucle d'attente des données d'une mission
    """
    temp_mission_found = False
    while not temp_mission_found:

        temp_mission = {
            "id": 2,
            "points": [
                {"index": 0, "x": 48.121045, "y": -1.6451528, "z": 300},
                {"index": 1, "x": 48.121545, "y": -1.6451528, "z": 300},
                {"index": 2, "x": 48.122045, "y": -1.6451528, "z": 300}
            ]
        }
        #get_last_mission()
        """
            {
            "id": 2,
            "points": [
                {"index": 0, "x": 48.121045, "y": -1.6451528, "z": 300},
                {"index": 1, "x": 48.121545, "y": -1.6451528, "z": 300},
                {"index": 2, "x": 48.122045, "y": -1.6451528, "z": 300}
            ]
        } 
        """
        if temp_mission is not None:
            temp_mission_found = True

        else:
            time.sleep(5)

    return temp_mission


def give_orders(miss, dro):
    """
    Envoi les  parapètres de mission au drone
    """
    first = True

    for a in miss["points"]:
        print a["x"], a["y"], a["z"], first
        dro.add_point(a["x"], a["y"], a["z"], first)
        first = False
    print "give orders0"
    dro.start_missions()
    print "give orders1"


drone_monitor = None


def monitoring_mission(miss, dr):
    """
    Ecoute les status du drone et trensmet les données jusqu'en fin mission
    """
    print "monitor mission0"
    global drone_monitor
    drone_monitor = {"end_mission_lat": miss["points"][-1]["x"],
                     "end_mission_lon": miss["points"][-1]["y"],
                     "current_lat": None,
                     "current_lon": None,
                     "current_alt": None,
                     "a_change_position": False,
                     "a_atteint_point_final": False
                     }

    mission_is_over = False
    print "monitor mission1"
    dr.register_position_callback(react_to_position)

    while not mission_is_over:
        print "check state"

        if drone_monitor["a_change_position"]:
            print "changed position"
            # notifier server et aquisition
            # TODO implement image transmition
            write_coord_xml(drone_monitor["current_lat"], drone_monitor["current_lon"], drone_monitor["current_alt"])
            path_image = take_screenshot()
            point = PointDeBase(drone_monitor["current_lat"], drone_monitor["current_lon"], drone_monitor["current_alt"])
            result_dto = ResultDTO(path_image, point.__dict__)
            send_photo(miss["id"], result_dto.__dict__)
            drone_monitor["a_change_position"] = False

        elif drone_monitor["a_atteint_point_final"]:
            print "reached end mission"
            # donne ordre de retourner se poser
            dr.return_to_base()
            mission_is_over = True

        time.sleep(5)


def react_to_position(self, attr_name, msg):
    """
    callback change drone_monitor according to position lisner
    """
    global drone_monitor
    print "callback: ", msg.lat, " ", msg.lon, " ", msg.alt

    if 5 > dist(drone_monitor["end_mission_lat"], drone_monitor["end_mission_lon"], msg.lat, msg.lon):
        drone_monitor["a_atteint_point_final"] = True

    if drone_monitor["current_lat"] is None:
        drone_monitor["current_lat"] = msg.lat
        drone_monitor["current_lon"] = msg.lon
        drone_monitor["current_alt"] = msg.alt

    if 5 < dist(drone_monitor["current_lat"], drone_monitor["current_lon"], msg.lat, msg.lon):
        print "dist ok: ", dist(drone_monitor["current_lat"], drone_monitor["current_lon"], msg.lat, msg.lon)
        drone_monitor["a_change_position"] = True
        drone_monitor["current_lat"] = msg.lat
        drone_monitor["current_lon"] = msg.lon
        drone_monitor["current_alt"] = msg.alt
    else:
        print "dist ko: ", dist(drone_monitor["current_lat"], drone_monitor["current_lon"], msg.lat, msg.lon)


if __name__ == '__main__':
    '''
    Script principal de récupération de mission, avant transmition au drône puis attente de la fn de la mission avant retour à l'attente de mission
    '''

    filename = os.path.join(os.getcwd(), "/tmp/camera_link_file.kml")
    command = "google-earth-pro " + filename
    print command
    args = shlex.split(command)
    p = subprocess.Popen(args)
    # sleep 5 seconds pour le lancement de google earth
    time.sleep(5)

    execute = True

    drone = DroneService(48.115616, -1.638298)

    while execute:
        mission = retrieving_mission()
        print "retriv-give"
        give_orders(mission, drone)
        print "give-monit"
        monitoring_mission(mission, drone)

        execute = False

    print "Fin"
