package com.example.nowdz.controller

import com.example.nowdz.model.Categories

object CategorieController {

    private val listTitle = arrayListOf<String>(
        Categories.INTERNATIONAL.category,
        Categories.SPORT.category,
        Categories.ALGERIE.category,
        Categories.CULTURE.category,
        Categories.ECONOMIE.category,
        Categories.EMPLOI.category,
        Categories.POLITICS.category,
        Categories.SANTE.category
    )
    private val listCategories = arrayListOf<Categories>(
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
     * Avoir tous les titres
     */
    fun getAllCategorie():ArrayList<String>{
        return this.listTitle
    }

    /**
     * Avoir tous les Categorie
     */
    fun getAlltitre() : ArrayList<Categories>{
        return this.listCategories
    }


}