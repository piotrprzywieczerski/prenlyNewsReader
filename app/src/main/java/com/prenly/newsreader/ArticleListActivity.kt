package com.prenly.newsreader

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.prenly.newsreader.articlelist.ArticleListAdapter
import com.prenly.newsreader.articlelist.ArticleListViewModel
import com.prenly.newsreader.databinding.ActivityArticleListBinding

class ArticleListActivity : AppCompatActivity() {

    private val articleListViewModel: ArticleListViewModel by viewModels {
        ViewModelFactory(
            application as NewsReaderApp
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityArticleListBinding>(
            this,
            R.layout.activity_article_list
        )
        binding.lifecycleOwner = this

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title
        setupRecyclerView(findViewById(R.id.item_list), articleListViewModel)

        articleListViewModel.fetchData()
        binding.viewmodel = articleListViewModel
    }

    private fun setupRecyclerView(
        recyclerView: RecyclerView,
        articleListViewModel: ArticleListViewModel
    ) {
        recyclerView.addItemDecoration(ArticleListItemDecoration())
        recyclerView.adapter =
            ArticleListAdapter(
                articleListViewModel
            )
    }

}