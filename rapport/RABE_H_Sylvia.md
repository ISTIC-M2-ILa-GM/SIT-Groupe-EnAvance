# RABE Hantavolaniaina Sylvia

# Equipe BackEnd

## Rôle : 

Développeur Java/JEE

## Mission : 
Notre mission dans l'équipe Back-end était d'implementer les services HTTP REST qui seront appelés 
par l'application mobile et le drône , et persister les données dans une base de données type NoSQL.
L'installation et la mise à disposition du serveur fait aussi partie de notre mission.

## Les technos que j'ai utilisées sont :
* API REST en Java / Spring Boot
* Base de données MongoDB
* Apache Tomcat
* Nginx


### Les tâches réalisées :
* Communiquer avec les 2 équipes Android et drone afin de bien se mettre d'accord par rapport l'architecture optimale et prendre en compte les modifications nécessaires plusieurs fois afin d'améliorer notre application.
* Ajout et modification de quelques dépendances dans le pom XML.
* Installation de "robomongo", interface graphique de MongoDB, pour faciliter les intéractions avec la base de données NoSQL.
* Création du serveur avec Tomcat.
* Création de container nginx pour le serveur de notre projet.
* Ajout de plugin docker dans le pom et la création de Dockerfile et docker-compose pour les tests.


### Installation de robomongo :
1. Télécharger le fichier tar.gz de Official robomongo website:
   wget https://download.robomongo.org/0.9.0/linux/robomongo-0.9.0-linux-x86_64-0786489.tar.gz
   
2. Extraire le fichier tar.gz:
   tar -xvzf robomongo-0.9.0-linux-x86_64-0786489.tar.gz
   
3. Déplacer les fichiers dans robomongo vers /usr/local/bin:
   sudo mkdir /usr/local/bin/robomongo
   sudo mv  robomongo-0.9.0-linux-x86_64-0786489/* /usr/local/bin/robomongo
   
4. Exécuter robomongo dans /usr/local/bin/robomongo/bin avec la ligne de commande suivante:
   cd /usr/local/bin/robomongo/bin
   
   S'assurer que robomongo est exécutable:
   sudo chmod +x robomongo
   
   Exécuter robomongo:
   ./robomongo


### Les difficultés rencontrées :
* La centralisation de MongoDB n'a pas pu se faire car le robomongo ne voulait pas se connecter à une machine voisine que ce soit en SSH ou en mettant directement l'adresse IP d'une machine. Cependant, chacun était en mesure de tester sur leur machine.
* La configuration de nginx est complexe et demande de la documentation.
* La dockerisation de spring boot application et mongodb nous génère toujours l'erreur 500.

