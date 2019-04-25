package com.example.nowdz.helper

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.nowdz.Adapter.NewAdapter
import com.example.nowdz.R

/**
 * dans le code ou elle est appler il faut initilaiser
 * la list
 * le adapter
 */

class RecycleViewHelper {

    var list = ArrayList<String>()
    private var recyclerView: RecyclerView? = null
    var adapter: NewAdapter? = null

    fun initLineaire(v: View,idRV : Int,orientationLayout:Int){
        recyclerView = v.findViewById(idRV)
        val horizontalLayoutManager = LinearLayoutManager(v.context, orientationLayout, false)
        recyclerView!!.layoutManager = horizontalLayoutManager
        recyclerView!!.adapter = adapter
    }
    fun ajouter(){
        list.clear()
        list.add("a")
        list.add("a")
        list.add("a")
        list.add("a")
        adapter!!.notifyDataSetChanged()
    }
}