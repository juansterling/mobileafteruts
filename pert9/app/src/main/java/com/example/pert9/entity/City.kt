package com.example.pert9.entity

import com.google.gson.annotations.SerializedName

data class City (
    @SerializedName("id") val id: Int,
    @SerializedName("name")val name: String,
    @SerializedName("coord")val coordinate: Coordinate

        ) {
}