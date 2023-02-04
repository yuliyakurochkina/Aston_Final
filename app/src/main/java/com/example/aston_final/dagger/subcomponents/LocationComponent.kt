package com.example.aston_final.dagger.subcomponents

import android.content.Context
import com.example.aston_final.dagger.modules.LocationModule
import com.example.aston_final.view.fragments.LocationFragment
import com.example.aston_final.view.dialogs.LocationFilterDialog
import com.example.aston_final.view.recycler_view.LocationPaginationRecyclerAdapter
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Named

@Subcomponent(modules = [LocationModule::class])
interface LocationComponent {
    fun inject(locationFragment: LocationFragment)

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun fragmentContext(@Named("locationContext") fragmentContext: Context): Builder

        @BindsInstance
        fun locationItemClickListener(itemClickListener: LocationPaginationRecyclerAdapter.LocationViewHolder.ItemClickListener): Builder

        @BindsInstance
        fun applyItemClickListener(applyClickListener: LocationFilterDialog.ApplyClickListener): Builder
        fun build(): LocationComponent
    }
}