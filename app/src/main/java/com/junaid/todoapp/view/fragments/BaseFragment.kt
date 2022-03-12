package com.junaid.todoapp.view.fragments


import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.junaid.todoapp.R
import com.junaid.todoapp.databinding.LayoutNoDataFoundBinding
import com.junaid.todoapp.utils.app.showSnackBar
import com.junaid.todoapp.utils.app.showToast
import com.junaid.todoapp.utils.generals.DialogUtils
import com.junaid.todoapp.viewModel.BaseViewModel



abstract class BaseFragment(layoutId: Int) : Fragment(layoutId) {


    private lateinit var progressDialog: AlertDialog
    protected lateinit var parentView: View


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentView = view
        progressDialog= DialogUtils.getProgressDialog(requireContext())
        setupViews()
        attachViewModel()
    }


    abstract fun setupViews()
    open fun attachViewModel() {}



    protected fun showProgressDialog(show: Boolean) {
        if (show) {
            if (!progressDialog.isShowing)
                progressDialog.apply {
                    show()
                }
        } else if (!show) {
            if (progressDialog.isShowing)
                progressDialog.dismiss()
        }
    }

    protected fun setupGeneralViewModel(generalViewModel: BaseViewModel) {
        with(generalViewModel)
        {
            successMessage.observe(viewLifecycleOwner) {
                it.getContentIfNotHandled()?.let {
                    showToast(it)
                }
            }

            progressBar.observe(viewLifecycleOwner) {
                it.getContentIfNotHandled()?.let {
                    showProgressDialog(it)
                }
            }
        }
    }




    fun showErrorDataFound(
        noDataFoundBinding: LayoutNoDataFoundBinding,
        recyclerView: View,
        message: String,
        callApi: (Boolean) -> Unit
    ) {
        recyclerView.visibility=GONE
        noDataFoundBinding.root.visibility= VISIBLE
        noDataFoundBinding.tvNoDataFound.visibility=GONE
        noDataFoundBinding.tvNoDataError.visibility= VISIBLE
        noDataFoundBinding.btnRetry.visibility= VISIBLE
        noDataFoundBinding.tvNoDataError.text = message
        noDataFoundBinding.btnRetry.setOnClickListener {
            noDataFoundBinding.root.visibility=GONE
            recyclerView.visibility= VISIBLE
            callApi.invoke(true)
        }
    }


    fun showNoDataFound(
        noDataFoundBinding: LayoutNoDataFoundBinding,
        itemCount: Int,
        message: String? = null
    ) {
        if (itemCount == 0) {
            noDataFoundBinding.root.visibility= VISIBLE
            noDataFoundBinding.tvNoDataFound.visibility= VISIBLE
            noDataFoundBinding.tvNoDataError.visibility= GONE
            noDataFoundBinding.btnRetry.visibility= GONE
            message?.let {
                noDataFoundBinding.tvNoDataFound.text = it
            }

        } else {
            noDataFoundBinding.tvNoDataFound.text = getString(R.string.no_data_found)
            noDataFoundBinding.root.visibility= GONE

        }
    }



}