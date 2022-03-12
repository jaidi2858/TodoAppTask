package com.junaid.todoapp.model.source.dataRepository



import com.junaid.todoapp.model.data.responseModels.BuyItem
import com.junaid.todoapp.model.data.responseModels.CallItem
import com.junaid.todoapp.model.source.networkConnection.ApiInterface
import com.junaid.todoapp.utils.app.safeApiCall
import com.junaid.todoapp.utils.generals.ResultWrapper
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


class DataRepository @Inject constructor(private val apiService: ApiInterface) {


    private val dispatcher = Dispatchers.IO

    // ====================================== REGISTRATION APIS =================================

    suspend fun getCallsList(): ResultWrapper<ArrayList<CallItem>> {
        return safeApiCall(dispatcher) {
            apiService.getCallsList()
        }
    }

    suspend fun getBuyList(): ResultWrapper<ArrayList<BuyItem>> {
        return safeApiCall(dispatcher) {
            apiService.getBuyList()
        }
    }



}

