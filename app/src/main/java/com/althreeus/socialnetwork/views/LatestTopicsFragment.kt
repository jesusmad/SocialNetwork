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
import android.widget.Button
import com.althreeus.socialnetwork.model.TestTopic
import kotlinx.android.synthetic.main.fragment_latest_topics.*


class LatestTopicsFragment : Fragment() {

    private lateinit var adapter: CustomAdapterTopics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_latest_topics, container, false)

        val rvtopics = view.findViewById<RecyclerView>(R.id.rvLatestTopics)

        rvtopics.layoutManager = LinearLayoutManager(activity)

        val topics = TestTopic

        adapter = CustomAdapterTopics(activity, R.layout.topic_row, topics.TOPICS)
        rvtopics.adapter = adapter

        return view
    }

}
