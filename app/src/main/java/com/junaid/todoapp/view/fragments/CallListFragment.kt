package com.junaid.todoapp.view.fragments


import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.junaid.todoapp.R
import com.junaid.todoapp.databinding.FragmentCallListBinding
import com.junaid.todoapp.model.data.responseModels.CallItem
import com.junaid.todoapp.utils.app.showToast
import com.junaid.todoapp.view.adapter.BaseTAdapter
import com.junaid.todoapp.view.adapter.CallListAdapter
import com.junaid.todoapp.viewModel.CallListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CallListFragment : BaseFragment(R.layout.fragment_call_list) , BaseTAdapter.OnItemClicker {

    lateinit var binding: FragmentCallListBinding

    private val callListViewModel: CallListViewModel by viewModels()
    private val allCallsList: ArrayList<CallItem> = ArrayList()
    private var callsListAdapter: CallListAdapter? = null



    override fun setupViews() {
        binding = FragmentCallListBinding.bind(parentView)
        with(binding)
        {
            clToolBar.tvFragmentTitle.text=getString(R.string.call_list)
            callsListAdapter = CallListAdapter(this@CallListFragment)
            rvCallsList.adapter = callsListAdapter
            clToolBar.ivBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun attachViewModel() {
        with(callListViewModel) {
            setupGeneralViewModel(this)
            snackBarMessage.observe(viewLifecycleOwner){
                it.getContentIfNotHandled()?.let {
                    showErrorDataFound(binding.clNoDataFound,binding.rvCallsList,it){
                        getCallsList()
                    }
                }
            }
            allCallsListLiveData.observe(viewLifecycleOwner) {
                it.getContentIfNotHandled()?.let {
                    allCallsList.clear()
                    allCallsList.addAll(it)
                    callsListAdapter?.updateItemList(allCallsList)
                    showNoDataFound(binding.clNoDataFound,allCallsList.size,"No call list found")
                }
            }
            getCallsList()
        }
    }




    override fun onItemClick(position: Int, data: Any?, adapterType: Int) {
        showToast((data as CallItem).name)
    }

}
