package com.anggapambudi.articleapp.base.viewmodel

import androidx.lifecycle.viewModelScope
import com.anggapambudi.articleapp.api.ApiService
import com.crocodic.core.base.viewmodel.CoreViewModel
import kotlinx.coroutines.launch

open class BaseViewModel(private val apiService: ApiService) : CoreViewModel() {

    override fun apiLogout() = viewModelScope.launch { }

    override fun apiRenewToken() = viewModelScope.launch { }

}