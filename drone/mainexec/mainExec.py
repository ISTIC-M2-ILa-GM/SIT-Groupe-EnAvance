# coding=utf-8
import math
import time

from service.drone_service import DroneService
from service.retreive_last_mission import get_last_mission


def dist(lat1, long1, lat2, long2):
    """
    Calcul la distance entre deux positions gps en metre
    """
    delta_lat = lat1-lat2
    delta_long = long1-long2
    cos_lat_moy = math.cos((lat1+lat2)/2)

    toReturn = 6366346*math.pow(math.pow(delta_lat, 2) + math.pow(cos_lat_moy*delta_long), 0.5)
    return toReturn


def retrieving_mission():
    """
    Boucle d'attente des données d'une mission
    """
    temp_mission_found = False
    while not temp_mission_found:

        temp_mission = {
            "id": 2,
            "points": [
                {"index": 0, "x": 1.001, "y": 2.003, "z": 22},
                {"index": 1, "x": 1.001, "y": 2.003, "z": 22},
                {"index": 2, "x": 1.001, "y": 2.003, "z": 22}
            ]
        }  # get_last_mission()
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

    dro.start_missions()


drone_monitor = None


def monitoring_mission(miss, dr):
    """
    Ecoute les status du drone et trensmet les données jusqu'en fin mission
    """
    global drone_monitor
    drone_monitor = {"end_mission_lat": miss["points"][-1]["x"],
                     "end_mission_lon": miss["points"][-1]["y"],
                     "current_lat": None,
                     "current_lon": None,
                     "a_change_position": False,
                     "a_atteint_point_final": False
                     }

    mission_is_over = False

    dr.register_position_callback(react_to_position)

    while not mission_is_over:

        if drone_monitor["a_change_position"]:
            # notifier server et aquisition
            # TODO implement image transmition
            drone_monitor["a_change_position"] = False
        elif drone_monitor["a_atteint_point_final"]:
            # donne ordre de retourner se poser
            dr.return_to_base()
            mission_is_over = True

        time.sleep(5)


def react_to_position(self, attr_name, msg):
    """
    callback change drone_monitor according to position lisner
    """
    global drone_monitor

    if 5 > dist(drone_monitor["end_mission_lat"], drone_monitor["end_mission_lon"], msg.lat, msg.lon):
        drone_monitor["a_atteint_point_final"] = True

    if drone_monitor["current_lat"] is None:
        drone_monitor["current_lat"] = msg.lat
        drone_monitor["current_lon"] = msg.lon

    if 5 > dist(drone_monitor["current_lat"], drone_monitor["current_lon"], msg.lat, msg.lon):
        drone_monitor["a_change_position"] = True


if __name__ == '__main__':
    '''
    Script principal de récupération de mission, avant transmition au drône puis attente de la fn de la mission avant retour à l'attente de mission
    '''
    execute = True

    drone = DroneService()

    while execute:
        mission = retrieving_mission()

        give_orders(mission, drone)

        monitoring_mission(mission, drone)

        execute = False

    print "Fin"
