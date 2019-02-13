# coding=utf-8
import time

from service.retreive_last_mission import get_last_mission

'''
Boucle d'attente des données d'une mission
'''


def retrievingmission():
    tempmissionfound = False
    while not tempmissionfound:

        tempmission = get_last_mission()
        if tempmission is not None:
            tempmissionfound = True

        else:
            time.sleep(5)

    return tempmission


'''
Envoi les  parapètres de mission au drone 
'''


def giveorders(param):
    print "coucou0"
    """
    TODO param the drone with "mission"
    """


'''
Ecoute les status du drone et trensmet les données jusqu'en fin mission 
'''


def monitoringmission():
    print "coucou1"
    """
    TODO handel listners
    """

'''
Script principal de récupération de mission, avant transmition au drône puis attente de la fn de la mission avant retour à l'attente de mission
'''

if __name__ == '__main__':

    execute = True

    while execute:
        mission = retrievingmission()

        giveorders(mission)

        monitoringmission()

        execute = False

    print "Fin"
