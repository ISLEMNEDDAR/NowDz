package com.example.nowdz.ui.Adapter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.nowdz.R
import com.example.nowdz.Service.ArticleService
import com.example.nowdz.Service.ServiceBuilder
import com.example.nowdz.helper.GlobalHelper
import com.example.nowdz.helper.SharedPreferenceInterface
import com.example.nowdz.helper.SharedPreferencesHelper
import com.example.nowdz.model.Categories
import com.example.nowdz.model.Source
import com.example.nowdz.viewModel.CategoryViewModel

class SiteAdapter(
    internal var context: Context,
    var view: View,
    var activity: Activity?)
    : RecyclerView.Adapter<SiteAdapter.SiteViewHolder>(),
    GlobalHelper,SharedPreferenceInterface {
    private var siteList : List<Source> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiteAdapter.SiteViewHolder {

        //inflate the layout file
        val newView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_site, parent, false)
        println("je suis creat theme adapter")
        return SiteViewHolder(newView)
    }

    override fun onBindViewHolder(holder: SiteViewHolder, position: Int) {
        val sites = siteList[position]
        var imageSuivi = holder.suivieImage
        holder.nomTheme.text = sites.name
        toggleSuivi(avoirSuivi(context,sites.name!!),imageSuivi, R.drawable.ic_saved, R.drawable.ic_save)
        var articleService = ServiceBuilder.buildService(ArticleService::class.java)
        holder.suivieImage.setOnClickListener {
            if (holder.suivieImage.tag == "nonSuivi"){
                /**
                 * faire l'abonnement
                 */
                //categoryViewModel.updateSuivi(1,categories.categoryId)
                setSuivi(context,sites.name!!,true)
                processusSuivre(R.drawable.ic_saved,imageSuivi,"Suivi")
                Log.i("user : ",avoirIdUser(context).toString())

            }else{
                /**
                 * desabonner
                 */
                //categoryViewModel.updateSuivi(0,categories.categoryId)
                setSuivi(context,sites.name!!,false)
                processusSuivre(R.drawable.ic_save,imageSuivi,"nonSuivi")

            }
        }
    }
    override fun getItemCount(): Int {
        return siteList.size
    }
    fun setSource(sites: List<Source>) {
        this.siteList = sites
        notifyDataSetChanged()
    }
    inner class SiteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal var item = view.findViewById<LinearLayout>(R.id.item_site)
        internal var suivieImage = view.findViewById<ImageView>(R.id.im_image_site)
        internal var nomTheme = view.findViewById<TextView>(R.id.im_text_site)
    }
}