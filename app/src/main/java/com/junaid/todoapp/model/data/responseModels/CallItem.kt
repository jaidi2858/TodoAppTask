package com.junaid.todoapp.model.data.responseModels

import java.io.Serializable

data class CallItem(
    val id: Int,
    val name: String,
    val number: String
):Serializable