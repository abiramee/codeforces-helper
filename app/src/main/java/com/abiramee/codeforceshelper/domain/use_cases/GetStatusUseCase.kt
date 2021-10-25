package com.abiramee.codeforceshelper.domain.use_cases

import com.abiramee.codeforceshelper.common.Resource
import com.abiramee.codeforceshelper.domain.model.Status
import com.abiramee.codeforceshelper.domain.repository.SolvedListRepositoryImp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetStatusUseCase @Inject constructor(private val repository: SolvedListRepositoryImp){
    operator fun invoke(username: String) : Flow<Resource<Status>> = flow {
        try {
            emit(Resource.Loading<Status>())
            val status = repository.getStatus(username).toStatus();
            emit(Resource.Success<Status>(status))
        } catch (e: HttpException) {
            emit(Resource.Error<Status>(message = "An unexpected Error occurred!"))
        } catch (e: IOException) {
            emit(Resource.Error<Status>(message = "Check your internet connection!"))
        }
    }
}