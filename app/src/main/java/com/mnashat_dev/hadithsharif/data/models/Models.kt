package com.mnashat_dev.hadithsharif.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "item_favorite_table")
@Parcelize
data class ItemFavorite(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val benefit: String? = null,
    val body: String? = null,
    val narrator: String? = null,
    val almahdath: String? = null,
    val explanation: String? = null,
    val isFavorite: Boolean? = null,
) : Parcelable


@Entity(tableName = "item_firebase_table")
@Parcelize
data class Hadith(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val benefit: String? = null,
    val body: String? = null,
    val narrator: String? = null,
    val almahdath: String? = null,
    val explanation: String? = null,
    val isFavorite: Boolean? = null,
) : Parcelable


data class ItemForFirebase(
    val id: Long? = null,
    val b: String? = null,
    val d: String? = null,
    val n: String? = null,
    val a: String? = null,
    val e: String? = null,
    val i: Boolean? = null,

    )


fun List<ItemForFirebase>.asdomainModel(): List<Hadith> {
    return map {
        Hadith(
            id = it.id,
            benefit = it.b,
            body = it.d,
            narrator = it.n,
            almahdath = it.a,
            explanation = it.e,
            isFavorite = it.i,
        )
    }
}
