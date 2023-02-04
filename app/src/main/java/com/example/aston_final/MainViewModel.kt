package com.example.aston_final

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aston_final.utils.ResourceProvider
import com.example.aston_final.view.fragments.*

class MainViewModel(
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    val titleString = MutableLiveData<String>()
    val showToolbar = MutableLiveData<Boolean>()
    val currentFragment = MutableLiveData<Fragment>()
    val currentDetailsFragment = MutableLiveData<Fragment>()

    init {
        titleString.value = resourceProvider.getString("menu_character")
        currentFragment.value = CharacterFragment()
        showToolbar.value = false
    }

    fun changeCurrentFragment(fragment: Fragment) {
        when (fragment) {
            is CharacterFragment -> changeTitle("menu_character")
            is EpisodeFragment -> changeTitle("menu_episode")
            is LocationFragment -> changeTitle("menu_location")
        }
        showToolbar.value = false
        currentFragment.value = fragment
    }

    fun changeCurrentDetailsFragment(fragment: Fragment) {
        when (fragment) {
            is CharacterDetailsFragment -> changeTitle("menu_details_character")
            is EpisodeDetailsFragment -> changeTitle("menu_details_episode")
            is LocationDetailsFragment -> changeTitle("menu_details_location")
        }
        showToolbar.value = true
        currentDetailsFragment.value = fragment
    }

    private fun changeTitle(titleId: String) {
        titleString.value = resourceProvider.getString(titleId)
    }

    fun checkFragment(fragment: Fragment) {
        when (fragment) {
            is CharacterFragment -> {
                showToolbar.value = false
                changeTitle("menu_character")
            }
            is EpisodeFragment -> {
                showToolbar.value = false
                changeTitle("menu_episode")
            }
            is LocationFragment -> {
                showToolbar.value = false
                changeTitle("menu_location")
            }
            is CharacterDetailsFragment -> {
                showToolbar.value = true
                changeTitle("menu_details_character")
            }
            is EpisodeDetailsFragment -> {
                showToolbar.value = true
                changeTitle("menu_details_episode")
            }
            is LocationDetailsFragment -> {
                showToolbar.value = true
                changeTitle("menu_details_location")
            }
        }
    }
}