package com.example.wandernow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wandernow.databinding.FragmentLocationDetailBinding
import com.example.wandernow.databinding.LocationAttractionViewBinding

class LocationAttractiveFragment: Fragment() {
    lateinit var binding : FragmentLocationDetailBinding
    private var locationAttractiveDatas = ArrayList<LocationAttractive>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLocationDetailBinding.inflate(inflater,container,false)

        locationAttractiveDatas.apply{
            add(LocationAttractive(R.drawable.NamiIsland, "남이섬"))
            add(LocationAttractive(R.drawable.morning_carm, "아침고요수목원"))
            add(LocationAttractive(R.drawable.France, "쁘티프랑스"))
        }

        val locationAttractiveRVAdapter = LocationAttractiveRVAdapter(locationAttractiveDatas)
        binding.locationAttractionRV.adapter = locationAttractiveRVAdapter
        binding.locationAttractionRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        return binding.root
    }


}