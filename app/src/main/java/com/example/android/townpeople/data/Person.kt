package com.example.android.townpeople.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Person(
    @field:SerializedName("thumbnail") val thumbnail: String = "",
    @field:SerializedName("name") val name: String = "",
    @field:SerializedName("professions") val professions: List<String>,
    @field:SerializedName("weight") val weight: Double = 0.0,
    @field:SerializedName("id") val id: Int = 0,
    @field:SerializedName("age") val age: Int = 0,
    @field:SerializedName("friends") val friends: List<String>,
    @field:SerializedName("height") val height: Double = 0.0,
    @field:SerializedName("hair_color") val hairColor: String = ""
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