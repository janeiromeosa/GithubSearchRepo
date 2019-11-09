package com.example.syftchallenge.model


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("has_pages")
    val hasPages: Boolean,
    @SerializedName("description")
    val description: Any

)