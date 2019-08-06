package com.example.nowdz.helper

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

interface RecycleViewHelper {
    var itemRecycleView : RecyclerView?
    /**
     * la fonction qui initilaise le recycleview
     */
    fun initLineaire(v: View, idRV : Int, orientationLayout:Int, adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>?){
        itemRecycleView = v.findViewById(idRV)
        val horizontalLayoutManager = LinearLayoutManager(v.context, orientationLayout, false)
        itemRecycleView!!.layoutManager = horizontalLayoutManager
        itemRecycleView!!.adapter = adapter!!
    }
    fun initGrid(v: View, idRV : Int, numberOfColomns:Int, adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>?){
        itemRecycleView = v.findViewById(idRV)
        val horizontalLayoutManager = GridLayoutManager(v.context,numberOfColomns)
        itemRecycleView!!.layoutManager = horizontalLayoutManager
        itemRecycleView!!.adapter = adapter!!
    }

}