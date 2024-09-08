# Anylytics - Provider Agnostic Analytics Library for Android

**Anylytics** is a flexible, provider-agnostic analytics library for Android applications. It allows you to easily switch between different analytics providers without locking into a specific one.

## Features
- 🚀 **Provider Agnostic**: Easily switch or combine multiple analytics providers.
- 📊 **Simple**: Minimal, easy-to-use APIs for tracking common events.

## Installation

### Gradle Kotlin DSL

Add the following dependency to your project's `build.gradle.kts` file:

```kotlin
dependencies {
    implementation("me.kartikarora.anylytics:anylytics:<insert-latest-version>")
}
```

### Gradle Groovy

Add the following dependency to your project's `build.gradle` file:

```groovy
dependencies {
    implementation 'me.kartikarora.anylytics:anylytics:<insert-latest-version>'
}
```

## Usage

### Anlytics Interface

The library comes with an interface called `AnlytitcsInterface`. It provides abstract functions to track a screen view or an action.

e.g.
```kotlin
class SomeAnalyticsImpl : AnylyticsInterface {
    override fun trackScreen(view: Event.View) {
        // your analytics provider screen view call goes here
    }

    override fun trackAction(action: Event.Action) {
        // your analytics provider action call goes here
    }
}
```
Then in your code, simply create an instance of your implementation and call the functions

#### Screen Views
```kotlin
val analytics = SomeAnalyticsImpl()
val screenViewEvent = Event.View(
    screenName = "screen_name",
    contextData = ContextData(
        screenName = screenName,
        contextMap = mutableMapOf(
            key1 to value1,
            key2 to value2,
            key3 to value3,
        )
    ),
    breadCrumbs = BreadCrumbs(
        section = "section",
        subSection = "sub_section",
        subSubSection = "sub_sub_section"
    )
)
analytics.trackScreen(screenViewEvent)
```

#### Actions
```kotlin
val analytics = SomeAnalyticsImpl()
val actionEvent = Event.Action(
    actionName = "action_name",
    contextData = ContextData(
        screenName = screenName,
        contextMap = mutableMapOf(
            key1 to value1,
            key2 to value2,
            key3 to value3,
        )
    ),
    breadCrumbs = BreadCrumbs(
        section = "section",
        subSection = "sub_section",
        subSubSection = "sub_sub_section"
    )
)
analytics.trackAction(actionEvent)

```
Or if you only need to pass the `screen name` or `action name`
```kotlin
val analytics = SomeAnalyticsImpl()
analytics.trackScreen("screen_name")
analytics.trackAction("action_name")
```

Please see the [API docs](API.md) for information on various classes in the library. 

Also available is `LocalAnylyticsProvider` to provide the instance of the interface within a composable, as long as your screen/scaffold composable is wrapped in a `CompositionLocalProvider`.

```kotlin
val analytics = SomeAnalyticsImpl()
CompositionLocalProvider(
    LocalAnylyticsInterface provides analytics
) {
    YourComposable()
}
```
And for using it
```kotlin
val analytics = LocalAnylyticsInterface.current

analytics.trackScreen(...)

analytics.trackAction(...)
```

The library also comes with a few composables to do the same within a Compose based UI, as long as its being called within the scope of the `CompositionLocalProvider` for analytics. The arguments for the composables match with that of the interface functions.
```kotlin
TrackScreen(...)

TrackAction(...)
```

### Implementations

For convinece, Anylytics implementations for Adobe Experience Cloud Analytics and Firebase Analytics are also published.
These implementations take care of sending the parameters/context data in the right format for the upstream provider.
They also have empty constructors with `@Inject` annotation so they are ready for your hilt dependency injections.

#### Adobe Analytics

##### Gradle Kotlin DSL

Add the following dependency to your project's `build.gradle.kts` file:

```kotlin
dependencies {
    implementation("me.kartikarora.anylytics:anylytics-adobe:<insert-latest-version>")
}
```

##### Gradle Groovy

Add the following dependency to your project's `build.gradle` file:

```groovy
dependencies {
    implementation 'me.kartikarora.anylytics:anylytics-adobe:<insert-latest-version>'
}
```

#### Firebase Analytics

##### Gradle Kotlin DSL

Add the following dependency to your project's `build.gradle.kts` file:

```kotlin
dependencies {
    implementation("me.kartikarora.anylytics:anylytics-firebase:<insert-latest-version>")
}
```

##### Gradle Groovy

Add the following dependency to your project's `build.gradle` file:

```groovy
dependencies {
    implementation 'me.kartikarora.anylytics:anylytics-firebase:<insert-latest-version>'
}
```

## Sample

Sample project is available as the anylytics-demo project in this repository. It uses Firebase Analytics to create a sample usage of the analytics and anylytics-firebase libraries.
To build, you will need to add your own `google-services.json` file for the package name `me.kartikaarora.anylytics.demo`. The APK in the releases uses a mocked `google-services.json`.

## Contributing
Contributions are welcome! Please see [CONTRIBUTING.md](CONTRIBUTING.md) for details

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
