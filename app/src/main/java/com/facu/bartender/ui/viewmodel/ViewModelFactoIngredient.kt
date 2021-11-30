package com.facu.bartender.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.facu.bartender.domain.RepoIngredient
//instancia el viewmodel
class ViewModelFactoIngredient(private val repo: RepoIngredient): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(RepoIngredient::class.java).newInstance(repo)
    }
}
