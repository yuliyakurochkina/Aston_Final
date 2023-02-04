package com.example.aston_final.dagger.subcomponents

import com.example.aston_final.dagger.modules.LocationDetailsModule
import com.example.aston_final.view.fragments.LocationDetailsFragment
import com.example.aston_final.view.recycler_view.CharacterRecyclerAdapter
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [LocationDetailsModule::class])
interface LocationDetailsComponent {
    fun inject(locationDetailsFragment: LocationDetailsFragment)

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun locationId(locationId: Int): Builder

        @BindsInstance
        fun characterItemClickListener(itemClickListener: CharacterRecyclerAdapter.CharacterViewHolder.ItemClickListener): Builder
        fun build(): LocationDetailsComponent
    }
}