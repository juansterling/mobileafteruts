package com.example.pert9.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pert9.R
import com.example.pert9.databinding.CityItemBinding
import com.example.pert9.entity.City

class CityDataAdapter(private val cities: ArrayList<City>) : RecyclerView.Adapter<CityDataAdapter.CityViewHolder>() {

    private lateinit var cityListener: CityListener

    inner class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: CityItemBinding
        init {
            binding = CityItemBinding.bind(itemView)
        }
        fun setCity(city: City) {
            binding.tvCityName.text = city.name
            binding.tvCityCoordinate.text = "${city.coordinate.latitude}, ${city.coordinate.longitude}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.city_item, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.setCity(cities[position])
        holder.itemView.setOnClickListener {
            cityListener.cityClicked(cities[position])
        }
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    fun setCityListener(cityListener: CityDataListener) {
        this.cityListener = cityListener
    }

    interface CityListener {
        fun cityClicked(city: City)
    }
interface CityDataListener : CityListener {
    fun cityItemClicked(city: City)
    override fun cityClicked(city: City) {
        TODO("Not yet implemented")
    }
}
}