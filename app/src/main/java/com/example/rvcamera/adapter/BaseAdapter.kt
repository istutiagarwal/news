package com.example.rvcamera.adapter

import android.content.Intent
import android.net.Uri
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rvcamera.R
import com.example.rvcamera.data.ArticlesModel


class BaseAdapter(private val list : List<ArticlesModel>) : RecyclerView.Adapter<BaseViewHolder>() {

    private lateinit var listener: interfaceL

    fun newsListener(listener : interfaceL){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).
        inflate(R.layout.camera_item, parent, false) as View
        return BaseViewHolder(view).apply {
            newsListener(listener)
        }
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(list[position])
    }
}


class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private lateinit var listener: interfaceL
    fun newsListener(listener: interfaceL) {
        this.listener = listener
    }


    val imageView: ImageView = itemView.findViewById(R.id.camera_image)
    val publishedAt: TextView = itemView.findViewById(R.id.rv_publishedAt)
    val name: TextView = itemView.findViewById(R.id.rv_name)
    val title: TextView = itemView.findViewById(R.id.rv_title)
    val description: TextView = itemView.findViewById(R.id.rv_description)
   // val item: Layout= itemView.findViewById(R.id.news_item)

    fun bind(list: ArticlesModel) {
        Glide.with(imageView.context).load(list.urlToImage).into(imageView)
        publishedAt.text = list.publishedAt
        name.text = list.source?.name ?: " "
        title.text = list.title
        description.text = list.description
        imageView.setOnClickListener {
            listener?.onClick(adapterPosition, list)
        }
       // content.text = list.content
    }
}

interface interfaceL{
    fun onClick(position: Int, list : ArticlesModel)
}