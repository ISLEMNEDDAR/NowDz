package com.example.nowdz.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nowdz.Adapter.NewAdapter
import com.example.nowdz.R


class AcuilleFragment : Fragment() {
    private val list = ArrayList<String>()
    private var newsRecyclerView: RecyclerView? = null
    private var newsAdapter: NewAdapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_accuille,null)
        initRvNews(v)
        ajouterNews()
        return v

    }

    private fun initRvNews(v: View){
        newsRecyclerView = v.findViewById(R.id.recycle_news_acuille)
        newsAdapter = NewAdapter(list,v.context)
        val horizontalLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        newsRecyclerView!!.layoutManager = horizontalLayoutManager
        newsRecyclerView!!.adapter = newsAdapter
    }
    private fun ajouterNews(){
        list.add("a")
        list.add("a")
        list.add("a")
        list.add("a")
        newsAdapter!!.notifyDataSetChanged()
    }
}
