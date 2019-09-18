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
import com.example.nowdz.controller.ArticleController
import com.example.nowdz.helper.GlobalHelper
import com.example.nowdz.helper.SharedPreferenceInterface
import com.example.nowdz.helper.onWebView
import com.example.nowdz.model.Article
import com.example.nowdz.model.Categories
import com.example.nowdz.model.RequestFavoris
import com.example.nowdz.viewModel.CategoryViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThemeAdapter(
    internal var context: Context,
    var view: View,
    var activity: Activity?)
    : RecyclerView.Adapter<ThemeAdapter.ThemeViewHolder>(),
    GlobalHelper, SharedPreferenceInterface {
    private var categorieList : List<Categories> = ArrayList()
    var categoryViewModel = ViewModelProviders.of(context as FragmentActivity).get(
        CategoryViewModel::class.java)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeAdapter.ThemeViewHolder {

        //inflate the layout file
        val newView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_theme, parent, false)
        println("je suis creat theme adapter")
        return ThemeViewHolder(newView)
    }

    override fun onBindViewHolder(holder: ThemeViewHolder, position: Int) {
        val categories = categorieList.get(position)
        var imageSuivi = holder.suivieImage
        holder.nomTheme.text = activity!!.getString(categories.categoryId)
        var suivi = false
        if(categories.suivi == 1) suivi = true
        toggleSuivi(suivi,imageSuivi,R.drawable.ic_saved,R.drawable.ic_save)
        var articleService = ServiceBuilder.buildService(ArticleService::class.java)
        holder.suivieImage.setOnClickListener {
            if (holder.suivieImage.tag == "nonSuivi"){
                /**
                 * faire l'abonnement
                 */
               addTheme(articleService,categories,holder,imageSuivi)
            }else{
                /**
                 * desabonner
                 */
                removeTheme(articleService,categories,holder,imageSuivi)
            }
        }
    }
    override fun getItemCount(): Int {
        return categorieList.size
    }
    fun setCategory(categories: List<Categories>) {
        this.categorieList = categories
        notifyDataSetChanged()
    }
    inner class ThemeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal var item = view.findViewById<LinearLayout>(R.id.item_theme)
        internal var suivieImage = view.findViewById<ImageView>(R.id.im_image_theme)
        internal var nomTheme = view.findViewById<TextView>(R.id.im_text_theme)
    }

    fun removeTheme(articleService : ArticleService, categories : Categories, holder : ThemeAdapter.ThemeViewHolder,imageSuivi : ImageView){
        var request = articleService.removeTheme(avoirIdUser(context).toString(),activity!!.getString(categories.categoryId)

        )
        request.enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if(response.isSuccessful){
                    categoryViewModel.updateSuivi(0,categories.categoryId)
                    processusSuivre(R.drawable.ic_save,imageSuivi,"nonSuivi")
                }else{
                    removeTheme(articleService,categories,holder,imageSuivi)
                }

            }
            override fun onFailure(call: Call<Any>, t: Throwable) {
                removeTheme(articleService,categories,holder,imageSuivi)
            }
        })
    }

    fun addTheme(articleService : ArticleService, categories : Categories, holder : ThemeAdapter.ThemeViewHolder,imageSuivi : ImageView){
        var request = articleService.removeTheme(avoirIdUser(context).toString(),
            activity!!.getString(categories.categoryId)
        )
        request.enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if(response.isSuccessful){
                    categoryViewModel.updateSuivi(1,categories.categoryId)
                    processusSuivre(R.drawable.ic_saved,imageSuivi,"Suivi")
                }else{
                    addTheme(articleService,categories,holder,imageSuivi)
                }
            }
            override fun onFailure(call: Call<Any>, t: Throwable) {
                addTheme(articleService,categories,holder,imageSuivi)
            }
        })
    }

}