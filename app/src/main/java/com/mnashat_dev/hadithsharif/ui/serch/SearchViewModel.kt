package com.mnashat_dev.hadithsharif.ui.serch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mnashat_dev.hadithsharif.data.localy.getDatabase
import com.mnashat_dev.hadithsharif.repository.ItemRepository

class SearchViewModel(application: Application) : AndroidViewModel(application) {


    private val TAG = "SearchViewModel"
    private val database = getDatabase(application)
    private val itemRepo = ItemRepository(database)
    var items = itemRepo.items

}