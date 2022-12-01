package com.example.pert9.entity

import com.google.gson.annotations.SerializedName

data class Wind(
    @SerializedName("speed")val speed: Double,
    @SerializedName("deg")val degree: Double,
    @SerializedName("gust")val gust: Double,

) {

}