# LEO Innovation Lab Test

This is an Android assignment for Harjit provided by Kevin Pelgrims.

## Installation

- Clone the repository
- Open the project in Android Studio
- Open emulator/device and Run as Android


## Instructions
When the user launches the app, he/she must click on the search icon and enter a Github username. After typing the username the user will press "Enter" on their keyboard and the app will fetch a user's profile information and their public repository.

Pressing the Profile button on BottomNavigation bar, the user can see their profile, and pressing the Repositories they can see their public repositories.


## Architecture
The app's utilizes the Android Architecture Components with ViewModel and LiveData. The app contains one Activity called MainActivity, which has a ViewModel, and the Activity contains two Fragments via BottomNavigation. These Fragments are hooked to the MainActivity's ViewModel. The ViewModel contains a two important LiveDatas. One for user-information and one for repositories.

The reason why I used one single Activity with a shared ViewModel amongst the Fragments was, both Fragments needed the same information from the same API-calls. Instead of calling the APIs multiple times, they are called at one place and then the rest of the app is updated.

#### API
`GithubApi.kt` uses RetroFit to call Github's Apis. 

#### Repository
`GithubRepository.kt` calls the two methods from `GithubApi` and returns a `User` and a `List<Repository>`. Note the `await()`extension which is a Kotlin extension allowing synchronous way to write Kotlin.

#### ViewModel
`MainViewModel`hooks a LiveData on the result from `User` and `List<Repository>`. The LiveData `user` is binded to the layout file for `ProfileFragment` so whenever `user` is updated the fields in the layout file is updated too.

In `RepositoryFragment` the list `repositories` are inserted to a RecyclerView Adapter which items are binded to `Repository`. This allows more clean code from the Kotlin file. 