package com.example.concatadapter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import com.example.concatadapter.ItemLoading
import com.example.concatadapter.ItemMultipleType
import com.example.concatadapter.ItemNotification
import com.example.concatadapter.R
import com.example.concatadapter.adapter.BaseDifferentAdapter.*

class NotificationAdapter :
    BaseDifferentAdapter<ItemMultipleType, BaseViewHolder<ItemMultipleType>>(config) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ItemMultipleType> {
        return when (viewType) {
            ItemLoading.ITEM_VIEW_TYPE -> LoadingViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_loading, parent, false)
            )
            ItemNotification.ITEM_VIEW_TYPE -> NotificationViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_notification, parent, false)
            )
            else -> {
                error("Doesn't support viewType: $viewType")
            }
        } as BaseViewHolder<ItemMultipleType>
    }

    inner class NotificationViewHolder(itemView: View) :
        BaseViewHolder<ItemNotification>(itemView) {
        override fun bind(data: ItemNotification, position: Int) {

        }
    }

    companion object {
        private val config = AsyncDifferConfig.Builder(
            object : DiffUtil.ItemCallback<ItemMultipleType>() {
                override fun areItemsTheSame(
                    oldItem: ItemMultipleType,
                    newItem: ItemMultipleType
                ): Boolean {
                    return oldItem.areItemsTheSame(newItem)
                }

                override fun areContentsTheSame(
                    oldItem: ItemMultipleType,
                    newItem: ItemMultipleType
                ): Boolean {
                    return oldItem.areContentsTheSame(newItem)
                }
            }
        ).build()
    }
}