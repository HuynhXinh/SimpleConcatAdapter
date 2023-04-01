package com.example.concatadapter.adapter

import android.content.Context
import android.view.View
import androidx.annotation.StringRes
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.concatadapter.ItemMultipleType
import com.example.concatadapter.adapter.BaseDifferentAdapter.*

abstract class BaseDifferentAdapter<D : ItemMultipleType, VH : BaseViewHolder<D>>(
    config: AsyncDifferConfig<D>
) : ListAdapter<D, VH>(config) {
    override fun getItemViewType(position: Int): Int {
        return currentList[position].itemViewType
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(currentList[position], position)
    }

    abstract class BaseViewHolder<D>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(data: D, position: Int)

        protected fun getString(@StringRes resId: Int, vararg formatArgs: Any): String {
            return itemView.context.getString(resId, *formatArgs)
        }

        protected fun getContext(): Context {
            return itemView.context
        }
    }
}