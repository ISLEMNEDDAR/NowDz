package com.example.nowdz.ui.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nowdz.R
import com.nshmura.recyclertablayout.RecyclerTabLayout
import android.support.v4.view.ViewPager
import com.example.nowdz.Adapter.TitlePagerAdapter
import com.example.nowdz.controller.CategorieController
import com.example.nowdz.model.Categories


class TitreFragement : Fragment() {
    protected var mRecyclerTabLayout: RecyclerTabLayout? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_titre,null)
        var listTitre = CategorieController.getAllCategorie()
        val adapter = TitlePagerAdapter(activity)
        val viewPager = v.findViewById<ViewPager>(R.id.content_vp)



        adapter.addAll(listTitre)
        viewPager.adapter = adapter
        mRecyclerTabLayout = v.findViewById(R.id.titre_tab_layout)
        mRecyclerTabLayout!!.setUpWithViewPager(viewPager)
        return v

    }
}
