package com.example.nowdz.ui.activities.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.example.nowdz.R
import com.example.nowdz.model.Categories
import com.example.nowdz.ui.Adapter.TitlePagerAdapter
import com.example.nowdz.viewModel.CategoryViewModel
import com.nshmura.recyclertablayout.RecyclerTabLayout


class TitreFragement : Fragment() {
    protected var mRecyclerTabLayout: RecyclerTabLayout? = null
    var listNumCat = ArrayList<Int>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var categoryViewModel = ViewModelProviders.of(this).get(
            CategoryViewModel::class.java)

        val v = inflater.inflate(R.layout.fragment_titre,null)
        val viewPager = v.findViewById<ViewPager>(R.id.content_vp)
        mRecyclerTabLayout = v.findViewById(R.id.titre_tab_layout)
        val adapter = TitlePagerAdapter(activity)
        //avoir les id des categorie
        categoryViewModel.getAllCategory().observe(
            this,
            Observer {
                listNumCat.clear()
                it.forEach {name->
                    if(name.affiche==1) listNumCat.add(name.categoryId)
                }
                val listTitre = ArrayList<String>()
                for (numCat in listNumCat){
                    listTitre.add(getString(numCat))
                    println(listTitre.toString())
                }
                println(listNumCat)
                Log.i("list titre",listTitre.toString())
                adapter.addAllNum(listNumCat)
                adapter.addAllTitle(listTitre)
            }

        )
        viewPager.adapter = adapter
        mRecyclerTabLayout!!.setUpWithViewPager(viewPager)
        //tronsformer les id en string
        return v

    }
}
