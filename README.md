# WeatherApp
Application Android affiche les prévisions météorologiques pour les 5 villes suivantes : Lyon, Paris, Nantes, Bordeaux et Rennes. Elle récupère les données météorologiques à partir de l'API https://openweathermap.org/current.

Dans mon cas, j'ai utilisé Activities (MainActivity et WeatherActivity) pour afficher les différentes interfaces de mon application. Il fonctionne bien et répond à vos besoins actuels.

Cependant, le remplacement des activités par des fragments peut être un moyen plus flexible et évolutif, surtout si je prévois d'ajouter plus de fonctionnalités ou de vues à mon application à l'avenir.

## Configuration
Pour exécuter l'application localement, suivez ces étapes :

1- Clonez le dépôt :
git clone https://github.com/votre-nom-utilisateur/appweather.git

2- Ouvrez le projet dans Android Studio.

3- Compilez et exécutez l'application sur un émulateur ou un appareil connecté.

## Dépendances
L'application utilise les dépendances suivantes :

AndroidX Core KTX : 1.7.0
AndroidX AppCompat : 1.6.1
Google Material : 1.9.0
ConstraintLayout : 2.1.4
JUnit : 4.13.2 (pour les tests)
Espresso Core : 3.5.1 (pour les tests d'interface utilisateur)
Retrofit : 2.9.0 (pour les requêtes réseau)
Retrofit GSON Converter : 2.9.0 (pour la sérialisation JSON)
Kotlin Coroutines Android : 1.7.1 (pour la programmation asynchrone)
Kotlin Coroutines Core : 1.7.1
AndroidX Lifecycle Extensions : 2.2.0 (pour ViewModel et LiveData)
Kotlin Stdlib JDK8 : 1.8.21
AndroidX Lifecycle ViewModel KTX : 2.5.1
AndroidX Lifecycle Runtime KTX : 2.5.1
AndroidX Lifecycle ViewModel SavedState : 2.5.1
AndroidX Activity KTX : 1.7.1
Data Binding Runtime : 7.2.0
Glide : 4.12.0 (pour le chargement et la mise en cache des images)
Lottie : 4.1.0 (pour les animations)
Dagger Hilt Android : 2.44 (pour l'injection de dépendances)
Dagger Hilt Compiler : 2.44

## Compatibilité
Compile SDK : 33
Minimum SDK : 24
Target SDK : 33
Source Compatibility : Java 8
Target Compatibility : Java 8
