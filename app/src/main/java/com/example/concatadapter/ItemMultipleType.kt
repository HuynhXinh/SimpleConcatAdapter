package com.example.concatadapter

import androidx.annotation.DrawableRes

interface ItemMultipleType {
    val itemViewType: Int

    fun areItemsTheSame(newItem: ItemMultipleType): Boolean = false
    fun areContentsTheSame(newItem: ItemMultipleType): Boolean = false
}

class ItemNotification : ItemMultipleType {
    override val itemViewType: Int = 1
}

data class ItemHeader(
    val title: String,
    val enableMore: Boolean,
    override val itemViewType: Int = 0
) : ItemMultipleType {
    override fun areItemsTheSame(newItem: ItemMultipleType): Boolean {
        return true
    }
}

object ItemFooter : ItemMultipleType {
    override val itemViewType: Int = 2

    override fun areItemsTheSame(newItem: ItemMultipleType): Boolean {
        return true
    }
}

data class ItemAccount(
    @DrawableRes val icon: Int,
    val name: String,
    val desc: String,
    val availableBalance: Balance = Balance.Loading,
    val currentBalance: Balance = Balance.Loading,
    override val itemViewType: Int = 1
) : ItemMultipleType {

    override fun areItemsTheSame(newItem: ItemMultipleType): Boolean {
        return (newItem as? ItemAccount)?.let {
            it.name == name
        } ?: false
    }

    override fun areContentsTheSame(newItem: ItemMultipleType): Boolean {
        return (newItem as? ItemAccount)?.let {
            it == this
        } ?: false
    }

    sealed class Balance {
        object Loading : Balance()
        object Error : Balance()
        data class Available(val name: String, val value: String) : Balance()
    }
}

data class ItemNetPosition(
    val total: String,
    val value: String,
    override val itemViewType: Int = 1
) : ItemMultipleType

data class ItemNetPositionDes(
    val desc: String,
    override val itemViewType: Int = 2
) : ItemMultipleType

