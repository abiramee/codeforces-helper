package com.abiramee.codeforceshelper.domain.use_case

import com.abiramee.codeforceshelper.common.Resource
import com.abiramee.codeforceshelper.domain.model.CheckUser
import com.abiramee.codeforceshelper.domain.repository.CheckUserRepositoryImp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CheckUserUseCase @Inject constructor(private val repository: CheckUserRepositoryImp){
    operator fun invoke(userName: String) : Flow<Resource<CheckUser>> = flow {
        try {
            emit(Resource.Loading<CheckUser>())
            val status = repository.checkUser(userName).toCheckUser()
            emit(Resource.Success<CheckUser>(status))
        } catch (e: HttpException) {
            if (e.code() == 400) {
                emit(Resource.Success<CheckUser>(CheckUser(false)))
            } else {
                emit(Resource.Error<CheckUser>(message = "An unexpected Error occurred! " + e.code()))
            }
        } catch (e: IOException) {
            emit(Resource.Error<CheckUser>(message = "Check your internet connection!"))
        }
    }
}