# Android Assignment TownPeople
TownPeople is an app that displays the census of a population with the detail for each villager.

## Features
* List of villagers from the internet
* Profile images caching to improve user experience
* Unit and Instrumentation tests
* Material components and styles

## Project Structure
The code base is organized in 3 different packages
- domain: Contains use cases and exceptions used in the app
- data: Contains classes and interfaces for network connectivity
- presentation: Contains fragments, activities, viewmodels and adapters

## Architecture
### MVVM
The app is composed of the views:
- `MainActivity`
- `PeopleFragment`
- `PeopleDetailFragment`
The PeopleFragment has a view models provided by Koin dependency injection.
View model state is observed through Live data from the fragment, updating the view elements upon state change.

### Repositories
The view model requires a remote repository to get information. The `RemoteRepository` interface can be implemented to get data from the internet or from the device. 
In this project, Retrofit is used as an implementation to get data from a remote json file with GSON for parsing.

### Dependency injection
The dependencies required for view models and repositories are managed by Koin. 
Koin is configured in the `TownPeopleApp` class. Koin is also used to inject mock dependencies in the tests.

### Image caching
Glide is used to cache profile images

### Androidx
Latest androidx features implemented: viewpager2, testing

## Third party libraries
- Retrofit for internet connectivity
- Glide for image caching
- Koin for dependency injection