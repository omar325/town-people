package com.example.android.townpeople.data

import android.os.Parcel
import android.os.Parcelable

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
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.createStringArrayList() ?: emptyList(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.createStringArrayList() ?: emptyList(),
        parcel.readDouble(),
        parcel.readString() ?: ""
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(out: Parcel, p1: Int) {
        out.writeString(thumbnail)
        out.writeString(name)
        out.writeStringList(professions)
        out.writeDouble(weight)
        out.writeInt(id)
        out.writeInt(age)
        out.writeStringList(friends)
        out.writeDouble(height)
        out.writeString(hairColor)
    }

    companion object CREATOR : Parcelable.Creator<Person> {
        override fun createFromParcel(parcel: Parcel): Person {
            return Person(parcel)
        }

        override fun newArray(size: Int): Array<Person?> {
            return arrayOfNulls(size)
        }
    }
}