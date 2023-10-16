package com.example.recyclerview.data

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerview.R
import com.example.recyclerview.model.Photo

class PhotoListAdapter(private val list: ArrayList<Photo>, private val context: Context) :
    RecyclerView.Adapter<PhotoListAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(photo: Photo) {
            val title = itemView.findViewById<TextView>(R.id.txtTitle)
            val myImageView = itemView.findViewById<ImageView>(R.id.myImageView)

            title.text = photo.title
            Glide.with(itemView)
                .load(photo.url)
                .into(myImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.photo_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position])
    }
}