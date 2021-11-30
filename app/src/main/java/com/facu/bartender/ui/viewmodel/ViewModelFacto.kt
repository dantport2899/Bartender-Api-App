package com.facu.bartender.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.facu.bartender.domain.Repo
//instancia el viewmodel
class ViewModelFacto(private val repo: Repo):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Repo::class.java).newInstance(repo)
    }
}