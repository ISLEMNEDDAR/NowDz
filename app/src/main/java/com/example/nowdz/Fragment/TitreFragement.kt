package com.example.nowdz.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nowdz.R
import com.nshmura.recyclertablayout.RecyclerTabLayout
import android.support.v4.view.ViewPager
import com.example.nowdz.Adapter.TitlePagerAdapter



class TitreFragement : Fragment() {
    protected var mRecyclerTabLayout: RecyclerTabLayout? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_titre,null)
        val listTitle = resources.getStringArray(R.array.type_news).toList()

        val adapter = TitlePagerAdapter(activity)
        adapter.addAll(listTitle)

        val viewPager = v.findViewById<ViewPager>(R.id.content_vp)
        viewPager.adapter = adapter

        mRecyclerTabLayout = v.findViewById(R.id.titre_tab_layout)
        mRecyclerTabLayout!!.setUpWithViewPager(viewPager)



        return v

    }
}
