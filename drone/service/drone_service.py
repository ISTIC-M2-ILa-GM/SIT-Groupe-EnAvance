# coding=utf-8
import time

import dronekit_sitl
# Import DroneKit-Python
from dronekit import connect, Command, VehicleMode
from pymavlink import mavutil


class DroneService:
    """
    Gestionnaire du drone
    """

    def __init__(self, latitude_depart, longitude_depart):
        print("Connexion au drone")
        sitl = dronekit_sitl.start_default(latitude_depart, longitude_depart)
        connection_string = sitl.connection_string()

        self.vehicle = connect(connection_string, wait_ready=True)
        self.positions = []
        self.callback = None
        self.vehicle.airspeed = 90
        pass

    def add_point(self, latitude, longitude, altitude, cancel_previous_missions=False):
        """
        Ajoute un autre point à atteindre
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

        # on annule les missions précédentes
        if cancel_previous_missions:
            cmds.clear()

        cmds.add(cmd)
        cmds.upload()
        self.positions.append((latitude, longitude, altitude))

    def start_missions(self):
        """
        Démarre le drone et le fait executer les missions préalablement chargé
        :return:
        """
        print("Début de la mission")
        self.__arm_and_takeoff(5)
        self.vehicle.commands.next = 0

        # Set mode to AUTO to start mission
        self.vehicle.mode = VehicleMode("AUTO")

        while True:
            nextwaypoint = self.vehicle.commands.next
            time.sleep(100)

    def register_position_callback(self, callback):
        """
        Enregistre un callback qui est appelé à chaque changement de position du drone
        :param callback: function de type -> toto(self, attr_name, msg)
        :return: nada
        """
        self.vehicle.add_attribute_listener('location.global_frame', callback)
        # self.callback = callback
        # self.vehicle.add_attribute_listener('location.local_frame', self.__callback_wrapper)

    def __callback_wrapper(self, attr_name, msg, fourth_arg):
        print("callback_wrapper")
        msg.heading = self.vehicle.heading
        msg.tilt = self.vehicle.attitude

        if self.callback is not None:
            self.callback(attr_name, msg)

    def heading(self):
        """
        Fournit la direction du drone
        :return:
        """
        return self.vehicle.heading

    def tilt(self):
        """
        Fournit l'inclinaison du drone
        :return:
        """
        return self.vehicle.attitude

    def return_to_base(self):
        """
        Stop l'execution et retourne à la base
        :return:
        """

    def get_position(self):
        """

        :return:
        """

    def __arm_and_takeoff(self, aTargetAltitude):
        """
        Arms vehicle and fly to aTargetAltitude.
        """

        print("Basic pre-arm checks")
        # Don't let the user try to arm until autopilot is ready
        while not self.vehicle.is_armable:
            print(" Waiting for vehicle to initialise...")
            time.sleep(1)

        print("Arming motors")
        # Copter should arm in GUIDED mode
        self.vehicle.mode = VehicleMode("GUIDED")
        self.vehicle.armed = True

        while not self.vehicle.armed:
            print(" Waiting for arming...")
            time.sleep(1)

        print("Taking off!")
        self.vehicle.simple_takeoff(aTargetAltitude)  # Take off to target altitude

        # Wait until the vehicle reaches a safe height before processing the goto (otherwise the command
        #  after Vehicle.simple_takeoff will execute immediately).
        while True:
            print(" Altitude: ", self.vehicle.location.global_relative_frame.alt)
            if self.vehicle.location.global_relative_frame.alt >= aTargetAltitude * 0.95:
                print("Reached target altitude")
                break
            time.sleep(1)
