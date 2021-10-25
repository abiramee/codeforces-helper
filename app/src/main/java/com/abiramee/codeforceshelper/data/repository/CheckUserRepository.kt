package com.abiramee.codeforceshelper.data.repository

import com.abiramee.codeforceshelper.data.remote.dto.CheckUserDto

interface CheckUserRepository {
    suspend fun checkUser(username: String) : CheckUserDto
}