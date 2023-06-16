# Mobile Development
Here is the Mobile Development repository of the Bean Greader application. Application to find information about coffee and know the quality of coffee beans.

## Table of Content
* [Architecture](#architecture)
* [Reuirements](#requirements)
* [Dependencies](#dependencies)
* [Features](#features)
* [Preview](#preview)


## Architecture
We use MVVM (Model View ViewModel) design pattern with a Clean Architecture. MVVM is a GUI-based application development architecture that focuses on the separation between code for business logic and application views. In its application, MVVM is divided into several layers, namely Model, View, and ViewModel. The following is a discussion of the three layers below.

* **Model** :
This layer is a model or entity that represents the data that will be used in business logic. Generally, the classes in it are POJO or Plain Old Java Object and Data Classes if we use Kotlin.
* **View** :
Unlike the previous layer, this layer contains the UI of the application to manage how the information will be displayed. This layer will contain classes, such as Activity and Fragment.
* **ViewModel** :
The last layer is the ViewModel which is responsible for interacting with the model where the data will be passed to the view layer.

## Requirements
* Minimum SDK 24
* Target SDK 33
* JDK VERSION 17
* Kotlin 1.8.10
* Android Gradle Plugin 8.1.0
* Gradle 8.0

## Dependencies

* [Material Design 3](https://m3.material.io/) Material Design is an adaptable system of guidelines, components, and tools that support the best practices of user interface design. Backed by open-source code, Material Design streamlines collaboration between designers and developers, and helps teams quickly build beautiful products.

* [Jetpack Compose](https://developer.android.com/jetpack/compose?gclid=Cj0KCQjwj_ajBhCqARIsAA37s0y845dTgL-gmRYWyATAUEanz3qTYdGRUZMQC_tI8sj3N6QgtWTDLlkaAm5YEALw_wcB&gclsrc=aw.ds&hl=id) Jetpack Compose is Android's recommended modern toolkit for building native UIs. Jetpack Compose simplifies and accelerates UI development on Android. Build your apps faster with less code, powerful tools, and an intuitive Kotlin API.

* [Firebase Authentication](https://firebase.google.com/docs/auth?hl=id) Firebase is a service from Google to make it easy and even easier for application developers to develop their applications. Firebase aka BaaS (Backend as a Service) is a solution offered by Google to speed up developer work.

* [Coil Compose](https://coil-kt.github.io/coil/compose/) Coil is an image loading library that uses Kotlin Coroutines as the backbone to support its performance.

* [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android?hl=id) Hilt is a dependency injection library for Android that reduces boilerplate when doing manual dependency injection in your project. With manual dependency injection, you have to manually create each class and its dependencies, and use containers to reuse and manage dependencies.

* [Camera X](https://developer.android.com/training/camerax?hl=id) CameraX is a Jetpack library, built to make camera app development easier. For new apps, we recommend starting with CameraX. CameraX provides a consistent and easy-to-use API that works on most Android devices, with backward compatibility up to Android 5.0 (API level 21).

* [Room](https://developer.android.com/training/data-storage/room?hl=id) Room is a built-in Android library that provides an abstraction on top of SQLite to allow easier database access without losing the powerful advantages that SQLite has.

* [Retrofit](https://square.github.io/retrofit/) Retrofit is a Rest Client library for android and java from squareup. it makes it relatively easy to fetch and upload JSON (or other data structures) via a REST-based webservice.

* [Kotlin Flow](https://developer.android.com/kotlin/flow?hl=id) In a coroutine, a flow is a type that can display multiple values sequentially, as opposed to a suspend function that only displays a single value. For example, you can use flow to receive updates directly from the database.

* [Airbnb Lottie](https://github.com/airbnb/lottie-android) Lottie is an animation library built by Airbnb that creates Adobe After Effects animations in real time on Android, iOS and native React. This is a sample app that includes some animations as well as different ways Lottie can be used in real apps.

## Features

In the Bean Greader application there are several features:

- Users can login using their email and password or Gmail account.
- There is various information about coffee on the Home page.
- There is a main feature, namely Scan Coffee Beans to find out the quality of coffee beans.
- Users can add favorites to the information about coffee.
- USer can choose light theme and dark theme on the Application.
- There are two main languages, namely Indonesian and English.

## Preview

<table>
    <tr>
        <td><img src="screenshot/github.gif" align="center" alt="4"</td>
        <td><img src="screenshot/splash.jpg" align="center" alt="4"</td>
        <td><img src="screenshot/home.jpg" align="center" alt="4"</td>
    </tr>
    <tr>
        <td><img src="screenshot/followers.jpg" align="center" alt="4"</td>
        <td><img src="screenshot/following.jpg" align="center" alt="4"</td>
        <td><img src="screenshot/search.jpg" align="center" alt="4"</td>
    </tr>
<table>
