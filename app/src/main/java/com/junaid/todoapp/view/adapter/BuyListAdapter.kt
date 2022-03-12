package com.junaid.todoapp.view.adapter

import android.view.View
import com.junaid.todoapp.R
import com.junaid.todoapp.databinding.RvBuyItemBinding
import com.junaid.todoapp.model.data.responseModels.BuyItem

class BuyListAdapter(
    private val itemClicker: OnItemClicker
) : BaseTAdapter<BuyItem>(itemClicker, R.layout.rv_buy_item) {

    override fun View.bind(item: BuyItem, position: Int) {
        val binding = RvBuyItemBinding.bind(this)
        with(binding)
        {
            binding.tvProductName.text= "Name: ${item.name}"
            binding.tvProductPrice.text= "Price: ${item.price}"
            binding.tvProductQuantity.text= "Quantity: ${item.quantity}"
        }
    }

}