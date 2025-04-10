# Calculatrice-Java
Une application web Java de calculatrice

Ce projet est une application web simple de calculatrice, développée en Java avec l'architecture MVC (Modèle-Vue-Contrôleur). Il utilise Maven pour la gestion des dépendances et est conçu pour être déployé sur un serveur d'applications Tomcat.

## Architecture

L'application est divisée en deux parties principales :

* **Backend (API HTTP)** :
    * Implémente une API HTTP fournissant des services de calcul.
    * Développé avec des servlets Java et déployé sur Tomcat.
* **Frontend (Interface utilisateur)** :
    * Consomme les services de l'API HTTP du backend.
    * Fournit une interface utilisateur pour saisir les opérandes et l'opération.

## Prérequis

* Java Development Kit (JDK)
* Apache Maven
* Apache Tomcat
* NetBeans (ou un autre IDE Java)

## Instructions d'Installation et d'Exécution

1.  **Cloner le dépôt**

2.  **Construire le backend :**

    * Ouvrez une invite de commande dans le répertoire `calculatrice_backend`.
    * Exécutez la commande `mvn clean install`.

3.  **Construire le frontend :**

    * Ouvrez une invite de commande dans le répertoire `calculatrice_frontend`.
    * Exécutez la commande `mvn clean install`.

4.  **Déployer l'application sur Tomcat :**

    * Copiez les fichiers WAR générés (dans les répertoires `calculatrice_backend/target` et `calculatrice_frontend/target`) dans le répertoire `webapps` de votre serveur Tomcat.
    * Démarrez le serveur Tomcat.

5.  **Accéder à l'application :**

    * Ouvrez un navigateur web et accédez à l'URL http://ip_serveur:port/calculatrice_frontend par défaut `http://localhost:8080/calculatrice_frontend/`.
