package com.prenly.newsreader

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Piotr Przywieczerski on 20.03.2021.
 */
class ArticleListItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val margin =
            parent.context.resources.getDimensionPixelSize(R.dimen.articles_list_item_margins)
        val position = parent.getChildLayoutPosition(view);

        outRect.right = margin
        outRect.left = margin
        outRect.bottom = margin

        if (position == 0) {
            outRect.top = margin
        }
    }
}