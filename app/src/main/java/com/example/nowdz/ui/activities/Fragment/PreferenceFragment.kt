package com.example.nowdz.ui.activities.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.nowdz.R
import com.example.nowdz.ui.Adapter.PreferencePagerAdapter
import com.nshmura.recyclertablayout.RecyclerTabLayout

class PreferenceFragment : Fragment() {
    protected var mRecyclerTabLayout: RecyclerTabLayout? = null
    var preferenceList : ArrayList<Int> = arrayListOf(1,2)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_preference,null)
        val viewPager = v.findViewById<ViewPager>(R.id.preference_vp)
        mRecyclerTabLayout = v.findViewById<RecyclerTabLayout>(R.id.preference_tab_layout)
        //avoir les id des categorie
        val adapter = PreferencePagerAdapter(activity)

        //tronsformer les id en string
        val listTitre : ArrayList<String> = arrayListOf("Theme","Site Presse")

        adapter.addAllNum(preferenceList)
        adapter.addAllTitle(listTitre)
        viewPager.adapter = adapter

        mRecyclerTabLayout!!.setUpWithViewPager(viewPager)
        return v

    }


}