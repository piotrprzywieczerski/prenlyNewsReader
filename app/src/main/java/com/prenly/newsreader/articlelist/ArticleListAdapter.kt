package com.prenly.newsreader.articlelist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.prenly.newsreader.ArticleListActivity
import com.prenly.newsreader.ItemDetailActivity
import com.prenly.newsreader.ItemDetailFragment
import com.prenly.newsreader.R
import com.prenly.newsreader.databinding.ItemListContentBinding
import com.prenly.newsreader.domain.model.Article
import com.prenly.newsreader.dummy.DummyContent

class ArticleListAdapter(
    private val parentActivity: ArticleListActivity,
    private val twoPane: Boolean,
    private val articleListViewModel: ArticleListViewModel
) : ListAdapter<Article, ArticleListAdapter.ViewHolder>(ArticleDiffCallback()) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(articleListViewModel, getItem(position))
    }

    class ViewHolder private constructor(private val binding: ItemListContentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: ArticleListViewModel, item: Article) {

            binding.articleListVM = viewModel
            binding.article = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemListContentBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class ArticleDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}