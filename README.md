# 🚧 Version 0 : Projet en cours de développement,
Ce projet a été initié dans le but de configurer un environnement Android Studio complet, 
avec déploiement et test sur un téléphone réel (via mirroring USB), 
afin de valider le bon fonctionnement d'une simple classe Kotlin (ici, une classe d'enregistrement audio).

L'objectif principal était de :
- Configurer Android Studio et les permissions nécessaires
- Connecter un téléphone en mode développeur + USB debugging
- Lancer l'application en conditions réelles
- Tester une classe de type `AudioRecorder` en interaction directe avec le matériel (microphone)

# 🎤 VoiceNotes

Application Android simple pour enregistrer des notes vocales en local.

## 🚀 Fonctionnalités

- Enregistrement audio à partir du micro  
- Interface intuitive avec Jetpack Compose  
- Gestion des permissions Android (RECORD_AUDIO)  
- Fichiers enregistrés localement (bientôt visibles dans le stockage)  
- Développée en Kotlin avec Android Studio  

## 🧱 Stack technique

- Kotlin  
- Jetpack Compose  
- MediaRecorder API  
- Android SDK  

## 📂 Organisation du code

- `MainActivity.kt` : UI + logique principale  
- `AudioRecorder.kt` : wrapper autour de `MediaRecorder`  
- `AndroidManifest.xml` : déclaration des permissions  

## 🔒 Permissions

L’application demande l’accès :
- au micro (`RECORD_AUDIO`)

## 💡 À venir

- Visualisation des enregistrements  
- Partage ou export des fichiers  
- Liste des enregistrements sauvegardés  
- Design Material amélioré  

## 👨‍💻 Auteur

[Jérôme Albrecht](https://github.com/jeromealbrecht)

---

> Projet personnel pour approfondir Jetpack Compose et l'accès au matériel audio sous Android.
