package com.example.android.townpeople.data

data class Person(
    val thumbnail: String = "",
    val name: String = "",
    val professions: List<String>,
    val weight: Double = 0.0,
    val id: Int = 0,
    val age: Int = 0,
    val friends: List<String>,
    val height: Double = 0.0,
    val hairColor: String = ""
)