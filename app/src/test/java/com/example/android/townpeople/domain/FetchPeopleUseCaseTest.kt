package com.example.android.townpeople.domain

import com.example.android.townpeople.data.ApiResponse
import com.example.android.townpeople.data.Person
import com.example.android.townpeople.data.RemoteRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.Assert
import junit.framework.Assert.assertSame
import junit.framework.Assert.assertTrue
import junit.framework.TestCase
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class FetchPeopleUseCaseTest {
    @MockK
    lateinit var remoteRepository: RemoteRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {}

    @Test
    fun remoteData_is_fetched_when_useCase_executes() = runBlockingTest {
        val remoteData = ApiResponse(listOf(
            Person("", "Name1", emptyList(), friends = emptyList())
        ))

        coEvery { remoteRepository.getPeople() } returns remoteData

        val actualData = FetchPeopleUseCase(remoteRepository).invoke()

        assertSame(actualData, remoteData.people)
    }

    @Test
    fun ErrorCreatingCounterException_thrown_when_repository_fails() = runBlockingTest {
        var isExpectedExceptionThrown = false

        coEvery { remoteRepository.getPeople() } throws Exception()

        try {
            FetchPeopleUseCase(remoteRepository).invoke("")
        } catch(e: AppExceptions.NetworkErrorException) {
            isExpectedExceptionThrown = true
        }

        assertTrue(isExpectedExceptionThrown)
    }
}