package com.example.android.houserental.domain.model
import android.location.Location
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class House(
    val id: Int,
    val image: String,
    val price: Double,
    val bedrooms: Int,
    val bathrooms:Int,
    val size:Double,
    val description:String,
    val zip: String,
    val city:String,
    val latitude: Double,
    val longitude:Double,
    val createdDate:String,
    var distance:String
) : Parcelable {
}