package com.junaid.todoapp.model.source.dataRepository

import android.content.Context
import com.junaid.todoapp.model.data.responseModels.SellItem
import com.junaid.todoapp.model.source.room.SellItemsDao
import com.junaid.todoapp.model.source.room.SellingRoomDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class LocalDataRepository @Inject constructor(@ApplicationContext private val context: Context){

    private val sellItemsDao: SellItemsDao = SellingRoomDatabase.getDatabase(context).sellItemsDao()


    suspend fun insertSellingItems(sellItemList: List<SellItem>) {
        return sellItemsDao.insert(sellItemList)
    }

    suspend fun getAllSellingItems() : List<SellItem> {
       return sellItemsDao.getSellingItems(2)
    }


}