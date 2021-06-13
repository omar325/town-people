package com.example.android.townpeople.domain

sealed class AppExceptions: Exception() {
    class NetworkErrorException: AppExceptions()
}