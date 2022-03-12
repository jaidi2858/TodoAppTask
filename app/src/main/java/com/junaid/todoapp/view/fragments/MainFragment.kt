package com.junaid.todoapp.view.fragments


import android.view.View
import androidx.navigation.fragment.findNavController
import com.junaid.todoapp.R
import com.junaid.todoapp.databinding.FragmentMainBinding


class MainFragment : BaseFragment(R.layout.fragment_main) , View.OnClickListener {

    lateinit var binding: FragmentMainBinding

    override fun setupViews() {
        binding = FragmentMainBinding.bind(parentView)
        with(binding)
        {
            tvCallList.setOnClickListener(this@MainFragment)
            tvBuyList.setOnClickListener(this@MainFragment)
            tvSellList.setOnClickListener(this@MainFragment)
        }
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.tvCallList->{
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToCallListFragment())
            }
            R.id.tvBuyList->{
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToBuyListFragment())
            }
            R.id.tvSellList->{
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToSellListFragment())
            }
        }
    }


}
