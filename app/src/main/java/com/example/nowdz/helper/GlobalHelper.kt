package com.example.nowdz.helper

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.widget.ImageView
import com.example.nowdz.R
import com.example.nowdz.controller.ArticleController
import com.example.nowdz.model.Article

interface GlobalHelper{

    fun switchActivity(contextSource : Context,contextDist:Class<*>,activity : Activity) {
        val intent = Intent(contextSource, contextDist)
        activity.startActivity(intent)
    }
    fun switchActivityExtra(contextSource : Context, contextDist:Class<*>, activity : Activity, tag :String, parcel :Parcelable) {
        val intent = Intent(contextSource, contextDist)
        intent.putExtra(tag,parcel)
        activity.startActivity(intent)
    }

    /**
     * le system de suive qui retourne un tableau de favoris
     */
    fun suiviProc(suivi : ImageView,article : Article):ArrayList<Article>{
        if (article.suivi){
            //faire le desabonnement
            processusSuivre(R.drawable.ic_save,suivi,"nonSuivi")

        }else{
            // l'abonnement
            processusSuivre(R.drawable.ic_saved,suivi,"suivi")
        }
        return ArticleController.getAllFavoris()
    }

    /**
     * Cote design seulement
     */
    fun processusSuivre(drawable :Int, image : ImageView, tag : String){
        image.setImageResource(drawable)
        image.tag = tag
    }


    /**
     * cote design seulement
     */
    fun toggleSuivi(suivi : Boolean, imageSuivi : ImageView, drawableSuivi : Int, drawableNonSuivi : Int){
        if (suivi){
            processusSuivre(R.drawable.ic_saved,imageSuivi,"Suivi")
        }else{
            processusSuivre(R.drawable.ic_save,imageSuivi,"nonSuivi")
        }
    }
}