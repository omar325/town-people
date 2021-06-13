package com.example.android.townpeople.domain

import com.example.android.townpeople.data.Person

class FetchPeopleUseCase: UseCase<Any?, List<Any>> {
    override suspend fun invoke(params: Any?): List<Person> {
        return listOf(
            Person(
                "", "Name1", emptyList(), friends = emptyList()
            ),
            Person(
                "", "Name2", emptyList(), friends = emptyList()
            )
        )
    }

}