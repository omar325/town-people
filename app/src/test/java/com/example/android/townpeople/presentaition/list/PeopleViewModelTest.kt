package com.example.android.townpeople.presentaition.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.android.townpeople.data.Person
import com.example.android.townpeople.domain.AppExceptions
import com.example.android.townpeople.domain.FetchPeopleUseCase
import com.example.android.townpeople.presentaition.util.AppDispatchers
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertSame
import junit.framework.TestCase
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PeopleViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testAppDispatchers = object: AppDispatchers {
        override val IO = TestCoroutineDispatcher()
    }

    @MockK
    lateinit var fetchPeopleUseCase: FetchPeopleUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun state_is_Loading_when_constructor_executes() {
        coEvery { fetchPeopleUseCase.invoke(any()) } returns emptyList()

        testAppDispatchers.IO.pauseDispatcher()

        val viewModel = PeopleViewModel.Default(
            testAppDispatchers,
            fetchPeopleUseCase,
        )

        val expectedState = PeopleViewModelState.Loading
        val actualActual = viewModel.state.value

        assertSame(expectedState, actualActual)
    }

    @Test
    fun state_is_Error_when_fetchPeopleUseCase_throws_NetworkErrorException() {
        coEvery { fetchPeopleUseCase.invoke(any()) } throws AppExceptions.NetworkErrorException()

        val viewModel = PeopleViewModel.Default(
            testAppDispatchers,
            fetchPeopleUseCase,
        )

        val expectedState = PeopleViewModelState.Error
        val actualActual = viewModel.state.value

        assertSame(expectedState, actualActual)
    }

    @Test
    fun state_is_Success_when_fetchPeopleUseCase_returns_expectedData() {
        val expectedData = emptyList<Person>()
        coEvery { fetchPeopleUseCase.invoke(any()) } returns expectedData

        val viewModel = PeopleViewModel.Default(
            testAppDispatchers,
            fetchPeopleUseCase,
        )

        val expectedState = PeopleViewModelState.Success(expectedData)
        val actualActual = viewModel.state.value

        assertEquals(expectedState, actualActual)
    }

}