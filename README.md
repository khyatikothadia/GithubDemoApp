# GitHubDemoApp with MVVM Architecture & Jetpack Compose

The GitHub Demo Android app is a mobile application designed to interact with the public GitHub API, providing users with detailed information about a specific GitHub user.

# Features

- **User Lookup**: Input a GitHub user's ID to see their avatar and name.
- **Repository List**: Explore the user's public repositories through a scrollable list, showcasing repository names and brief descriptions.
- **Repository Detail**: Dive deeper into a specific repository by selecting it, revealing comprehensive details on a dedicated detail screen.
- **Animated Interaction**: Enjoy a visually engaging experience with animations that present user and repository information in an intuitive and lively manner.

# Tech Stack
- [Kotlin](https://kotlinlang.org/docs/getting-started.html) - First class and official programming language for Android development.
- [Coroutines](https://developer.android.com/kotlin/coroutines) - For concurrency and asynchronous tasks
- [Flow](https://developer.android.com/kotlin/flow) - A asynchronous data stream that sequentially emits values and completes normally or with an exception.
- [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) - A live data replacement.
- [Dependency Injection](https://developer.android.com/training/dependency-injection)
  - [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - An easier way to incorporate Dagger DI into the Android application.
- [Android Architecture Components](https://developer.android.com/topic/architecture) 
  - [Jetpack Compose](https://developer.android.com/jetpack/compose) - Modern toolkit for building native UI
  - [Material3](https://m3.material.io/) - Modern design guide native UI theme.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
  - [Navigation Components](https://developer.android.com/guide/navigation) - Navigate to different pages more easily.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [Coil](https://coil-kt.github.io/coil/) - An image loading library for Android backed by Kotlin Coroutines.
- [Mockk](https://mockk.io/) - For Mocking and Unit Testing

# Architecture Patterns

### 1. **MVVM** ###

  MVVM architecture is a Model-View-ViewModel architecture that removes the tight coupling between each component. It is a design pattern that separates the user interface (View) from the underlying data and business logic (Model). Most importantly, in this architecture, the children don't have the direct reference to the parent, they only have the reference by observables.

### 2. **Repository** ###

  The Repository Pattern is a design pattern commonly used in software development to abstract and centralize data access logic. It provides a clean and organized way to manage the interaction between the application's business logic and the data sources (such as databases, web services, or APIs).

- Abstracts and centralizes data access logic, decoupling business logic from storage details.
- Separates data access, making code modular. Changes are confined to the repository, easing maintenance.
- Enhances testability by separating concerns. Mocking repositories in tests isolates business logic.
- Facilitates easy switching between data sources (e.g., local database to remote API).
