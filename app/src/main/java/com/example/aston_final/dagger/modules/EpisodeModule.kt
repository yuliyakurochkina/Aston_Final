package com.example.aston_final.dagger.modules

import android.content.Context
import com.example.aston_final.view.dialogs.EpisodeFilterDialog
import com.example.aston_final.view.recycler_view.EpisodePaginationRecyclerAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class EpisodeModule {

    @Provides
    fun provideEpisodePaginationRecyclerAdapter(
        itemClickListener: EpisodePaginationRecyclerAdapter.EpisodeViewHolder.ItemClickListener
    ): EpisodePaginationRecyclerAdapter {
        return EpisodePaginationRecyclerAdapter(itemClickListener)
    }

    @Provides
    fun provideEpisodeDialogProcessor(
        @Named("episodeContext") fragmentContext: Context,
        applyClickListener: EpisodeFilterDialog.ApplyClickListener
    ): EpisodeFilterDialog {
        return EpisodeFilterDialog(fragmentContext, applyClickListener)
    }
}