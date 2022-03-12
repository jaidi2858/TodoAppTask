package com.junaid.todoapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.junaid.todoapp.model.data.responseModels.SellItem
import com.junaid.todoapp.model.source.dataRepository.LocalDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SellListViewModel @Inject constructor(private val localDataRepository: LocalDataRepository) :
    BaseViewModel() {

    var allSellingListLiveData: MutableLiveData<List<SellItem>> = MutableLiveData()
    var recordInsertLiveData: MutableLiveData<Int> = MutableLiveData()

    fun getSellingList() {
        viewModelScope.launch {
            val allSellingItems = localDataRepository.getAllSellingItems()
            withContext(Dispatchers.Main) {
                allSellingListLiveData.value = allSellingItems
            }
        }
    }


    fun insertSellingList(sellItemList: List<SellItem>) {
        viewModelScope.launch {
            localDataRepository.insertSellingItems(sellItemList)
            withContext(Dispatchers.Main) {
                recordInsertLiveData.value = 1
            }
        }

    }

}