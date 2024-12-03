package com.example.wandernow


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wandernow.databinding.FragmentTriplistBinding
import com.example.wandernow.dataclass.Triplist

class TripListFragment :Fragment(){
    lateinit var binding: FragmentTriplistBinding
    private var triplistDatas = ArrayList<Triplist>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTriplistBinding.inflate(inflater,container,false)

        binding.addListBtn.setOnClickListener{
            Toast.makeText(requireContext(),"Button Clicked!!", Toast.LENGTH_SHORT).show()
        }


        triplistDatas.apply{
            add(Triplist("2024.10.21",R.drawable.img_detail_gapyeong_2,"장소: 경기도 가평군"))
            add(Triplist("2024.10.02",R.drawable.gapyeoung2,"장소: 경기도 가평군"))
            add(Triplist("2024.09.18",R.drawable.gapyeoung3,"장소: 경기도 가평군"))
            add(Triplist("2024.10.21",R.drawable.img_detail_gapyeong_2,"장소: 경기도 가평군"))
            add(Triplist("2024.10.02",R.drawable.gapyeoung2,"장소: 경기도 가평군"))
            add(Triplist("2024.09.18",R.drawable.gapyeoung3,"장소: 경기도 가평군"))
        }

        val triplistRVAdapter = TriplistRVAdapter(triplistDatas)
        binding.tripRV.adapter = triplistRVAdapter
        binding.tripRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        triplistRVAdapter.setMyItemCLickListener(object: TriplistRVAdapter.MyItemClickListener {
            override fun onItemClick(triplist: Triplist) {
                changeTripDetailFragment(triplist)
            }
        })

        return binding.root
    }

    private fun changeTripDetailFragment(triplist: Triplist) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, TripDetailFragment().apply {
            })
            .commitAllowingStateLoss()
    }
}