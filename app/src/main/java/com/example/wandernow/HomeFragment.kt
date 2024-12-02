package com.example.wandernow

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.wandernow.databinding.FragmentHomeBinding
import com.example.wandernow.viewmodel.LocationViewModel
import java.util.Timer
import kotlin.concurrent.scheduleAtFixedRate

class HomeFragment :Fragment() {
    lateinit var binding: FragmentHomeBinding
    private val viewModel: LocationViewModel by activityViewModels()
    private lateinit var locationRVAdapter: LocationRVAdapter
    private lateinit var popularRVAdapter: PopularRVAdapter
    private val timer = Timer()
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupOneHourRecyclerView()
        setupPopularRecyclerView()
        observeLocations()

        val homeBannerVPAdapter = HomeBannerVPAdapter(this)
        homeBannerVPAdapter.addFragment(HomeBannerFragment(R.drawable.img_home_banner))
        homeBannerVPAdapter.addFragment(HomeBannerFragment(R.drawable.img_home_banner2))
        homeBannerVPAdapter.addFragment(HomeBannerFragment(R.drawable.img_home_banner3))
        binding.homeBannerVp.adapter = homeBannerVPAdapter
        binding.homeBannerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        startAutoSlide(homeBannerVPAdapter)

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

    private fun startAutoSlide(adapter : HomeBannerVPAdapter) {
        timer.scheduleAtFixedRate(4000,4000) {
            handler.post {
                val nextItem = binding.homeBannerVp.currentItem + 1
                if (nextItem < adapter.itemCount) {
                    binding.homeBannerVp.currentItem = nextItem
                } else {
                    binding.homeBannerVp.currentItem = 0
                }
            }
        }
    }
}