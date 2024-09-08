# Classes

## `Event`

Sealed class to hold data of an event that might be of interest from analytics perpective.

- **`Event.View`**: Represents a screen view event
- **`Event.Action`**: Represents an action performed by the user, e.g. CTA Tap.

## `Type`

Sealed class to represent the types of events possible
- **`State`**: State of a screen
- **`Action`**: Action on a screen

## `ContextData`

This class holds data in the form of Key Value pairs about what's going on the screen. This helps create a context of the analytics event that's being captured.

## `BreadCrumbs`

This class holds three strings to represents a trail of navigation from a screen
e.g. If a user goes from "Home" to "Search" to an "Item", the breadcrumbs would as follows
```kotlin
val breadCrumbs = BreadCrumbs(
    section = "Home",
    subSection = "Search",
    subSubSection = "Item"
)
```

# Interfaces

## `AnylyticsInterface`

The main interface which enables an agnostic analytics implementation. With a simple 2 function override, any analytics provider can be implemented using this interface so that they can be swapped without having to remove/update the function calls. 

