package com.althreeus.socialnetwork.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.althreeus.socialnetwork.R
import com.althreeus.realtimedbfirebase.adapter.CustomAdapterTopics
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView


class LatestTopicsFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_latest_topics, container, false)

        val rvtopics = view.findViewById<RecyclerView>(R.id.rvLatestTopics)

        rvtopics.layoutManager = LinearLayoutManager(activity)

        //HomeActivity.adapterLatest = CustomAdapterTopics(activity, R.layout.topic_row, HomeActivity.topics)

        rvtopics.adapter = HomeActivity.adapterLatest

        return view
    }

}
