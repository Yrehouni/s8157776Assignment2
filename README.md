# s8157776Assignment2

An Android application for the NIT3213 assignment 2. Users log in through the Footscray API, view dashboard entities in a list, and open an entity to see its full details.

## Requirements

- Android Studio
- JDK 17
- Android SDK Platform 37
- Internet connection

The project uses Gradle 9.6.0 and Android Gradle Plugin 9.4.1. Gradle is configured to download the required Gradle distribution when the project is opened or built.

## Build and run

1. Clone or download this repository.
2. Open the project in Android Studio.
3. Allow Gradle to sync.
4. Select an Android emulator or connect an Android device with USB debugging enabled.
5. Click Run in Android Studio.


## Login
The app uses the Footscray authentication endpoint:
https://nit3213apinew.onrender.com/footscray/auth
Enter your student ID as the username without the leading s, and your first name as the password, with the correct capitalization. The app uses the returned keypass to request dashboard data.
The API is hosted on a free service and may take a little time to respond if it has been idle.
Main features
- Login with error handling
- Dashboard list of entities retrieved from the API
- Entity details screen showing the full information, including the description
- Hilt dependency injection
- Unit tests for the login and dashboard ViewModels
  Main technologies
- Kotlin
- Android Views and RecyclerView
- Retrofit and Gson for API requests
- Hilt and KSP for dependency injection
- Kotlin Coroutines
- JUnit and coroutine test libraries