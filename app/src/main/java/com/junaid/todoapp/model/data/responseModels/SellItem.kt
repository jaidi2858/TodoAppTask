package com.junaid.todoapp.model.data.responseModels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "sellItem")
data class SellItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Long = 0,

    @ColumnInfo(name = "name")
    val name: String="",

    @ColumnInfo(name = "quantity")
    val quantity: Int=-1,

    @ColumnInfo(name = "price")
    val price: Double=0.0,

    @ColumnInfo(name = "type")
    val type: Int=1
):Serializable