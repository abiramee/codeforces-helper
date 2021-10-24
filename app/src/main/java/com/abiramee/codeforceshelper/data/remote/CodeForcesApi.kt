package com.abiramee.codeforceshelper.data.remote.dto

import retrofit2.http.GET

interface CodeForcesApi {
    @GET("user.status?handle=abiramee")
    suspend fun getStatus() : StatusDto
}