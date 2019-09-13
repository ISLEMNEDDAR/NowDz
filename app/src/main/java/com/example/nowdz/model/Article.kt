package com.example.nowdz.model


import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import kotlinx.android.parcel.Parcelize
import java.util.*

@Suppress("UNREACHABLE_CODE")
@Parcelize
@Entity(tableName = "article_table")
data class Article (
    @Embedded
    var journal : Source,
    var author : String?,
    var titre : String?,
    var url : String?,
    var urltoImage : Int?,
    var published_at : Date?,
    var content : String?,
    var categorie : String,
    var suivi : Boolean
): Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}