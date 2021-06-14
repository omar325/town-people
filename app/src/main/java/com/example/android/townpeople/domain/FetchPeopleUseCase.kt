package com.example.android.townpeople.domain

import com.example.android.townpeople.data.Person
import com.example.android.townpeople.data.RemoteRepository
import java.lang.Exception

class FetchPeopleUseCase(
    private val remoteRepository: RemoteRepository,
): UseCase<Any?, List<Any>> {
    override suspend fun invoke(params: Any?): List<Person> {
        return try {
            remoteRepository.getPeople().people
        } catch (e: Exception) {
            throw AppExceptions.NetworkErrorException()
        }
        /*return listOf(
            Person(
                "https://pfpmaker.com/_nuxt/img/profile-3-1.3e702c5.png", "Name1", emptyList(), friends = emptyList()
            ),
            Person(
                "https://pfpmaker.com/_nuxt/img/profile-3-1.3e702c5.png", "Name2", emptyList(), friends = emptyList()
            )
        )*/
    }

}