package com.example.concatadapter

import androidx.annotation.DrawableRes

interface ItemMultipleType {
    val itemViewType: Int

    fun areItemsTheSame(newItem: ItemMultipleType): Boolean = false
    fun areContentsTheSame(newItem: ItemMultipleType): Boolean = false
}

data class ItemHeader(
    val title: String,
    val enableMore: Boolean,
    override val itemViewType: Int = ITEM_VIEW_TYPE
) : ItemMultipleType {
    override fun areItemsTheSame(newItem: ItemMultipleType): Boolean {
        return true
    }

    companion object {
        const val ITEM_VIEW_TYPE = 0
    }
}

class ItemFooter(
    override val itemViewType: Int = ITEM_VIEW_TYPE
) : ItemMultipleType {
    override fun areItemsTheSame(newItem: ItemMultipleType): Boolean {
        return true
    }

    companion object {
        const val ITEM_VIEW_TYPE = 1
    }
}

class ItemLoading(override val itemViewType: Int = ITEM_VIEW_TYPE) : ItemMultipleType {
    override fun areItemsTheSame(newItem: ItemMultipleType): Boolean {
        return true
    }

    companion object {
        const val ITEM_VIEW_TYPE = 2
    }
}

data class ItemAccount(
    @DrawableRes val icon: Int,
    val name: String,
    val desc: String,
    val availableBalance: Balance = Balance.Loading,
    val currentBalance: Balance = Balance.Loading,
    override val itemViewType: Int = ITEM_VIEW_TYPE
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

    companion object {
        const val ITEM_VIEW_TYPE = 4
    }
}

data class ItemNetPosition(
    val total: String,
    val value: String,
    override val itemViewType: Int = ITEM_VIEW_TYPE
) : ItemMultipleType {
    companion object {
        const val ITEM_VIEW_TYPE = 5
    }
}

data class ItemNetPositionDes(
    val desc: String,
    override val itemViewType: Int = ITEM_VIEW_TYPE
) : ItemMultipleType {
    companion object {
        const val ITEM_VIEW_TYPE = 6
    }
}

class ItemNotification(override val itemViewType: Int = ITEM_VIEW_TYPE) : ItemMultipleType {
    companion object {
        const val ITEM_VIEW_TYPE = 7
    }
}

