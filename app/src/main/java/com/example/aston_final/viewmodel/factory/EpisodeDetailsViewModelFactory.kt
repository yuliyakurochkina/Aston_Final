package com.example.aston_final.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aston_final.model.repository.EpisodeDetailsRepository
import com.example.aston_final.utils.InternetConnectionChecker
import com.example.aston_final.viewmodel.EpisodeDetailsViewModel

class EpisodeDetailsViewModelFactory(
    private val episodeID: Int,
    val repository: EpisodeDetailsRepository,
    private val internetChecker: InternetConnectionChecker
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EpisodeDetailsViewModel(episodeID, repository, internetChecker) as T
    }
}