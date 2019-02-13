from scripts.common import *
from scripts import config


'''
GET /api/mission/last
    {
        id: ...,

        points: [

            {index: int, x: double, y: double, z: double},

            {index: int, x: double, y: double, z: double},

            {index: int, x: double, y: double, z: double}
        ]
    }

'''


def get_last_mission():
    hostServer = config.hostServer
    url = hostServer + "/api/mission/last"
    res = get_request(url)
    return res
