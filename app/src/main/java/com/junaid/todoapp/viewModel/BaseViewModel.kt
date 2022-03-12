package com.junaid.todoapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.junaid.todoapp.model.data.utilityModels.BaseResponse
import com.junaid.todoapp.utils.generals.OneShotEvent
import com.junaid.todoapp.utils.generals.ResultWrapper


open class BaseViewModel() : ViewModel() {


    val snackBarMessage = MutableLiveData<OneShotEvent<String>>()
    val successMessage = MutableLiveData<OneShotEvent<String>>()
    val unauthenticatedLiveData = MutableLiveData<OneShotEvent<String>>()
    val errorDataLiveData = MutableLiveData<OneShotEvent<String>>()
    val progressBar = MutableLiveData<OneShotEvent<Boolean>>()


    protected fun showSnackBarMessage(message: String,needToShowError: Boolean) {
        snackBarMessage.value = OneShotEvent(message)
        if(needToShowError){
            showErrorData(message)
        }
    }

    private fun showNetworkError(needToShowError: Boolean) {
        snackBarMessage.value = OneShotEvent("Internet connection problem")
        if(needToShowError){
            showErrorData("Internet connection problem")
        }
    }
    private fun showErrorData(message: String){
        errorDataLiveData.value = OneShotEvent(message)
    }

    protected fun showSuccessMessage(message: String) {
        successMessage.value = OneShotEvent(message)
    }

    protected fun handleErrorType(error: ResultWrapper<Any>,needToShowError:Boolean=false) {
        when (error) {
            is ResultWrapper.NetworkError ->
                showNetworkError(needToShowError)
            is ResultWrapper.GenericError ->
                error?.error?.let {
                    showSnackBarMessage(it.message,needToShowError)
                    if (error.code == 401) {
                        unauthenticatedLiveData.value = OneShotEvent("")
                    }
                }
        }
    }



    protected fun showProgressBar(show: Boolean) {
        progressBar.value = OneShotEvent(show)
    }




}