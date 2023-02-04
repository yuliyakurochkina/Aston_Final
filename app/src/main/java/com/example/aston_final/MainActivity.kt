package com.example.aston_final

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.aston_final.view.fragments.CharacterFragment
import com.example.aston_final.view.fragments.EpisodeFragment
import com.example.aston_final.view.fragments.LocationFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var vmFactory: MainViewModelFactory
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Aston_Final)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this, vmFactory)[MainViewModel::class.java]
        viewModel.titleString.observe(this) {
            title = it
        }
        viewModel.showToolbar.observe(this) {
            this.supportActionBar?.setDisplayHomeAsUpEnabled(it)
        }
        viewModel.currentFragment.observe(this) {
            val fragments = supportFragmentManager.fragments
            for (fragment in fragments) {
                supportFragmentManager.beginTransaction().remove(fragment).commit()
            }
            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView, it, "current_main_fragment")
                commit()
            }
        }
        viewModel.currentDetailsFragment.observe(this) {
            val fTrans = supportFragmentManager.beginTransaction()
            supportFragmentManager.findFragmentByTag("current_main_fragment")
                ?.let { fTrans.hide(it) }

            fTrans.apply {
                replace(R.id.fragmentContainerView, it, "current_main_fragment")
                addToBackStack(null)
                commit()
            }
        }
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.ic_character -> {
                    viewModel.changeCurrentFragment(CharacterFragment.newInstance())
                }
                R.id.ic_episode -> {
                    viewModel.changeCurrentFragment(EpisodeFragment.newInstance())
                }
                R.id.ic_location -> {
                    viewModel.changeCurrentFragment(LocationFragment.newInstance())
                }
            }
            true
        }
        bottomNavigation.selectedItemId = R.id.ic_character
    }

    @Override
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                if (onBackPressedDispatcher.hasEnabledCallbacks()) {
                    checkFragment()
                    onBackPressedDispatcher.onBackPressed()
                } else return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun checkFragment() {
        val currentFragment = supportFragmentManager.findFragmentByTag("current_main_fragment")
        if (currentFragment != null) {
            viewModel.checkFragment(currentFragment)
        }
    }
}