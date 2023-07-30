package com.mnashat_dev.hadithsharif.repository

import com.google.firebase.database.*
import com.mnashat_dev.hadithsharif.data.localy.ItemDatabase
import com.mnashat_dev.hadithsharif.data.models.ItemForFirebase
import com.mnashat_dev.hadithsharif.data.models.asdomainModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ItemRepository(private val database: ItemDatabase) {

    val applicationScope = CoroutineScope(Dispatchers.Default)

    private var databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("Items")

    var listOfItem = arrayListOf<ItemForFirebase>()

    val items = database.itemDao.getAllItems()
    val item = database.itemDao.getItem()

    suspend fun refreshData() {
        withContext(Dispatchers.IO) {
            getDataFromFirebase()
        }
    }

    private fun getDataFromFirebase() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listOfItem.clear()
                if (snapshot.exists()) {
                    for (snapItem in snapshot.children) {
                        val itemFoFirebase = snapItem.getValue(ItemForFirebase::class.java)
                        listOfItem.add(itemFoFirebase!!)
                    }
                    applicationScope.launch {
                        database.itemDao.upSert(*listOfItem.asdomainModel().toTypedArray())
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }


}