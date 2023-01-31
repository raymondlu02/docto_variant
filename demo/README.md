# README PROJET DOCTOLIB

# Utilisation : 
Il faut cree une DB doctolib 
```sql 
CREATE DATABASE doctolib;
```

Il faut cree un utilisateur : devrep, avec un mdp : devrep, et lui donner les droits.

```sql
 CREATE USER 'devrep'@'localhost' IDENTIFIED BY 'devrep';
    GRANT ALL PRIVILEGES ON * . * TO 'devrep'@'localhost';
    FLUSH PRIVILEGES;
```

Puis Il suffit d'executer DemoApplication.java depuis vscode

# Connexion user : 
username : jourdan@gil.com
password : mdp

# Connexion pro : 
username : tournesol@fred.be
password : mdp

# Fonctionnalité
Sans être connecté : 

-On peut revenir à l'accueil en cliquant sur Home
-S'inscrire en tant qu'utilisateur ou professionnel depuis la page d'accueil en cliquant sur les boutons d'inscriptions respectives.
-Chercher un professionnel par rôle ou par nom depuis l'accueil. (recherche vide affiche tous les professionnels)
-Se connecter (username = mail)

-Voir les disponibilités d'un professionnel en cliquant sur le bouton "disponibilité" du professionnel en question.
-Prendre un rendez-vous en cliquant sur le bouton "prendreRDV" (si on n'est pas connecté on est redirigé vers la page login)

En étant connecté :
-Faire tout ce qui peut être fait en non connecter (sauf se connecter et s'inscrire évidemment)
-Se déconnecter
-Consulter ses rendez-vous, annuler des rendez-vous
-Consulter son profil, sur lequel on peut mettre à jour ses informations.


