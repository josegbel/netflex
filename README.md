
# 🎬 Netflex

Welcome to Netflex, a Kotlin Multiplatform (KMP) project that showcases video playback using ExoPlayer on Android and AVPlayer on iOS. This project is designed with modularity and a clean architecture in mind, making it easy to maintain and extend.

## 🌟 Technology Used

- **Kotlin Multiplatform (KMP)**: Allows sharing code between Android and iOS, providing a seamless development experience.
- **Compose Multiplatform**: Used for building the UI in a declarative way on both Android and iOS. **Note I'm not using SwiftUI due to time constraints** 
- **ExoPlayer**: A powerful media player library used for video playback on Android.
- **AVPlayer**: A media player library used for video playback on iOS.
- **Koin**: A lightweight dependency injection library used to manage dependencies.

## 🏗️ Architecture

The Netflex project follows the principles of Clean Architecture, ensuring a clear separation of concerns. The project is divided into different Gradle modules to promote modularity and maintainability:

- **Presentation Layer**: Contains the UI components and view models. Built with Jetpack Compose.
- **Domain Layer**: Contains the business logic. This is use cases and Repository interfaces. This layer is completely platform-agnostic.
- **Data Layer**: This layer would contain the data sources and repository implementations. However, for this project, there is no network or database interaction. 

### Modules

- **app**: Android-specific code and the main entry point.
- **iosApp**: iOS-specific code and the main entry point.
- **presentation**: Contains the UI components and view models.
- **domain**: Contains the business logic in use cases.
- **data**: It's an empty module in this project, however I included it for completion.

### Dependency Injection

Koin is used for dependency injection, allowing easy management of dependencies and promoting a decoupled architecture.

## 🚀 Getting Started

To get started with the Netflex project, follow these steps:

1. **Clone the repository**:
   ```sh
   git clone https://github.com/josegbel/netflex.git
   cd netflex
   ```

2. **Open the project in Android Studio**: Ensure you have the latest version of Android Studio with Kotlin Multiplatform support.

3. **Build and run the project**:
   - For Android: Run the `presentation` module.
   - For iOS: Open the `iosApp` module in Xcode and run it.

## 📝 Testing

Unfortunately, due to time constraints, I was unable to add tests to the project. However, the architecture and modular design make it straightforward to add unit tests and UI tests in the future.

## 📄 License

This project is licensed under the MIT License.

---

Enjoy exploring Netflex! 🎥✨
