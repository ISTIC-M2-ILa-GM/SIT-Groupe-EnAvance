# Sous-projet Drone

## Objectifs
* Obtenir les missions du back-end
* Executer les missions
* Fournir les images/vidéos et la télémétrie au back-end

## Membres
* BANNIER Hugo
* GAUHIER Arnaud
* LE HENAFF Gwénolé
* MERZOUK Fahim

## Technologies
* [Python 2.7](https://docs.python.org/2/)
* [Dronekit](http://python.dronekit.io/)

## Environnement de développement

### PyCharm

La mise à jour est automatique par la fonction Sync

#### Création

Créer un environnement virtuel :

File -> Settings -> Project drone -> Project Interpreter -> Roue dentée -> Add
VirtualEnv Environment ->  Base interpreter python 2

### Nouvelle dépendance

Ecrire simplement le nom de la librairie et sa version dans le fichier ```requirements.txt```

### Console

#### Création

Utiliser python 2

```virtualenv -p python2 venv```

```pip install -r requirements.txt```

#### Activer

```source venv/bin/activate```

#### Désactiver

```deactivate```

#### Nouvelle dépendance

Ecrire simplement le nom de la librairie et sa version dans le fichier ```requirements.txt```

Et saisir dans votre console :

```pip install -r requirements.txt```

#### Mise à jour

```pip install -r requirements.txt```

  
### Google earth
```bash
google-earth-pro $PWD/camera_link_file.kml
```
