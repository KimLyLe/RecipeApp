package com.example.recipeapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe (
    @SerializedName("title")var title: String,
    @SerializedName("image")var image: String,
    @SerializedName("id")var id: String
) : Parcelable