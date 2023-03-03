package com.seokee.datecourse.view.main.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.seokee.datecourse.util.serverdb.Repository

class MenuMapViewModelFactory(
    private val repository: Repository,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MenuMapViewModel(repository) as T
    }
}
