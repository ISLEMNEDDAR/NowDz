package com.example.nowdz.helper

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View

interface RecycleViewHelper {
    var itemRecycleView : androidx.recyclerview.widget.RecyclerView?
    /**
     * la fonction qui initilaise le recycleview
     */
    fun initLineaire(v: View, idRV : Int, orientationLayout:Int, adapter : androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>?){
        itemRecycleView = v.findViewById(idRV)
        val horizontalLayoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(v.context, orientationLayout, false)
        itemRecycleView!!.layoutManager = horizontalLayoutManager
        itemRecycleView!!.adapter = adapter!!
    }
    fun initGrid(v: View, idRV : Int, numberOfColomns:Int, adapter : androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>?){
        itemRecycleView = v.findViewById(idRV)
        val horizontalLayoutManager =
            androidx.recyclerview.widget.GridLayoutManager(v.context, numberOfColomns)
        itemRecycleView!!.layoutManager = horizontalLayoutManager
        itemRecycleView!!.adapter = adapter!!
    }

}