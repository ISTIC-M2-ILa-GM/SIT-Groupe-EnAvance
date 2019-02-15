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
* Docker
* docker-compose


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
  - controller: implémente un service HTTP REST, qui permet d'envoyer la notification au firebase cloud Messaging 
  
 containeriser la base de donnée. Construction de container docker pour lancer utilisant docker-compose.

## Difficultés rencontrées

* Dockeriser entièrement la partie back-end. A cause d'un problème technique dans l'usage d'un plugin dans maven, nous n'avons pas pu integrer un service dans le docker-compose pour établir le lien avec le service mongodb.

* Pousser la notification au firebase cloud messaging en redérigant des paramètres d'une requête POST provenante du drone.

