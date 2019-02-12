import requests
import json
import logging

FORMAT = "%(asctime)s %(message)s"
logging.basicConfig(format=FORMAT)


'''
Envoie une requete GET
:param url: url sur laquelle on envoie la requete 
:return: le retour sous format json (qu'il faut parser)
'''


def get_request(url):

    try:
        response = requests.get(url)
        response.raise_for_status()
        return response.json()
    except requests.exceptions.RequestException as e:
        logging.error(e)


'''
Envoie une requete POST 
:param url: url sur laquelle on envoie la requete 
:param body: body de la requete post
:return: le retour sous format json (potentiellement vide)
'''


def post_request(url, body):
    try:
        response = requests.post(url, data=json.dumps(body))
        response.raise_for_status()
        return response.json()
    except requests.exceptions.RequestException as e:
        logging.error(e)


