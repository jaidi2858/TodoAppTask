package com.junaid.todoapp.utils.generals

import com.junaid.todoapp.BuildConfig
import com.junaid.todoapp.model.data.responseModels.SellItem


object AppConstants {

    const val BASE_URL = BuildConfig.API_URL
    const val CALL_LIST_API_URL = "call"
    const val BUY_LIST_API_URL = "buy"
    var DATABASE_NAME = "com.todoapp.database"


    fun getDummySellList():List<SellItem> {
        return listOf(SellItem(0,"Table",1,12000.0,2), SellItem(0,"TV",2,38000.0,2),
            SellItem(0,"IPHONE X",1,150000.0,2), SellItem(0,"PIXEL 3",2,160000.0,2))
    }


}