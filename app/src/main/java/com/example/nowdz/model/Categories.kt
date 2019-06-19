package com.example.nowdz.model

import com.example.nowdz.R

enum class Categories(val category : Int,var affichee : Boolean) {
    ALGERIE(R.string.algerie,true),
    SPORT(R.string.sport,false),
    ECONOMIE(R.string.economie,true),
    SANTE(R.string.sante,true),
    EMPLOI(R.string.emploi,false),
    CULTURE(R.string.culture,true),
    POLITICS(R.string.politics,true),
    INTERNATIONAL(R.string.international,true)
}