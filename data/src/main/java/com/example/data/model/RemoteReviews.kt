package com.example.data.model

import com.example.domain.model.Review
import com.google.gson.annotations.SerializedName

data class RemoteReviews (
    @SerializedName("id") var id: String? = null,
    @SerializedName("author") var author: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("content") var content: String? = null,
)