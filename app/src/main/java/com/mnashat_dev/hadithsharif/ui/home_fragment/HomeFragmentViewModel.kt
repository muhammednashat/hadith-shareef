package com.mnashat_dev.hadithsharif.ui.home_fragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mnashat_dev.hadithsharif.data.localy.getDatabase
import com.mnashat_dev.hadithsharif.repository.ItemRepository
import kotlinx.coroutines.launch

class HomeFragmentViewModel (application: Application): AndroidViewModel(application) {


    private val TAG = "HomeFragmentViewModel"
    private val database = getDatabase(application)
    private val itemRepo = ItemRepository(database)
    var items = itemRepo.items

    init {

        viewModelScope.launch {
            try {
                itemRepo.refreshData()
                Log.e("TAG","success")
            } catch (e: Exception) {
                Log.e(TAG, " Exception ${e.message}")
                Log.e("TAG","fail")

            }
        }
    }


}