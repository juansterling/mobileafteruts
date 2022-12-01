package com.example.pert9.entity

import com.google.gson.annotations.SerializedName

data class MainWeather (
    @SerializedName("temp")val temp: Double,
    @SerializedName("temp_min")val temp_min: Double,
    @SerializedName("temp_max")val temp_max: Double,
    @SerializedName("pressure")val pressure: Double,
    @SerializedName("humidity")val humidity: Double,
        ){
}