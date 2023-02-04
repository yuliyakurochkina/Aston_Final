package com.example.aston_final.model.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.aston_final.model.database.ItemsDatabase
import com.example.aston_final.model.mediator.LocationRemoteMediator
import com.example.aston_final.model.dto.LocationForListDto
import com.example.aston_final.model.retrofit.RetrofitServices
import kotlinx.coroutines.flow.Flow

class LocationRepository(
    private val mService: RetrofitServices,
    private val database: ItemsDatabase
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getLocationsFromMediator(queryData: MutableList<String>): Flow<PagingData<LocationForListDto>> {
        return Pager(PagingConfig(pageSize = 20, maxSize = 60),
            remoteMediator = LocationRemoteMediator(mService, database, queryData),
            pagingSourceFactory = {
                database.getLocationDao()
                    .getSeveralForFilter(queryData[0], queryData[1], queryData[2])
            }).flow
    }
}