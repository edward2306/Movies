package com.example.data.model

import com.google.gson.annotations.SerializedName

data class RemoteVideos (
    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("key") var key: String? = null,
    @SerializedName("size") var size: Int? = null
)