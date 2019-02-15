
## Rapport SIT
### Groupe A
### Application Mobile

## Noureddine KADRI


Dans cette partie du projet, nous avons développé une application mobile Android **`Lapin`**, qui est dédiée à la création de missions pour le drone ainsi que la visualisation des images capturées.

## Tâches réalisées: 
- Création des missions:  Pour réaliser cette tâche, nous avons implémenté l’API **GoogleMaps**, pour sa simplicité d’utilisation.
L’API accède aux coordonnées GPS du terminal via **Google Play services**.

L’utilisateur doit introduire la hauteur du vol, cliquer sur l’endroit ou il souhaite envoyer le drone.

Nous avons mis en place un Listener afin de récupérer les cliques sur la carte. Un clique génère un **Point** de coordonnées `(Latitude, Longitude, Altitude)` et ajoute un marqueur visuel sur la carte pour mémoriser l’endroit.

- Implémentation d'une base de données:  Dans un premier temps, nous avons créé une base de donnée **SQLite** pour stocker les points générés sur le device de l’utilisateur, mais nous n’avons pas utilisé cette solution pour des raisons techniques.

- Transmission des données:  Une liste de points sera envoyée au serveur grâce à une **API REST**,  pour enfin la transmettre au drone.
 
  
