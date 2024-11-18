package com.example.wandernow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wandernow.databinding.FragmentHomeBinding
import com.example.wandernow.viewmodel.LocationViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.ArrayList

class HomeFragment :Fragment() {
    lateinit var binding: FragmentHomeBinding
    private var locationDatas = ArrayList<Location>()
    private var popularLocationDatas = ArrayList<Location>()
    private val viewModel: LocationViewModel by viewModels()
    private lateinit var locationRVAdapter: LocationRVAdapter

    private var database: FirebaseDatabase? = null
    private var databaseReference: DatabaseReference? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        database = FirebaseDatabase.getInstance() // 파이어 데이터베이스 연동
        databaseReference = database!!.getReference("location")

//        databaseReference!!.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                locationDatas.clear() // 기존 데이터 초기화
//
//                for (data in dataSnapshot.children) {
//                    val locationData = data.getValue(Location::class.java)
//                    locationData?.let{
//                        locationDatas.add(it)
//                    }
//                }
//                locationRVAdapter.notifyDataSetChanged() // 어댑터에 데이터 변경 알림
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//        })

//        observeViewModel()

        locationDatas.apply{
            add(Location("55분","경기도 포천시", 4.9,"자연","계곡","관광",R.drawable.img_pocheon))
            add(Location("60분","경기도 가평군", 5.0,"자연","액티비티","관광",R.drawable.img_gapyeong))
            add(Location("55분","경기도 수원시", 4.7,"한국적인","야경","관광",R.drawable.img_suwon))
            add(Location("55분","경기도 동두천시", 4.5,"이국적인","자연","관광",R.drawable.img_dongducheon))
            add(Location("50분","경기도 광주시", 4.8,"자연","단풍","관광",R.drawable.img_gwangju))
        }

        popularLocationDatas.apply{
            add(Location("","경남 거제시", 5.0,"한국적인","바다","노을",R.drawable.img_geoje))
            add(Location("","경기도 수원시", 4.9,"도시","야경","바다",R.drawable.img_suwon))
            add(Location("","경기도 연천군", 5.0,"가을","자연","관광",R.drawable.img_yeoncheon))
            add(Location("","경북 경주시", 5.0,"한국적인","가을","단풍",R.drawable.img_gyeongju))
        }

        locationRVAdapter = LocationRVAdapter(locationDatas)
        binding.homeOneHourRecommendRv.adapter = locationRVAdapter
        binding.homeOneHourRecommendRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        locationRVAdapter.setMyItemCLickListener(object: LocationRVAdapter.MyItemClickListener{
            override fun onItemClick(location: Location) {
                changeLocationDetailFragment(location)
            }
        })

        val popularRVAdapter = PopularRVAdapter(popularLocationDatas)
        binding.homePopularRv.adapter = popularRVAdapter
        binding.homePopularRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


//        databaseReference!!.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                locationDatas.clear() // 기존 데이터 삭제
//                for (locationSnapshot in snapshot.children) {
//                    val location = locationSnapshot.getValue(Location::class.java)
//                    location?.let { locationDatas.add(it) }
//                }
//                locationRVAdapter.notifyDataSetChanged() // 어댑터에 데이터 변경 알림
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.e("FirebaseError", error.message)
//            }
//        })

        return binding.root
    }

    private fun changeLocationDetailFragment(location: Location) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, LocationDetailFragment().apply {
//                arguments = Bundle().apply {
//                    val gson = Gson()
//                    val triplistJson = gson.toJson(location)
//                    putString("triplist", triplistJson)
//                }
            })
            .commitAllowingStateLoss()
    }

    private fun observeViewModel() {
        viewModel.locations.observe(viewLifecycleOwner, Observer { locations ->
            locations?.let {
                locationRVAdapter.updateLocations(it)
            }
        })
    }
}