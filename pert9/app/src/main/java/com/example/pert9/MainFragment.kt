package com.example.pert9

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.pert9.adapter.CityDataAdapter
import com.example.pert9.databinding.FragmentMainBinding
import com.example.pert9.entity.City
import com.example.pert9.entity.WeatherResponse
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import java.io.InputStreamReader

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var cities: ArrayList<City>
    private lateinit var CityDataAdapter: CityDataAdapter
    private lateinit var fragmentMainBinding : FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cities = ArrayList()
        CityDataAdapter = CityDataAdapter(cities)
        CityDataAdapter.setCityListener(object: CityDataAdapter.CityDataListener{
            override fun cityItemClicked(city: City) {
                showWeatherData(city)
            }
        })



}
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMainBinding = FragmentMainBinding.inflate(inflater,container,false)
        fragmentMainBinding.rvCities.layoutManager = LinearLayoutManager(context)
        fragmentMainBinding.rvCities.adapter = CityDataAdapter
        fragmentMainBinding.srLayout.setOnRefreshListener {
            fragmentMainBinding.srLayout.isRefreshing = false
        }
        return fragmentMainBinding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun onStart() {
        super.onStart()
        this.fetchCityDataFromFile()
    }
    private fun fetchCityDataFromFile(){
        val inputStream = activity?.assets?.open("city.list.json")
        val reader = JsonReader(InputStreamReader(inputStream, "UTF-8"))
        val gson = Gson()
        val city = gson.fromJson<Array<City>>(reader, Array<City>::class.java)
        cities.addAll(city)
        CityDataAdapter.notifyItemChanged(0)
    }
    private fun showWeatherData(city: City){
        val requestQueue = Volley.newRequestQueue(activity)
        val uri = Uri.parse("https://api.openweathermap.org/data/2.5/weather").buildUpon().appendQueryParameter("id",city.id.toString())
            .appendQueryParameter("appid","25d628e4f3e5c826856d8e87e4c0d09e").build()
        val request = StringRequest(
            Request.Method.GET,uri.toString(),
            Response.Listener {
                val gson = Gson()
                val weatherResponse = gson.fromJson<WeatherResponse>(it, WeatherResponse::class.java)
                Toast.makeText(activity,weatherResponse.mainWeather.humidity.toString(),
                    Toast.LENGTH_SHORT).show()
            },
            Response.ErrorListener {
                Toast.makeText(activity,it.message, Toast.LENGTH_SHORT).show()
            })
        requestQueue.add(request)
    }
}