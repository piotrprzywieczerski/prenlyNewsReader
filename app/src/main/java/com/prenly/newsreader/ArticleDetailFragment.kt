package com.prenly.newsreader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.prenly.newsreader.articlelist.ArticleListViewModel
import com.prenly.newsreader.databinding.FragmentArticleDetailBinding
import com.prenly.newsreader.domain.model.Article

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ArticleListActivity]
 * in two-pane mode (on tablets) or a [ArticleDetailActivity]
 * on handsets.
 */
class ArticleDetailFragment : Fragment() {

    private var articleId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                articleId = it.getString(ARG_ITEM_ID)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            FragmentArticleDetailBinding.inflate(LayoutInflater.from(context), container, false)

        articleId?.let {
            val articleListViewModel: ArticleListViewModel by viewModels {
                ViewModelFactory(
                    activity?.application as NewsReaderApp
                )
            }
            articleListViewModel.article(it).observe(this as LifecycleOwner,
                Observer<Article> { article ->
                    binding.article = article
                }
            )
        }

        return binding.root
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }
}