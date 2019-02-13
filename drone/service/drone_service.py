# coding=utf-8
import dronekit_sitl

# Import DroneKit-Python
from dronekit import connect, LocationGlobal, Command
from pymavlink import mavutil

sitl = dronekit_sitl.start_default()
connection_string = sitl.connection_string()


class DroneService:
    """
    Gestionnaire du drone
    """

    def __init__(self):
        print("Connexion au drone")
        self.vehicle = connect(connection_string, wait_ready=True)
        pass

    def add_mission(self, latitude, longitude, altitude, cancel_previous_missions=False):
        """
        Ajoute une mission à
        :param altitude:
        :param latitude:
        :param longitude:
        :param cancel_previous_missions:
        :return:
        """
        cmds = self.vehicle.commands
        cmd = Command(0, 0, 0, mavutil.mavlink.MAV_FRAME_GLOBAL_RELATIVE_ALT, mavutil.mavlink.MAV_CMD_NAV_WAYPOINT,
                      0, 0, 0, 0, 0, 0,
                      latitude, longitude, altitude)
        cmds.add(cmd)
        cmds.upload()

    def start_missions(self):
        """
        Démarre le drone et le fait executer les missions préalablement chargé
        :return:
        """

    def return_to_base(self):
        """
        Stop l'execution et retourne à la base
        :return:
        """

    def get_position(self):
        """

        :return:
        """
