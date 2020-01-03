package com.example.recipeapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Equipment (
    @SerializedName("name")var name: String,
    @SerializedName("image")var image: String
) : Parcelable