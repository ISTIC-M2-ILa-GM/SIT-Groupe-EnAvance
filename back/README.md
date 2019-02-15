
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