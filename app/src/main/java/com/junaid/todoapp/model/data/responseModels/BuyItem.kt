package com.junaid.todoapp.model.data.responseModels

import java.io.Serializable

data class BuyItem(
    val id: Int,
    val name: String,
    val price: Int,
    val quantity: Int,
    val type: Int
):Serializable