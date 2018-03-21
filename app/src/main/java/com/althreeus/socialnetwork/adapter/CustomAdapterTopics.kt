package com.althreeus.realtimedbfirebase.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.althreeus.socialnetwork.model.Topic
import kotlinx.android.synthetic.main.topic_row.view.*

/**
 * Created by jr on 05-Mar-18.
 */
class CustomAdapterTopics(val context: Context,
                           val layout: Int,
                           val dataList: ArrayList<Topic>): RecyclerView.Adapter<CustomAdapterTopics.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item,position)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {

        fun bind(dataItem: Topic, position: Int){

            itemView.tvTopicNameRow.text = dataItem.name
            itemView.tvUserNameRow.text = dataItem.nick
            itemView.tvDateRow.text = dataItem.date
            itemView.tvTechNameRow.text = dataItem.nameTechnology

        }

    }

}