package com.example.aston_final.dagger.modules

import android.content.Context
import com.example.aston_final.view.dialogs.LocationFilterDialog
import com.example.aston_final.view.recycler_view.LocationPaginationRecyclerAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class LocationModule {
    @Provides
    fun provideLocationPaginationRecyclerAdapter(
        itemClickListener: LocationPaginationRecyclerAdapter.LocationViewHolder.ItemClickListener
    ): LocationPaginationRecyclerAdapter {
        return LocationPaginationRecyclerAdapter(itemClickListener)
    }

    @Provides
    fun provideLocationDialogProcessor(
        @Named("locationContext") fragmentContext: Context,
        applyClickListener: LocationFilterDialog.ApplyClickListener
    ): LocationFilterDialog {
        return LocationFilterDialog(fragmentContext, applyClickListener)
    }
}