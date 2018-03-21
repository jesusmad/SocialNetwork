package com.althreeus.socialnetwork.adapter

import android.content.Context
import android.view.View
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.althreeus.socialnetwork.model.Post
import com.althreeus.socialnetwork.model.Repository
import kotlinx.android.synthetic.main.post_row.view.*
import kotlinx.android.synthetic.main.repositories_row.view.*

/**
 * Created by alberto on 21/03/2018.
 */
class PostsCustomAdapter (val context: Context,
                          val layout: Int,
                          val data: List<Post>): RecyclerView.Adapter<PostsCustomAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }


    inner class ViewHolder(viewLayout: View, val context: Context): RecyclerView.ViewHolder(viewLayout) {
        fun bind(dataItem: Post){

        itemView.tvDate.text = dataItem.date
            itemView.tvContent.text = dataItem.content
            itemView.tvUser.text = dataItem.nick

        }
    }
}