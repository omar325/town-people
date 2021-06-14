package com.example.android.townpeople.data

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @field:SerializedName("Brastlewark") val people: List<Person>
)