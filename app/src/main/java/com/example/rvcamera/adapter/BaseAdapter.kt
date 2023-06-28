package com.example.rvcamera.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rvcamera.R

class BaseAdapter() : RecyclerView.Adapter<BaseViewHolder>() {
    val list = listOf<String>("a","b","c")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).
        inflate(R.layout.camera_item, parent, false) as View
        return BaseViewHolder(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.imageView.text = list[position]
    }
}


class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val imageView : TextView = itemView.findViewById(R.id.camera_image)

}