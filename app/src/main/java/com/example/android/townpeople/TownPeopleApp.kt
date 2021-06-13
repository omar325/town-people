package com.example.android.townpeople

import android.app.Application
import com.example.android.townpeople.domain.FetchPeopleUseCase
import com.example.android.townpeople.presentaition.list.PeopleViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class TownPeopleApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TownPeopleApp)
            modules(
                module {
                    factory<PeopleViewModel> {
                        PeopleViewModel.Default(
                            fetchPeopleUseCase = get(),
                        )
                    }
                    factory { FetchPeopleUseCase() }
                })
        }
    }
}