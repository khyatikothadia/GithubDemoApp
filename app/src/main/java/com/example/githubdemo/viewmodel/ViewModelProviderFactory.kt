package com.example.githubdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * Factory class for providing ViewModels using Dagger's multibinding.
 *
 * @param map A map containing ViewModels associated with their respective class keys.
 */
class ViewModelProviderFactory @Inject constructor(private val map: Map<Class<*>, @JvmSuppressWildcards ViewModel>) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return map[modelClass] as T
    }
}