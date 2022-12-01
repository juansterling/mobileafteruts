package com.example.pert9

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.pert9.databinding.FragmentDetailBinding
import com.example.pert9.entity.City
import com.example.pert9.entity.WeatherResponse
import com.google.gson.Gson

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {
    private var city:City? = null
    private  lateinit var fragmentDetailBinding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            city = it.getParcelable(ARG_PARAM1)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentDetailBinding = FragmentDetailBinding.inflate(inflater,container,false)
        return fragmentDetailBinding.root
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
//                Toast.makeText(activity,weatherResponse.mainWeather.humidity.toString(),
//                    Toast.LENGTH_SHORT).show()
                fragmentDetailBinding.tvdetail1.text = weatherResponse.mainWeather.humidity.toString()
            },
            Response.ErrorListener {
                Toast.makeText(activity,it.message, Toast.LENGTH_SHORT).show()
            })
        requestQueue.add(request)
    }

    override fun onStart() {
        super.onStart()
        city?.let{showWeatherData(it)}
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(city: City) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1,city)
                }
            }
    }
}