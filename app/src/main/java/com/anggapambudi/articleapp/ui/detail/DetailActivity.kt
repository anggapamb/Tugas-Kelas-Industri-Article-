package com.anggapambudi.articleapp.ui.detail

import android.os.Bundle
import com.anggapambudi.articleapp.R
import com.anggapambudi.articleapp.data.constan.Const
import com.anggapambudi.articleapp.data.model.Article
import com.anggapambudi.articleapp.databinding.ActivityDetailBinding
import com.crocodic.core.base.activity.NoViewModelActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : NoViewModelActivity<ActivityDetailBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutRes(R.layout.activity_detail)

        //set toolbar
        setSupportActionBar(binding.toolbarDetail)

        //get parcelable
        val article: Article? = intent.getParcelableExtra(Const.BUNDLE.ARTICLE)
        //set in data
        binding.data = article

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}