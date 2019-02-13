from service.drone_service import DroneService


# Callback to print the location in global frame
def location_callback(self, attr_name, msg):
    print("Location (Global): ")
    print("lat : ", msg.lat)
    print("lon : ", msg.lon)
    print("altitude : ", msg.alt)


droneService = DroneService()

droneService.register_position_callback(location_callback)

droneService.add_point(37.0, 1.337, 23)

droneService.add_point(37.1, 1.336, 23)

droneService.start_missions()
