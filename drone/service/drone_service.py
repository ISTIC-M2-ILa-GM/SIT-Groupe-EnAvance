# coding=utf-8
import time

import dronekit_sitl
# Import DroneKit-Python
from dronekit import connect, Command, VehicleMode
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
