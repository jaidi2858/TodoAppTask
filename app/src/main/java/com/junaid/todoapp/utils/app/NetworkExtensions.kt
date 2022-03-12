package com.junaid.todoapp.utils.app

import android.content.Context
import android.net.ConnectivityManager
import com.junaid.todoapp.model.data.utilityModels.ApiErrorResponse
import com.junaid.todoapp.utils.generals.ErrorUtils
import com.junaid.todoapp.utils.generals.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResultWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> ResultWrapper.NetworkError
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)
                    ResultWrapper.GenericError(code, errorResponse)
                }
                else -> {
                    ResultWrapper.GenericError(0, ApiErrorResponse(0, "" + throwable.message))
                }
            }
        }
    }
}


public fun convertErrorBody(throwable: HttpException): ApiErrorResponse? {
    return try {
        throwable.response()?.errorBody()?.let {
            return ErrorUtils.parseError(it.string(), throwable)
        }
    } catch (exception: Exception) {
        ApiErrorResponse(throwable.code(), throwable.message())
    }
}

