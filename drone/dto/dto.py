class PointDeBase:

    def __init__(self, lat, lon, alt):
        self.x = lat
        self.y = lon
        self.z = alt
        pass


class ResultDTO:
    def __init__(self, picture, point_de_base):
        self.picture = picture
        self.point_de_base = point_de_base
        pass
