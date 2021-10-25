package com.abiramee.codeforceshelper.domain.repository

import com.abiramee.codeforceshelper.data.remote.dto.CheckUserDto
import com.abiramee.codeforceshelper.data.remote.dto.CodeForcesApi
import com.abiramee.codeforceshelper.data.repository.CheckUserRepository
import javax.inject.Inject

class CheckUserRepositoryImp @Inject constructor(private val api: CodeForcesApi): CheckUserRepository{
    override suspend fun checkUser(username: String): CheckUserDto {
        return api.checkUser(username)
    }
}