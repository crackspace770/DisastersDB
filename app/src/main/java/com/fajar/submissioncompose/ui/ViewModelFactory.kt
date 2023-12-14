package com.fajar.submissioncompose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fajar.submissioncompose.data.DisasterRepository
import com.fajar.submissioncompose.ui.screen.bookmark.BookmarkViewModel
import com.fajar.submissioncompose.ui.screen.detail.DetailViewModel
import com.fajar.submissioncompose.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: DisasterRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(BookmarkViewModel::class.java)) {
            return BookmarkViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}