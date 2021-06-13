package com.example.android.townpeople.domain

import com.example.android.townpeople.data.Person

class FetchPeopleUseCase: UseCase<Any?, List<Any>> {
    override suspend fun invoke(params: Any?): List<Person> {
        return listOf(
            Person(
                "https://pfpmaker.com/_nuxt/img/profile-3-1.3e702c5.png", "Name1", emptyList(), friends = emptyList()
            ),
            Person(
                "https://pfpmaker.com/_nuxt/img/profile-3-1.3e702c5.png", "Name2", emptyList(), friends = emptyList()
            )
        )
    }

}