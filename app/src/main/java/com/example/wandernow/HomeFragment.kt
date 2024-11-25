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
    private val viewModel: LocationViewModel by activityViewModels()
    private lateinit var locationRVAdapter: LocationRVAdapter
    private lateinit var popularRVAdapter: PopularRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupOneHourRecyclerView()
        setupPopularRecyclerView()
        observeLocations()
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

    private fun setupPopularRecyclerView() {
        popularRVAdapter = PopularRVAdapter(emptyList())
        binding.homePopularRv.adapter = popularRVAdapter
        binding.homePopularRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun changeLocationDetailFragment(location: Location) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, LocationDetailFragment().apply {
            })
            .commitAllowingStateLoss()
    }

    private fun observeLocations() {
        viewModel.getLocations().observe(viewLifecycleOwner) { locations ->
            val oneHourFiltered = locations.filter { it.time <= 60 }
            val popularSorted = locations.sortedByDescending { it.star }

            Log.d("HomeFragment", "Sorted locations: $popularSorted")

            // 어댑터에 데이터 업데이트
            locationRVAdapter.updateLocations(oneHourFiltered.take(5))
            popularRVAdapter.updatePopularLocations(popularSorted.take(5))
        }
    }
}