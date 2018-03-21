package com.althreeus.socialnetwork.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.althreeus.socialnetwork.model.Repository
import com.althreeus.socialnetwork.model.Topic
import kotlinx.android.synthetic.main.repositories_row.view.*

/**
 * Created by alberto on 21/03/2018.
 */
class RepositoryCustomAdapter (val context: Context,
                               val layout: Int,
                               val data: List<Topic>): RecyclerView.Adapter<RepositoryCustomAdapter.ViewHolder>() {


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
        fun bind(dataItem: Topic){
            itemView.tvNameProfileRepository.text = dataItem.name
            itemView.setOnClickListener( {v-> initPostActivity(dataItem)})
        }

        private fun initPostActivity(dataItem: Topic) {
            val intent = Intent(context, PostActivity::class.java)
            intent.putExtra("topic", dataItem)
            context.startActivity(intent)
        }
    }
}