package com.example.concatadapter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import com.example.concatadapter.*
import com.example.concatadapter.adapter.BaseDifferentAdapter.BaseViewHolder

class NetPositionAdapter :
    BaseDifferentAdapter<ItemMultipleType, BaseViewHolder<ItemMultipleType>>(config) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ItemMultipleType> {
        return when (viewType) {
            0 -> HeaderViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_header, parent, false)
            )
            1 -> NetPositionViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_net_position, parent, false)
            )
            2 -> NetPositionDesViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_net_position_des, parent, false)
            )
            else -> {
                error("Doesn't support viewType: $viewType")
            }
        } as BaseViewHolder<ItemMultipleType>
    }

    inner class NetPositionViewHolder(itemView: View) : BaseViewHolder<ItemNetPosition>(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.title)
        private val descTextView: TextView = itemView.findViewById(R.id.description)

        override fun bind(data: ItemNetPosition, position: Int) {
            titleTextView.text = data.total
            descTextView.text = data.value
        }
    }

    inner class NetPositionDesViewHolder(itemView: View) : BaseViewHolder<ItemNetPositionDes>(itemView) {
        private val descTextView: TextView = itemView.findViewById(R.id.description)

        override fun bind(data: ItemNetPositionDes, position: Int) {
            descTextView.text = data.desc
        }
    }

    companion object {
        private val config = AsyncDifferConfig.Builder(
            object : DiffUtil.ItemCallback<ItemMultipleType>() {
                override fun areItemsTheSame(
                    oldItem: ItemMultipleType,
                    newItem: ItemMultipleType
                ): Boolean {
                    return false
                }

                override fun areContentsTheSame(
                    oldItem: ItemMultipleType,
                    newItem: ItemMultipleType
                ): Boolean {
                    return false
                }
            }
        ).build()
    }
}
