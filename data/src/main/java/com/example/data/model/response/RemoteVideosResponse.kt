package com.example.data.model.response

import com.example.domain.model.Video
import com.google.gson.annotations.SerializedName

data class RemoteVideosResponse(
    @SerializedName("results") var results: List<Video>? = null
)