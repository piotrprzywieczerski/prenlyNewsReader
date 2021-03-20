package com.prenly.newsreader.articlelist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.prenly.newsreader.ArticleListActivity
import com.prenly.newsreader.ItemDetailActivity
import com.prenly.newsreader.ItemDetailFragment
import com.prenly.newsreader.R
import com.prenly.newsreader.dummy.DummyContent

class ArticleListAdapter(
    private val parentActivity: ArticleListActivity,
    private val twoPane: Boolean
) :
    RecyclerView.Adapter<ArticleListAdapter.ViewHolder>() {

    private var items: List<DummyContent.DummyItem> = emptyList()
    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as DummyContent.DummyItem
            if (twoPane) {
                val fragment = ItemDetailFragment().apply {
                    arguments = Bundle().apply {
                        putString(ItemDetailFragment.ARG_ITEM_ID, item.id)
                    }
                }
                parentActivity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit()
            } else {
                val intent = Intent(
                    v.context,
                    ItemDetailActivity::class.java
                ).apply {
                    putExtra(ItemDetailFragment.ARG_ITEM_ID, item.id)
                }
                v.context.startActivity(intent)
            }
        }
    }

    fun setItems(values: List<DummyContent.DummyItem>) {
        this.items = values

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.idView.text = item.id
        holder.contentView.text = item.content

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.id_text)
        val contentView: TextView = view.findViewById(R.id.content)
    }
}