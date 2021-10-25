package com.abiramee.codeforceshelper.data.remote.dto


import com.abiramee.codeforceshelper.domain.model.CheckUser
import com.google.gson.annotations.SerializedName

data class CheckUserDto(
    @SerializedName("result")
    val result: List<Result>,
    @SerializedName("status")
    val status: String,
) {
    fun toCheckUser(): CheckUser {
        return CheckUser(status == "OK")
    }
}