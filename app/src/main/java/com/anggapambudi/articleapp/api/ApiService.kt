package com.anggapambudi.articleapp.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("webs/list-article")
    fun listArticle(
        @Query("page") page: Int?
    ): Single<String>

}