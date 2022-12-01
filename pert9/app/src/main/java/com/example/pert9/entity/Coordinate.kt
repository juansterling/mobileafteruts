package com.example.pert9.entity

import com.google.gson.annotations.SerializedName

data class Coordinate(
    @SerializedName("lon") val longitude: Double,
    @SerializedName("lat")val latitude: Double

) {
}