# Mounir BOUYAMINE
Rapport SIT 

Projet Lapin

## Rôle

* Dev Backend

## Mission

Mise en place des API REST pour faire le lien entre le backend, le front(application mobile) et le drône . Persister les données dans une base de données NoSQL.

## Technos

* Java
* Spring boot
* Firebase Cloud Messaging
* MongoDB


## Tâches réalisées

Mise en place d'un service de messaging. Il sert à créer un Topic auquel le front-end de l'application pourra s'inscrire et récuperer des messages sous forme de fichiers JSON. Le message présente une notification avec l'id du résultat et l'id de la mission. Ces dernièrs sont récupérés du drone, via un API HTTP POST.

* Message à envoyer : 
``` {
           "notification": {
              "title": "Notification",
              "body": "Mission notification"
           },
           "data": {
              "mission_id": "m_id",
              "result_id": "point id"
           },
           "to": "/topics/drone",
           "priority": "high"
        }
```
* Acteurs:

  - Dependence json dans maven
  - Service androidPushNotificationService + HeaderRequestInterceptor: Pour pousser la notification.
  - webController: implémente un service HTTP REST, qui permet d'envoyer la notification au firebase cloud Messaging 

## Difficultés rencontrées

