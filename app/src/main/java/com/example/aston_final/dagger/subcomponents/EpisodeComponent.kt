package com.example.aston_final.dagger.subcomponents

import android.content.Context
import com.example.aston_final.dagger.modules.EpisodeModule
import com.example.aston_final.view.fragments.EpisodeFragment
import com.example.aston_final.view.dialogs.EpisodeFilterDialog
import com.example.aston_final.view.recycler_view.EpisodePaginationRecyclerAdapter
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Named

@Subcomponent(modules = [EpisodeModule::class])
interface EpisodeComponent {
    fun inject(episodeFragment: EpisodeFragment)

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun fragmentContext(@Named("episodeContext") fragmentContext: Context): Builder

        @BindsInstance
        fun episodeItemClickListener(itemClickListener: EpisodePaginationRecyclerAdapter.EpisodeViewHolder.ItemClickListener): Builder

        @BindsInstance
        fun applyItemClickListener(applyClickListener: EpisodeFilterDialog.ApplyClickListener): Builder
        fun build(): EpisodeComponent
    }
}