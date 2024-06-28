package com.example.weatherapp.features.weather.presentation.citylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weatherapp.features.weather.domain.model.City

class CityListAdapter(private val onItemClicked: (City) -> Unit): RecyclerView.Adapter<CityListHolder>() {

    private var cities: MutableList<City> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        return CityListHolder(view, onItemClicked)
    }

    override fun onBindViewHolder(holder: CityListHolder, position: Int) {
        holder.bind(city = cities[position])
    }

    override fun getItemCount(): Int {
        return if (cities != null) cities.size else 0
    }

    fun updateCities(newCities: List<City>) {
        if (cities == null) return
        val diffCallback = CityDiffCallback(this.cities, newCities)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.cities.clear()
        this.cities.addAll(newCities)
        diffResult.dispatchUpdatesTo(this)
    }
}

class CityListHolder(itemView: View, private val onItemClicked: (City) -> Unit): RecyclerView.ViewHolder(itemView) {
    fun bind(city: City) {
        with(itemView) {
            findViewById<TextView>(R.id.tv_city).text = city.name
            setOnClickListener {
                onItemClicked(city)
            }
        }
    }
}

class CityDiffCallback(
    private val oldList: List<City>,
    private val newList: List<City>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldListSize
    }

    override fun getNewListSize(): Int {
        return newListSize
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}