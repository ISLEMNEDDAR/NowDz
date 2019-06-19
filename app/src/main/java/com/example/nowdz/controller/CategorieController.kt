package com.example.nowdz.controller

import com.example.nowdz.model.Categories

object CategorieController {

    private val listTitle = arrayListOf<Int>(
        Categories.INTERNATIONAL.category,
        Categories.SPORT.category,
        Categories.ALGERIE.category,
        Categories.CULTURE.category,
        Categories.ECONOMIE.category,
        Categories.EMPLOI.category,
        Categories.POLITICS.category,
        Categories.SANTE.category
    )
    private var listCategories = arrayListOf<Categories>(
        Categories.INTERNATIONAL,
        Categories.SPORT,
        Categories.ALGERIE,
        Categories.CULTURE,
        Categories.ECONOMIE,
        Categories.EMPLOI,
        Categories.POLITICS,
        Categories.SANTE
    )


    /**
     * Avoir tous les titres suivi
     */
    fun getAllTitre():ArrayList<Int>{
        val list = ArrayList<Int>()
        for(cat in this.listCategories){
            if (cat.affichee){
                list.add(cat.category)
            }
        }
        return list
    }

    /**
     * Avoir tous les Categorie
     */
    fun getAllCategorie() : ArrayList<Categories>{
        return this.listCategories
    }

    fun putAllCategorie(categories: ArrayList<Categories>){
        this.listCategories.addAll(categories)
    }
    fun removelisteCatg(){
        this.listCategories.clear()
    }

}