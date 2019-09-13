package com.example.nowdz.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Source (
     var name : String?,
     var urlJourrnal : String?,
     var logo : Int?
) : Parcelable