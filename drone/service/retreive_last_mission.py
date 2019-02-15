import base64

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


def send_photo(mission_id, result_dto):
    hostServer = config.hostServer
    url = hostServer + "/api/result/" + str(mission_id)
    res = post_request(url, result_dto)
    return res


def image_to_b64(file_path):
    with open(file_path, "rb") as image_file:
        encoded_string = base64.b64encode(image_file.read())

    return encoded_string
