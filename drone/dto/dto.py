class PointDeBase:
    def __init__(self, lat, lon, alt):
        """
        :param lat: latitude (double)
        :param lon: longitude (double)
        :param alt:  altitude (double)
        """
        self.x = lat
        self.y = lon
        self.z = alt
        pass


class ResultDTO:
    def __init__(self, picture, point_de_base):
        """
        :param picture: string
        :param point_de_base: PointDeBase
        """
        self.picture = picture
        self.point_de_base = point_de_base
        pass
