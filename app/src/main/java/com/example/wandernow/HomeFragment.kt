package com.example.wandernow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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
import com.google.firebase.database.database
import java.util.ArrayList

class HomeFragment :Fragment() {
    lateinit var binding: FragmentHomeBinding
    private var popularLocationDatas = ArrayList<Location>()
    private val viewModel: LocationViewModel by activityViewModels()
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

        popularLocationDatas.apply{
            add(Location("","경남 거제시", 5.0,"한국적인","바다","노을",R.drawable.img_geoje))
            add(Location("","경기도 수원시", 4.9,"도시","야경","바다",R.drawable.img_suwon))
            add(Location("","경기도 연천군", 5.0,"가을","자연","관광",R.drawable.img_yeoncheon))
            add(Location("","경북 경주시", 5.0,"한국적인","가을","단풍",R.drawable.img_gyeongju))
        }

        val popularRVAdapter = PopularRVAdapter(popularLocationDatas)
        binding.homePopularRv.adapter = popularRVAdapter
        binding.homePopularRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        setupOneHourRecyclerView()
        observeViewModel()

        return binding.root
    }

    private fun setupOneHourRecyclerView() {
        locationRVAdapter = LocationRVAdapter(emptyList())
        binding.homeOneHourRecommendRv.adapter = locationRVAdapter
        binding.homeOneHourRecommendRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        locationRVAdapter.setMyItemCLickListener(object: LocationRVAdapter.MyItemClickListener{
            override fun onItemClick(location: Location) {
                changeLocationDetailFragment(location)
            }
        })
    }

    private fun changeLocationDetailFragment(location: Location) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, LocationDetailFragment().apply {
            })
            .commitAllowingStateLoss()
    }

    private fun observeViewModel() {
        viewModel.locations.observe(viewLifecycleOwner, Observer { locations ->
            locations?.let {
                locationRVAdapter.updateLocations(it)
                locationRVAdapter.notifyDataSetChanged()
            }
        })
    }
}