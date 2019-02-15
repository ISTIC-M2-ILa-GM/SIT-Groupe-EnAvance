# NEMEH Bashar

# Equipe BackEnd

## Rôle : 

Développeur Java/JEE

## Mission : 
Notre mission dans l'équipe Back-end était d'implementer les services HTTP REST qui seront appelés 
par l'application mobile et le drône , et persister les données dans une base de données type NoSQL

## Les technos que j'ai utilisées sont :
* API REST en Java / Spring Boot
* Base de données MongoDB


### les Taches réalisées :
* Communiquer avec les 2 équipes Android et drone afin de bien se mettre d'accord par rapport à l'architecture optimale et prendre en compte les modifications nécessaires plusieurs fois afin d'améliorer notre application.
* Rajouter toutes les dépendances nécessaires pour la partie spring boot et mongodb dans le pom XML.
* Créer toutes les classes entités à persister et leurs repositories.
* Créer le service "NextSequenceService" qui nous permet de générer des index automatiquement incrémentés pour le document Mission et garantir leur unicité.
* Créer toutes les classes DTO afin de les utiliser dans les requêtes HTTP Rest (pour récupérer les données avec une méthode POST et renvoyer de données avec une méthode GET).
* Créer la classe controller qui contient 4 services HTTP REST :
  * 1.POST - addMission : pour que le portable puisse nous envoyer la liste de points.
  * 2.GET - getLastMission : pour que le drone puisse recupére la dernière mission .
  * 3.POST - sendPhoto : pour que le drone puisse nous renvoyer la photo prise avec le point exact où la photo a été prise afin qu'on puisse les persister dans la base de données en format chaîne de caractère Base64.
  * 4.GET - getPhoto : pour que le drone puisse récupérer la photo (format chaîne de caractère Base64) et le point après recevoir une notification quand le drone fait son send Photo.
* Tester toutes les fonctions précédentes en local en utilisant Postman avec plusieurs valeurs et plusieurs conditions:
  * 1.addMission :
  
    ##### PostMan:
    
    ![alt text](https://i.ibb.co/289qbg2/add-Mission.png)
    
    ##### MongoDB:
    
    ![alt text](https://i.ibb.co/ZhqbsBR/Addmission2.png)
    
    * 2.getLastMission :
  
    ##### PostMan:
    
    ![alt text](https://i.ibb.co/KhvY2Jn/get-Last-Mission.png)
    
      * 3.sendPhoto :
  
    ##### PostMan:
    
    ![alt text](https://i.ibb.co/QMmVZqT/send-Photo.png)
    
    ##### MongoDB:
    
    ![alt text](https://i.ibb.co/YhcYjDX/send-Photo1.png)
    
        
    * 4.getLastMission :
  
    ##### PostMan:
    
    ![alt text](https://i.ibb.co/zJRhQQy/getphoto.png)

 


   
