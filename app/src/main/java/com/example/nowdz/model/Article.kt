package com.example.nowdz.model


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Suppress("UNREACHABLE_CODE")
@Parcelize
data class Article (
    var journal : Source,
    var author : String?,
    var titre : String?,
    var url : String?,
    var urltoImage : Int?,
    var published_at : Date?,
    var content : String?,
    var categorie : Categories,
    var suivi : Boolean

) : Parcelable