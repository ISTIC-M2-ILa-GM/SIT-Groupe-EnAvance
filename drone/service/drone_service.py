# coding=utf-8
import dronekit_sitl

# Import DroneKit-Python
from dronekit import connect

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

    def add_mission(self, latitude, longitude):
        """
        Ajoute une mission à
        :param latitude:
        :param longitude:
        :return:
        """

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
