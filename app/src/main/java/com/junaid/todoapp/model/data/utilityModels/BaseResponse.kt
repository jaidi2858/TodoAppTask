package com.junaid.todoapp.model.data.utilityModels

import java.io.Serializable

open class BaseResponse(
    val status_code: Int = 0,
    var message: String = "",
    var request_body: Any ?= null,
) : Serializable

