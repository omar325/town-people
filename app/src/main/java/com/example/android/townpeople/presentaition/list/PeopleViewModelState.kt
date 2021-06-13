package com.example.android.townpeople.presentaition.list

sealed class PeopleViewModelState {
    object Initial: PeopleViewModelState()
    object Loading: PeopleViewModelState()
    object Error: PeopleViewModelState()
    data class Success(
        val people: List<Any>
    ): PeopleViewModelState()
}