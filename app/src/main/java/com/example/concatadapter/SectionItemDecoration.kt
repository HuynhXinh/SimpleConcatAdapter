package com.example.concatadapter

import android.graphics.*
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class SectionItemDecoration(private val radius: Float, private val margin: Float) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)

        if (position == RecyclerView.NO_POSITION) return

        // Check if this is the first item in a section
        if (isFirstInSection(position, parent)) {
            outRect.top = radius.toInt()
        }

        // Check if this is the last item in a section
        if (isLastInSection(position, parent)) {
            outRect.bottom = radius.toInt() + margin.toInt()
        }

        // Add left and right padding to create the round corners effect
        outRect.left = radius.toInt()
        outRect.right = radius.toInt()
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(child)

            if (position == RecyclerView.NO_POSITION) continue

            val isFirst = isFirstInSection(position, parent)
            val isLast = isLastInSection(position, parent)

            val backgroundResId = when {
                isFirst && isLast -> R.drawable.rounded_top_bottom_background
                isFirst -> R.drawable.rounded_top_background
                isLast -> R.drawable.rounded_bottom_background
                else -> R.drawable.rounded_background
            }

            val backgroundDrawable = ContextCompat.getDrawable(parent.context, backgroundResId)
            child.background = backgroundDrawable
        }
    }

    private fun isFirstInSection(position: Int, parent: RecyclerView): Boolean {
        if (position == 0) return true

        val adapter = parent.adapter ?: return false

        val prevItem = adapter.getItemViewType(position - 1)
        val curItem = adapter.getItemViewType(position)

        return prevItem != curItem
    }

    private fun isLastInSection(position: Int, parent: RecyclerView): Boolean {
        val adapter = parent.adapter ?: return false
        val itemCount = adapter.itemCount

        if (position == itemCount - 1) return true

        val nextItem = adapter.getItemViewType(position + 1)
        val curItem = adapter.getItemViewType(position)

        return nextItem != curItem
    }
}