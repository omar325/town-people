package com.example.android.townpeople.domain

interface UseCase<in Parameter, out Result> {
    suspend operator fun invoke(params: Parameter? = null) : Result
}