package com.facu.bartender.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.facu.bartender.domain.RepoRandom
//instancia el viewmodel
class ViewModelFactoRandom(private val repo: RepoRandom): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(RepoRandom::class.java).newInstance(repo)
    }
}