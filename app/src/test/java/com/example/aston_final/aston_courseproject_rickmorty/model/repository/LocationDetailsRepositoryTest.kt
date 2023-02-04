package com.example.aston_final.aston_courseproject_rickmorty.model.repository

import com.example.aston_final.aston_courseproject_rickmorty.MainCoroutineRule
import com.example.aston_final.model.database.ItemsDatabase
import com.example.aston_final.model.repository.LocationDetailsRepository
import com.example.aston_final.model.retrofit.RetrofitServices
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.Before
import org.junit.Rule
import org.mockito.Mockito

class LocationDetailsRepositoryTest {
    private var mockRetrofitServices: RetrofitServices = mock()
    private var mockItemsDatabase: ItemsDatabase = mock()
    lateinit var repository: LocationDetailsRepository

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutinesRule = MainCoroutineRule()
    @ExperimentalCoroutinesApi
    val testDispatcher = StandardTestDispatcher()
    @Before
    fun setUp() {
        Mockito.reset(mockRetrofitServices)
        Mockito.reset(mockItemsDatabase)
    }
}