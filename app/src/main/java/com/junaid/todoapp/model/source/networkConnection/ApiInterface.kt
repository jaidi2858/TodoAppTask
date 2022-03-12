package com.junaid.todoapp.model.source.networkConnection


import com.junaid.todoapp.model.data.responseModels.BuyItem
import com.junaid.todoapp.model.data.responseModels.CallItem
import com.junaid.todoapp.utils.generals.AppConstants.BUY_LIST_API_URL
import com.junaid.todoapp.utils.generals.AppConstants.CALL_LIST_API_URL
import retrofit2.http.*

interface ApiInterface {

    @GET(CALL_LIST_API_URL)
    suspend fun getCallsList(): ArrayList<CallItem>

    @GET(BUY_LIST_API_URL)
    suspend fun getBuyList(): ArrayList<BuyItem>

}