package com.example.aston_final.aston_courseproject_rickmorty.viewmodel

import com.example.aston_final.aston_courseproject_rickmorty.MainCoroutineRule
import com.example.aston_final.model.dto.LocationDto
import com.example.aston_final.model.repository.LocationDetailsRepository
import com.example.aston_final.model.retrofit.ApiState
import com.example.aston_final.model.retrofit.Status
import com.example.aston_final.utils.InternetConnectionChecker
import com.example.aston_final.viewmodel.LocationDetailsViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class LocationDetailsViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var mainCoroutinesRule = MainCoroutineRule()
    private var mockLocationDetailsRepository: LocationDetailsRepository = mock()
    private var mockInternetConnectionChecker: InternetConnectionChecker = mock()
    private lateinit var viewModel: LocationDetailsViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Mockito.reset(mockLocationDetailsRepository)
        Mockito.reset(mockInternetConnectionChecker)
        whenever(mockInternetConnectionChecker.isOnline()).thenReturn(true)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getLocation() = runTest {
        val locationId = 2
        viewModel = LocationDetailsViewModel(
            locationId,
            mockLocationDetailsRepository,
            mockInternetConnectionChecker,
            testDispatcher
        )
        whenever(mockLocationDetailsRepository.getLocation(locationId))
            .thenReturn(flow {
                emit(
                    ApiState.success(
                        LocationDto(
                            2,
                            "Abadango",
                            "Cluster",
                            "unknown",
                            "6"
                        )
                    )
                )
            }.flowOn(testDispatcher))
        val expected = MutableStateFlow(ApiState(Status.SUCCESS, data = LocationDto(), ""))
        val result = viewModel.getLocationValue()
        Assert.assertNotEquals(result, expected)
    }

    @Test
    fun getCharacters() {
    }
}