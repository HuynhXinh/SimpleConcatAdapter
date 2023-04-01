package com.example.concatadapter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import com.example.concatadapter.ItemNotification
import com.example.concatadapter.R
import com.example.concatadapter.adapter.NotificationAdapter.NotificationViewHolder

class NotificationAdapter
    : BaseDifferentAdapter<ItemNotification, NotificationViewHolder>(config) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        return NotificationViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_notification, parent, false)
        )
    }

    inner class NotificationViewHolder(itemView: View) :
        BaseViewHolder<ItemNotification>(itemView) {
        override fun bind(data: ItemNotification, position: Int) {

        }
    }

    companion object {
        private val config = AsyncDifferConfig.Builder(
            object : DiffUtil.ItemCallback<ItemNotification>() {
                override fun areItemsTheSame(
                    oldItem: ItemNotification,
                    newItem: ItemNotification
                ): Boolean {
                    return oldItem.areItemsTheSame(newItem)
                }

                override fun areContentsTheSame(
                    oldItem: ItemNotification,
                    newItem: ItemNotification
                ): Boolean {
                    return oldItem.areContentsTheSame(newItem)
                }
            }
        ).build()
    }
}