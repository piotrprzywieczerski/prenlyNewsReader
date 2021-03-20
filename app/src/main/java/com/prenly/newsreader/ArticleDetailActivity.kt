package com.prenly.newsreader

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.prenly.newsreader.articlelist.ArticleListViewModel
import com.prenly.newsreader.databinding.ActivityArticleDetailBinding
import com.prenly.newsreader.domain.model.Article
import timber.log.Timber

class ArticleDetailActivity : AppCompatActivity() {

    private val articleListViewModel: ArticleListViewModel by viewModels {
        ViewModelFactory(
            application as NewsReaderApp
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityArticleDetailBinding>(
            this,
            R.layout.activity_article_detail
        )
        binding.lifecycleOwner = this
        setSupportActionBar(findViewById(R.id.detail_toolbar))

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val articleId = intent.getStringExtra(ArticleDetailFragment.ARG_ITEM_ID)
        articleListViewModel.article(articleId!!).observe(this,
            Observer<Article> { article ->
                Timber.d("Piotr setting article with title: ${article.title}")
                binding.article = article
            }
        )
        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don"t need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val fragment = ArticleDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(
                        ArticleDetailFragment.ARG_ITEM_ID,
                        articleId
                    )
                }
            }

            supportFragmentManager.beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back

                navigateUpTo(Intent(this, ArticleListActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}