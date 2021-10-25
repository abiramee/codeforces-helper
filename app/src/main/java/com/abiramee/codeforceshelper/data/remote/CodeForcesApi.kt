package com.abiramee.codeforceshelper.data.remote.dto

import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CodeForcesApi {
    @GET("user.status")
    suspend fun getStatus(@Query("handle") userName: String) : StatusDto

    @GET("user.status")
    suspend fun checkUser(@Query("handle") username: String) : CheckUserDto
}