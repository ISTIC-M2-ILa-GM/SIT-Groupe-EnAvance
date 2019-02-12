import requests
import json
import logging

FORMAT = "%(asctime)s %(message)s"
logging.basicConfig(format=FORMAT)


'''
Envoie une requete GET sur 
'''


def get_request(url):

    try:
        response = requests.get(url)
        response.raise_for_status()
        return response.json()
    except requests.exceptions.RequestException as e:
        logging.error(e)


'''
Envoie une requete POST sur 
'''


def post_request(url, body):
    try:
        response = requests.post(url, data=json.dumps(body))
        response.raise_for_status()
        return response.json()
    except requests.exceptions.RequestException as e:
        logging.error(e)


if __name__ == '__main__':
    res = get_request("http://httpbin.org/get")
    print(res["url"])
    data = [{"hello": "world"}, {"hello2": "world2"}]
    res = post_request("http://httpbin.org/post", data)
    print(res)

