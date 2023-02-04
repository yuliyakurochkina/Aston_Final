package com.example.aston_final.dagger.subcomponents

import android.content.Context
import com.example.aston_final.dagger.modules.CharacterModule
import com.example.aston_final.view.fragments.CharacterFragment
import com.example.aston_final.view.dialogs.CharacterFilterDialog
import com.example.aston_final.view.recycler_view.CharacterPaginationRecyclerAdapter
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Named

@Subcomponent(modules = [CharacterModule::class])
interface CharacterComponent {
    fun inject(characterFragment: CharacterFragment)

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun fragmentContext(@Named("characterContext") fragmentContext: Context): Builder

        @BindsInstance
        fun characterItemClickListener(itemClickListener: CharacterPaginationRecyclerAdapter.CharacterViewHolder.ItemClickListener): Builder

        @BindsInstance
        fun applyItemClickListener(applyClickListener: CharacterFilterDialog.ApplyClickListener): Builder
        fun build(): CharacterComponent
    }
}