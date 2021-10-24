package com.abiramee.codeforceshelper.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Member(
    @SerializedName("handle")
    val handle: String
)