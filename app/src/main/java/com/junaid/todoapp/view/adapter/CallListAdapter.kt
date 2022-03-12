package com.junaid.todoapp.view.adapter

import android.view.View
import com.junaid.todoapp.R
import com.junaid.todoapp.databinding.RvCallItemBinding
import com.junaid.todoapp.model.data.responseModels.CallItem

class CallListAdapter(
    private val itemClicker: OnItemClicker
) : BaseTAdapter<CallItem>(itemClicker, R.layout.rv_call_item) {

    override fun View.bind(item: CallItem, position: Int) {
        val binding = RvCallItemBinding.bind(this)
        with(binding)
        {
            binding.tvUserName.text= "Name: ${item.name}"
            binding.tvUserNumber.text= "Number : ${item.number}"
        }
    }

}