from scripts import config
from scripts.common import *

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


def send_photo(id, result_dto):
    hostServer = config.hostServer
    url = hostServer + "/api/result/" + str(id)
    res = post_request(url, result_dto)
    return res
