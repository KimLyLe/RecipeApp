package com.example.recipeapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Ingredient (
    @SerializedName("name")var name: String,
    @SerializedName("amount")var amount: String,
    @SerializedName("unit")var unit: String
    ) : Parcelable