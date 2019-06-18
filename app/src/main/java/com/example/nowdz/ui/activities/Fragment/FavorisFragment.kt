package com.example.nowdz.ui.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.nowdz.ui.ArticleEnregistreActivity
import com.example.nowdz.R
import com.example.nowdz.helper.GlobalHelper

class FavorisFragment : Fragment(),GlobalHelper {
    private var linktext : TextView?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_favoris,null)
        linktext = v.findViewById(R.id.linktext)
        linktext!!.setOnClickListener{
            switchActivity(v.context, ArticleEnregistreActivity::class.java,activity!!)
        }
        return v
    }
}
