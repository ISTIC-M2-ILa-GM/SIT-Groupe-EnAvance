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
        self.vehicle = connect(connection_string, wait_ready=True)
        pass
