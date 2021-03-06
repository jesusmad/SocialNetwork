package com.althreeus.socialnetwork.views


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.althreeus.realtimedbfirebase.adapter.CustomAdapterTopics

import com.althreeus.socialnetwork.R


class MyTopicsFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_my_topics, container, false)


        val rvtopics = view.findViewById<RecyclerView>(R.id.rvMyTopics)

        rvtopics.layoutManager = LinearLayoutManager(activity)


        //HomeActivity.adapterMyTopics = CustomAdapterTopics(activity, R.layout.topic_row, HomeActivity.mytopics)
        rvtopics.adapter = HomeActivity.adapterMyTopics


        return view
    }

}
