package com.junaid.todoapp.view.fragments


import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.junaid.todoapp.R
import com.junaid.todoapp.databinding.FragmentBuyListBinding
import com.junaid.todoapp.model.data.responseModels.CallItem
import com.junaid.todoapp.model.data.responseModels.SellItem
import com.junaid.todoapp.utils.app.showToast
import com.junaid.todoapp.utils.generals.AppConstants
import com.junaid.todoapp.view.adapter.BaseTAdapter
import com.junaid.todoapp.view.adapter.SellListAdapter
import com.junaid.todoapp.viewModel.SellListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SellListFragment : BaseFragment(R.layout.fragment_buy_list), BaseTAdapter.OnItemClicker {

    lateinit var binding: FragmentBuyListBinding

    private val sellListViewModel: SellListViewModel by viewModels()
    private val allSellList: ArrayList<SellItem> = ArrayList()
    private var sellListAdapter: SellListAdapter? = null


    override fun setupViews() {
        binding = FragmentBuyListBinding.bind(parentView)
        with(binding)
        {
            clToolBar.tvFragmentTitle.text = getString(R.string.sell_list)
            sellListAdapter = SellListAdapter(this@SellListFragment)
            rvBuyList.adapter = sellListAdapter
            clToolBar.ivBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun attachViewModel() {
        with(sellListViewModel) {
            allSellingListLiveData.observe(viewLifecycleOwner) {
                allSellList.clear()
                allSellList.addAll(it)
                sellListAdapter?.updateItemList(allSellList)
                if (allSellList.isNullOrEmpty()) {
                    insertDummyRecords()
                }
            }
            recordInsertLiveData.observe(viewLifecycleOwner) {
                getSellingList()
            }
            getSellingList()
        }
    }

    private fun insertDummyRecords() {
        sellListViewModel.insertSellingList(AppConstants.getDummySellList())
    }


    override fun onItemClick(position: Int, data: Any?, adapterType: Int) {
        showToast((data as SellItem).name)
    }

}
