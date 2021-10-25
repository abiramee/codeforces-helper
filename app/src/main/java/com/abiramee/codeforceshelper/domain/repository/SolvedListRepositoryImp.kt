package com.abiramee.codeforceshelper.domain.repository

import com.abiramee.codeforceshelper.data.remote.dto.CodeForcesApi
import com.abiramee.codeforceshelper.data.remote.dto.StatusDto
import com.abiramee.codeforceshelper.data.repository.SolvedListRepository
import javax.inject.Inject

class SolvedListRepositoryImp @Inject constructor(private val api: CodeForcesApi): SolvedListRepository {
    override suspend fun getStatus(username: String): StatusDto {
        return api.getStatus(username)
    }
}