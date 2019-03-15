package com.syxz.hbdroid.activitys.recyclerview.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.syxz.hbdroid.R
import kotlinx.android.synthetic.main.my_text_view.view.*

class MyBasicAdapter(private var myDataset: ArrayList<String>) : RecyclerView.Adapter<MyBasicAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.my_text_view, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = myDataset.size

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        viewHolder.textView.text = myDataset[position]
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.tvContent
    }

    fun getDatas(): ArrayList<String> {
        return myDataset
    }
}