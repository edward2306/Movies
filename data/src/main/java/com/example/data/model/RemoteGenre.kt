package com.example.data.model

import com.google.gson.annotations.SerializedName

data class RemoteGenre (
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null
)