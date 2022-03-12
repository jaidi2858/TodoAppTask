package com.junaid.todoapp.utils.generals



import com.junaid.todoapp.model.data.utilityModels.ApiErrorResponse
import org.json.JSONObject
object ErrorUtils {
    fun parseError(apiError: String,t: Throwable): ApiErrorResponse {
        return try {
            val json = JSONObject(apiError)
            var error = ApiErrorResponse(
                json.optInt("code", 0),
                json.optString("message", ""))
            error
        }catch (ex: Exception){
            ex.printStackTrace()
            parseError(t)
        }
    }



    fun parseError(t: Throwable): ApiErrorResponse {
        try {
            return ApiErrorResponse(
                0,
                t.message!!
            )
        }catch (ex: Exception){
            ex.printStackTrace()
            return ApiErrorResponse(
                0,
                ""
            )
        }
    }


}