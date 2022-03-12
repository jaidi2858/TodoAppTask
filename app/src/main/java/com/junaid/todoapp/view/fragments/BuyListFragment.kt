package com.junaid.todoapp.view.fragments


import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.junaid.todoapp.R
import com.junaid.todoapp.databinding.FragmentBuyListBinding
import com.junaid.todoapp.model.data.responseModels.BuyItem
import com.junaid.todoapp.model.data.responseModels.CallItem
import com.junaid.todoapp.utils.app.showToast
import com.junaid.todoapp.view.adapter.BaseTAdapter
import com.junaid.todoapp.view.adapter.BuyListAdapter
import com.junaid.todoapp.viewModel.BuyListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BuyListFragment : BaseFragment(R.layout.fragment_buy_list) , BaseTAdapter.OnItemClicker {

    lateinit var binding: FragmentBuyListBinding

    private val buyListViewModel: BuyListViewModel by viewModels()
    private val allBuyList: ArrayList<BuyItem> = ArrayList()
    private var buyListAdapter: BuyListAdapter? = null



    override fun setupViews() {
        binding = FragmentBuyListBinding.bind(parentView)
        with(binding)
        {
            clToolBar.tvFragmentTitle.text=getString(R.string.buy_list)
            buyListAdapter = BuyListAdapter(this@BuyListFragment)
            rvBuyList.adapter = buyListAdapter
            clToolBar.ivBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun attachViewModel() {
        with(buyListViewModel) {
            setupGeneralViewModel(this)
            snackBarMessage.observe(viewLifecycleOwner){
                it.getContentIfNotHandled()?.let {
                    showErrorDataFound(binding.clNoDataFound,binding.rvBuyList,it){
                        getBuyList()
                    }
                }
            }
            allBuyListLiveData.observe(viewLifecycleOwner) {
                it.getContentIfNotHandled()?.let {
                    allBuyList.clear()
                    allBuyList.addAll(it)
                    buyListAdapter?.updateItemList(allBuyList)
                    showNoDataFound(binding.clNoDataFound,allBuyList.size,"No buy list found")
                }
            }
            getBuyList()
        }
    }




    override fun onItemClick(position: Int, data: Any?, adapterType: Int) {
        showToast((data as BuyItem).name)
    }

}
