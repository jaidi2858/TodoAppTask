package com.junaid.todoapp.model.source.room


import androidx.room.Dao
import androidx.room.Query
import com.junaid.todoapp.model.data.responseModels.SellItem

@Dao
interface SellItemsDao : BaseDao<SellItem>{

    @Query("SELECT * from sellItem where type = :type ")
    suspend fun getSellingItems(type:Int): List<SellItem>

}