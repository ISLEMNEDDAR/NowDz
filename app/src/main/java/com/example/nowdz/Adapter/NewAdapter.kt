package com.example.nowdz.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.nowdz.R

class NewAdapter (
    private val list: ArrayList<String>,
    internal var context: Context
) : RecyclerView.Adapter<NewAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        //inflate the layout file
        val newView =
            LayoutInflater.from(parent.context).inflate(R.layout.card_news_secondaire, parent, false)
        return NewsViewHolder(newView)
    }

    override fun onBindViewHolder(holder:NewsViewHolder, position: Int) {
        val classement = position+2
        holder.posiotion.text = "$classement."

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        internal var posiotion : TextView = view.findViewById(R.id.clasement_news)

    }
}
