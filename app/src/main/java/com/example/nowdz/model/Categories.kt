package com.example.nowdz.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nowdz.R

/*data class Categories(val category : Int,var affichee : Boolean) {
    ALGERIE(R.string.algerie,true),
    SPORT(R.string.sport,false),
    ECONOMIE(R.string.economie,true),
    SANTE(R.string.sante,true),
    EMPLOI(R.string.emploi,false),
    CULTURE(R.string.culture,true),
    POLITICS(R.string.politics,true),
    INTERNATIONAL(R.string.international,true)
}*/
@Entity(tableName = "category")
data class Categories(
    @PrimaryKey
    var categoryId : Int,
    var affiche : Int,
    var suivi : Int
)