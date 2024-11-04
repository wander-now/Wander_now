package com.example.wandernow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wandernow.databinding.FragmentTriplistBinding

class TripListFragment :Fragment(){
    lateinit var binding: FragmentTriplistBinding
    private var triplistDatas = ArrayList<Triplist>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTriplistBinding.inflate(inflater,container,false)

        triplistDatas.apply{
            add(Triplist("2024.10.21",R.drawable.img_detail_gapyeong_2,"장소: 경기도 가평군"))
            add(Triplist("2024.10.23",R.drawable.img_detail_gapyeong_3,"장소: 경기도 가평군"))
            add(Triplist("2024.10.18",R.drawable.img_detail_gapyeong_1,"경장소: 경기도 가평군"))
        }

        val locationRVAdapter = TriplistRVAdapter(triplistDatas)
        binding.tripListRv.adapter = locationRVAdapter
        binding.tripListRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        return binding.root
    }
}