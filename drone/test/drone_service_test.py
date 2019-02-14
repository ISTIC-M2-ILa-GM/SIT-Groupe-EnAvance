from service.drone_service import DroneService


# Callback to print the location in global frame
def location_callback(self, attr_name, msg):
    print("Location (Global): ", attr_name)
    print("lat : ", msg.lat)
    print("lon : ", msg.lon)
    print("altitude : ", msg.alt)
    # print("heading : ", msg.heading)
    # print("tilt : ", msg.tilt)


droneService = DroneService(48.115616, -1.638298)

droneService.register_position_callback(location_callback)

droneService.add_point(37.0, 1.337, 23)

droneService.add_point(38.1, 3, 23)

droneService.start_missions()
