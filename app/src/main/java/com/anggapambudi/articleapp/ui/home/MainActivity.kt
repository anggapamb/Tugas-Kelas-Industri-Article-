package com.anggapambudi.articleapp.ui.home

import android.os.Bundle
import com.anggapambudi.articleapp.R
import com.anggapambudi.articleapp.base.activity.BaseActivity
import com.anggapambudi.articleapp.data.constan.Const
import com.anggapambudi.articleapp.data.model.Article
import com.anggapambudi.articleapp.databinding.ActivityMainBinding
import com.anggapambudi.articleapp.databinding.ItemArticleBinding
import com.anggapambudi.articleapp.ui.detail.DetailActivity
import com.crocodic.core.base.adapter.CoreListAdapter
import com.crocodic.core.base.adapter.CoreListAdapter.Companion.get
import com.crocodic.core.extension.initLoadMore
import com.crocodic.core.extension.openActivity
import com.crocodic.core.helper.list.EndlessScrollListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private var articles : ArrayList<Article?> = ArrayList()
    private var scrollListener: EndlessScrollListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutRes(R.layout.activity_main)

        //get data article
        observe()
        //set in view
        initView()
        //get data page
        getData()

    }

    private fun initView() {

        //create adapter
        binding.rvArticle.adapter =
            CoreListAdapter<ItemArticleBinding, Article>(R.layout.item_article)
                .initItem(articles) { position, data ->  
                    openActivity<DetailActivity>{
                        putExtra(Const.BUNDLE.ARTICLE, data)
                    }
                }

        //cek data
        android.util.Log.d("MainActivity", "CEK_ARTICLES = $articles")

        //add scrolling
        scrollListener = binding.rvArticle.initLoadMore { page ->
            getData(page + 1)
            //add loading
            binding.rvArticle.adapter?.get()?.addNull()
        }

    }

    private fun observe() {

        //get article
        viewModel.articles.observe(this, {
            binding.rvArticle.adapter?.get()?.removeNull()

            if (it.page == 1) {
                articles.clear()
                binding.rvArticle.adapter?.notifyDataSetChanged()
                scrollListener?.resetState()
            }

            if (it.datas.isNotEmpty()) {
                if (binding.rvArticle.adapter?.itemCount == 0) {

                    //cek data
                    android.util.Log.d("MainActivity", "CEK_DATA_OBSERVER = ${it.datas}")

                    articles.addAll(it.datas)
                    binding.rvArticle.adapter?.notifyItemInserted(0)
                } else {
                    articles.addAll(it.datas)
                    binding.rvArticle.adapter?.notifyItemInserted(binding.rvArticle.adapter?.itemCount ?: 1 - 1)
                }
            }

        })
    }

    //get page
    fun getData(page: Int = 1) {

        //add loading
        binding.rvArticle.adapter?.get()?.addNull()

        viewModel.listArticle(page = page)
    }
}