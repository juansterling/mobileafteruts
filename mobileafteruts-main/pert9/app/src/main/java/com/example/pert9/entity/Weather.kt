package com.example.pert9.entity

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("id")val id: Int,
    @SerializedName("main")val main: String,
    @SerializedName("description")val descrition: String
) {
}