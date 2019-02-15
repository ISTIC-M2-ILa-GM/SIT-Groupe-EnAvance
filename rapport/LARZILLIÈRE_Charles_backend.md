# LARZILIÈRE Charles

# Equipe BackEnd

## Rôle : 

Développeur Java/JEE

## Mission : 
Implementer les services HTTP REST appelés par l'application mobile et le drône.
Sauvegarder les données dans une base de données type NoSQL.

## Les technos que j'ai utilisées sont :
* API REST en Java / Spring Boot
* Base de données MongoDB


### les Taches réalisées :
_PAIR PROGRAMMING AVEC BASHAR NEMEH_
* Communiquer avec les 2 équipes Android et drone afin de bien se mettre d'accord par rapport à l'architecture optimale et prendre en compte les modifications nécessaires plusieurs fois afin d'améliorer notre application.
* Découverte de MangoDB
* Créer les classes entités à persister et leurs repositories.
* Créer les classes DTO afin de les utiliser dans les requêtes HTTP Rest
* Créer la classe controller qui contient 4 services HTTP REST :
  * 1.POST - addMission : pour que le portable puisse nous envoyer la liste de points.
  * 2.GET - getLastMission : pour que le drone puisse recupére la dernière mission .
  * 3.POST - sendPhoto : pour que le drone puisse nous renvoyer la photo prise avec le point exact où la photo a été prise afin qu'on puisse les persister dans la base de données en format chaîne de caractère Base64.
  * 4.GET - getPhoto : pour que le drone puisse récupérer la photo (format chaîne de caractère Base64) et le point après recevoir une notification quand le drone fait son send Photo.

## Difficultées

* Gestion des demandes venant des différents pole de l'application pour une mise en place optimum des services sur une période de temps limité.



   
