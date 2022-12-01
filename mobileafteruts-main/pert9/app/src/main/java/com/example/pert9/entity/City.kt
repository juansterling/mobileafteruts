package com.example.pert9.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class City (
    @SerializedName("id") val id: Int,
    @SerializedName("name")val name: String,
    @SerializedName("coord")val coordinate: Coordinate

        ): Parcelable