package com.junaid.todoapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.junaid.todoapp.model.data.responseModels.BuyItem
import com.junaid.todoapp.model.data.responseModels.CallItem
import com.junaid.todoapp.model.source.dataRepository.DataRepository
import com.junaid.todoapp.utils.generals.OneShotEvent
import com.junaid.todoapp.utils.generals.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BuyListViewModel @Inject constructor(private val dataRepository: DataRepository) :
    BaseViewModel() {

    var allBuyListLiveData: MutableLiveData<OneShotEvent<ArrayList<BuyItem>>> = MutableLiveData()

    fun getBuyList() {
        showProgressBar(true)
        viewModelScope.launch {
            dataRepository.getBuyList()?.let { response ->
                showProgressBar(false)
                when (response) {
                    is ResultWrapper.Success ->
                        allBuyListLiveData.value = OneShotEvent(response.value)
                    else -> handleErrorType(response)
                }
            }
        }

    }

}