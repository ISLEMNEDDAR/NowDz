package com.example.nowdz.ui.activities.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nowdz.R
import com.nshmura.recyclertablayout.RecyclerTabLayout
import androidx.viewpager.widget.ViewPager
import com.example.nowdz.ui.Adapter.TitlePagerAdapter
import com.example.nowdz.controller.CategorieController


class TitreFragement : Fragment() {
    protected var mRecyclerTabLayout: RecyclerTabLayout? = null
    var listNumCat = ArrayList<Int>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_titre,null)
        val viewPager = v.findViewById<ViewPager>(R.id.content_vp)
        mRecyclerTabLayout = v.findViewById<RecyclerTabLayout>(R.id.titre_tab_layout)
        //avoir les id des categorie
         listNumCat = CategorieController.getAllTitre()
        val adapter = TitlePagerAdapter(activity)

        //tronsformer les id en string
        val listTitre = ArrayList<String>()
        for (numCat in listNumCat){

            listTitre.add(getString(numCat))
            println(listTitre.toString())
        }
        adapter.addAllNum(listNumCat)
        adapter.addAllTitle(listTitre)
        viewPager.adapter = adapter

        mRecyclerTabLayout!!.setUpWithViewPager(viewPager)
        return v

    }
}
