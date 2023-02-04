package com.example.aston_final.dagger.subcomponents

import com.example.aston_final.dagger.modules.EpisodeDetailsModule
import com.example.aston_final.view.fragments.EpisodeDetailsFragment
import com.example.aston_final.view.recycler_view.CharacterRecyclerAdapter
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [EpisodeDetailsModule::class])
interface EpisodeDetailsComponent {
    fun inject(episodeDetailsFragment: EpisodeDetailsFragment)

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun episodeId(episodeId: Int): Builder

        @BindsInstance
        fun characterItemClickListener(itemClickListener: CharacterRecyclerAdapter.CharacterViewHolder.ItemClickListener): Builder
        fun build(): EpisodeDetailsComponent
    }
}