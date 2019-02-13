from service.drone_service import DroneService

droneService = DroneService()

droneService.add_point(37.0, 1.337, 23)

droneService.add_point(37.1, 1.336, 23)

droneService.start_missions()






