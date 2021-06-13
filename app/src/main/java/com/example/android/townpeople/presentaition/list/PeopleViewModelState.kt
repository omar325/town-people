package com.example.android.townpeople.presentaition.list

import com.example.android.townpeople.data.Person

sealed class PeopleViewModelState {
    object Initial: PeopleViewModelState()
    object Loading: PeopleViewModelState()
    object Error: PeopleViewModelState()
    data class Success(
        val people: List<Person>
    ): PeopleViewModelState()
}