package com.abiramee.codeforceshelper.data.repository

import com.abiramee.codeforceshelper.data.remote.dto.StatusDto

interface SolvedListRepository {
    suspend fun getStatus(): StatusDto;
}