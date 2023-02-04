package com.example.aston_final.dagger.modules

import android.content.Context
import com.example.aston_final.view.dialogs.CharacterFilterDialog
import com.example.aston_final.view.recycler_view.CharacterPaginationRecyclerAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class CharacterModule {
    @Provides
    fun provideCharacterPaginationRecyclerAdapter(
        itemClickListener: CharacterPaginationRecyclerAdapter.CharacterViewHolder.ItemClickListener
    ): CharacterPaginationRecyclerAdapter {
        return CharacterPaginationRecyclerAdapter(itemClickListener)
    }

    @Provides
    fun provideCharacterDialogProcessor(
        @Named("characterContext") fragmentContext: Context,
        applyClickListener: CharacterFilterDialog.ApplyClickListener
    ): CharacterFilterDialog {
        return CharacterFilterDialog(fragmentContext, applyClickListener)
    }
}