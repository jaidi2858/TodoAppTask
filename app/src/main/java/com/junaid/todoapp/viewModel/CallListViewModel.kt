package com.junaid.todoapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.junaid.todoapp.model.data.responseModels.CallItem
import com.junaid.todoapp.model.source.dataRepository.DataRepository
import com.junaid.todoapp.utils.generals.OneShotEvent
import com.junaid.todoapp.utils.generals.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CallListViewModel @Inject constructor(private val dataRepository: DataRepository) :
    BaseViewModel() {

    var allCallsListLiveData: MutableLiveData<OneShotEvent<ArrayList<CallItem>>> = MutableLiveData()

    fun getCallsList() {
        showProgressBar(true)
        viewModelScope.launch {
            dataRepository.getCallsList()?.let { response ->
                showProgressBar(false)
                when (response) {
                    is ResultWrapper.Success ->
                        allCallsListLiveData.value = OneShotEvent(response.value)

                    else -> handleErrorType(response)
                }
            }
        }

    }

}