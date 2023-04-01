package com.example.concatadapter.adapter

import android.view.View
import com.example.concatadapter.ItemFooter
import com.example.concatadapter.ItemLoading
import com.example.concatadapter.adapter.BaseDifferentAdapter.BaseViewHolder

class LoadingViewHolder(itemView: View) : BaseViewHolder<ItemLoading>(itemView) {

    override fun bind(data: ItemLoading, position: Int) {}
}