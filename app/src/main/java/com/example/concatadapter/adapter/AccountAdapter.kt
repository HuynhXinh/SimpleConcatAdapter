package com.example.concatadapter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import com.example.concatadapter.*
import com.example.concatadapter.adapter.BaseDifferentAdapter.BaseViewHolder

class AccountAdapter :
    BaseDifferentAdapter<ItemMultipleType, BaseViewHolder<ItemMultipleType>>(config) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ItemMultipleType> {
        return when (viewType) {
            ItemHeader.ITEM_VIEW_TYPE -> HeaderViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_header, parent, false)
            )

            ItemAccount.ITEM_VIEW_TYPE -> AccountViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_account, parent, false)
            )

            ItemFooter.ITEM_VIEW_TYPE -> FooterViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_footer, parent, false)
            )

            ItemLoading.ITEM_VIEW_TYPE -> LoadingViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_loading, parent, false)
            )

            else -> {
                error("Doesn't support viewType: $viewType")
            }
        } as BaseViewHolder<ItemMultipleType>
    }

    inner class AccountViewHolder(itemView: View) : BaseViewHolder<ItemAccount>(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.title)
        private val descTextView: TextView = itemView.findViewById(R.id.description)
        private val balanceTextView: TextView = itemView.findViewById(R.id.balance)

        override fun bind(data: ItemAccount, position: Int) {
            titleTextView.text = data.name
            descTextView.text = data.desc
            balanceTextView.text = getBalance(data.availableBalance)
        }

        private fun getBalance(availableBalance: ItemAccount.Balance): String {
            return when (availableBalance) {
                is ItemAccount.Balance.Available -> "${availableBalance.name} - ${availableBalance.value}"
                ItemAccount.Balance.Error -> "Fail to get balance"
                ItemAccount.Balance.Loading -> "Loading...."
            }
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
