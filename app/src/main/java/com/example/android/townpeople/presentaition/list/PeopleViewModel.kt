package com.example.android.townpeople.presentaition.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.townpeople.domain.AppExceptions
import com.example.android.townpeople.domain.FetchPeopleUseCase
import com.example.android.townpeople.presentaition.util.AppDispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

interface PeopleViewModel: KoinComponent {

    val state: LiveData<PeopleViewModelState>

    fun getPeople()

    class Default(
        private val dispatchers: AppDispatchers = AppDispatchers.Default(),
        private val fetchPeopleUseCase: FetchPeopleUseCase,
    ): PeopleViewModel, ViewModel() {
        private val _state = MutableLiveData<PeopleViewModelState>(PeopleViewModelState.Initial)
        override val state: LiveData<PeopleViewModelState> = _state

        private var fetchPeopleJob: Job? = null

        init {
            getPeople()
        }

        override fun getPeople() {
            _state.postValue(PeopleViewModelState.Loading)

            fetchPeopleJob?.cancel()
            fetchPeopleJob = viewModelScope.launch(dispatchers.IO) {
                _state.postValue(
                    try {
                        PeopleViewModelState.Success(fetchPeopleUseCase.invoke())
                    } catch (e: AppExceptions.NetworkErrorException) {
                        PeopleViewModelState.Error
                    }
                )
            }
        }
    }
}