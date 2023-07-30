package com.mnashat_dev.hadithsharif.di

import androidx.room.Room
import com.mnashat_dev.hadithsharif.data.localy.ItemDatabase
import com.mnashat_dev.hadithsharif.ui.allhadith.AllHadithViewModel
import com.mnashat_dev.hadithsharif.ui.displayhadith.DisplayHadithFragment
import com.mnashat_dev.hadithsharif.ui.displayhadith.DisplayHadithViewModel
import com.mnashat_dev.hadithsharif.ui.home_fragment.HomeFragmentViewModel
import com.mnashat_dev.hadithsharif.ui.serch.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    viewModel{HomeFragmentViewModel(get())}
    viewModel{AllHadithViewModel(get())}
    viewModel{SearchViewModel(get())}
    viewModel{DisplayHadithViewModel(get())}



    single {
        Room.databaseBuilder(
            get(),
            ItemDatabase::class.java,
            "RUNNING_DATABASE_NAME"
        ).build()
    }

    single{
        val database = get<ItemDatabase>()
        database.itemFavoriteDao
    }

    single{
        val database = get<ItemDatabase>()
        database.itemDao
    }


}