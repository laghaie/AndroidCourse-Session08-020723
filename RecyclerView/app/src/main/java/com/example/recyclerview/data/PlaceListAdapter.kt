package com.example.recyclerview.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import com.example.recyclerview.model.Place

class PlaceListAdapter(private val list: ArrayList<Place>, private val context: Context) :
    RecyclerView.Adapter<PlaceListAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(place: Place) {
            var txtCountry = itemView.findViewById<TextView>(R.id.txtCountry)
            var txtCity = itemView.findViewById<TextView>(R.id.txtCity)

            txtCountry.text = place.countryName
            txtCity.text = place.cityName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position])
    }
}