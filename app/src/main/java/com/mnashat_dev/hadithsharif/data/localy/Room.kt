package com.mnashat_dev.hadithsharif.data.localy;

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.mnashat_dev.hadithsharif.data.models.Hadith
import com.mnashat_dev.hadithsharif.data.models.ItemFavorite


@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upSert(vararg hadiths: Hadith)

    @Query("SELECT * FROM ITEM_FIREBASE_TABLE")
    fun getAllItems(): LiveData<List<Hadith>>

    @Query("SELECT * FROM ITEM_FIREBASE_TABLE ORDER BY id DESC LIMIT 1")
    fun getItem(): LiveData<Hadith>

}


@Dao
interface ItemFavoriteDao {

    @Insert()
    suspend fun insert(itemFavorite:ItemFavorite)

    @Query("SELECT * FROM ITEM_FAVORITE_TABLE")
    fun getAllItemsFavorite(): LiveData<List<ItemFavorite>>

    @Delete()
    fun delete(itemFavorite:ItemFavorite)

}

private lateinit var INSTANCE: ItemDatabase

@Database(entities = [Hadith::class,ItemFavorite::class], version = 1, exportSchema = false)
abstract class ItemDatabase() : RoomDatabase() {

    abstract val itemDao: ItemDao
    abstract val itemFavoriteDao: ItemFavoriteDao
}


fun getDatabase(context: Context): ItemDatabase {
    synchronized(ItemDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                ItemDatabase::class.java,
                "item_.db"
            ).fallbackToDestructiveMigration()
                .build()
        }
    }
    return INSTANCE
}