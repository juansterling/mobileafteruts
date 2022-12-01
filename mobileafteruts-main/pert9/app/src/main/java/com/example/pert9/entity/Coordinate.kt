package com.example.pert9.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coordinate(
    @SerializedName("lon") val longitude: Double,
    @SerializedName("lat")val latitude: Double

) : Parcelable