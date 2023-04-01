package com.example.concatadapter.adapter

import android.view.View
import android.widget.TextView
import com.example.concatadapter.ItemHeader
import com.example.concatadapter.R

class HeaderViewHolder(itemView: View) : BaseDifferentAdapter.BaseViewHolder<ItemHeader>(itemView) {
    private val titleTextView: TextView = itemView.findViewById(R.id.title)

    override fun bind(data: ItemHeader, position: Int) {
        titleTextView.text = data.title
    }
}