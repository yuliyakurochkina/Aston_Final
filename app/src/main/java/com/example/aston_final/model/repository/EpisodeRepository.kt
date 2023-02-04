package com.example.aston_final.model.repository

import androidx.paging.*
import com.example.aston_final.model.mediator.EpisodeRemoteMediator
import com.example.aston_final.model.database.ItemsDatabase
import com.example.aston_final.model.dto.EpisodeForListDto
import com.example.aston_final.model.retrofit.RetrofitServices
import kotlinx.coroutines.flow.Flow

class EpisodeRepository(
    private val mService: RetrofitServices,
    private val database: ItemsDatabase
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getEpisodesFromMediator(queryData: MutableList<String>): Flow<PagingData<EpisodeForListDto>> {
        return Pager(PagingConfig(pageSize = 20, maxSize = 60),
            remoteMediator = EpisodeRemoteMediator(
                mService, database,
                queryData
            ),
            pagingSourceFactory = {
                database.getEpisodeDao().getSeveralForFilter(queryData[0], queryData[1])
            }).flow
    }
}