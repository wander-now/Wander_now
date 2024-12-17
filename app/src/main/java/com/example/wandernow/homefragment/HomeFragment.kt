package com.example.wandernow.homefragment

import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.wandernow.LocationDetailFragment
import com.example.wandernow.MainActivity
import com.example.wandernow.R
import com.example.wandernow.databinding.FragmentHomeBinding
import com.example.wandernow.dataclass.Location
import com.example.wandernow.viewmodel.LocationViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import java.util.Timer
import kotlin.concurrent.scheduleAtFixedRate
import android.Manifest
import android.location.Geocoder
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.Priority
import java.util.Locale


class HomeFragment :Fragment() {
    lateinit var binding: FragmentHomeBinding
    private val viewModel: LocationViewModel by activityViewModels()
    var bundle = Bundle()

    //adapter
    private lateinit var locationRVAdapter: LocationRVAdapter
    private lateinit var popularRVAdapter: PopularRVAdapter

    //banner
    private val timer = Timer()
    private val handler = Handler(Looper.getMainLooper())

    //gps
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var requestLocationPermissionLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestLocationPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                getLocation()
            } else {
                binding.currentLocationTv2.text = "위치 권한이 필요합니다."
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupOneHourRecyclerView()
        setupPopularRecyclerView()
        observeLocations()

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        locationRequest = LocationRequest.Builder(10000L) // 10초 (밀리초 단위)
            .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
            .setMinUpdateIntervalMillis(5000L) // 최소 업데이트 간격 5초
            .build()


        val homeBannerVPAdapter = HomeBannerVPAdapter(this)
        homeBannerVPAdapter.addFragment(HomeBannerFragment(R.drawable.img_home_banner))
        homeBannerVPAdapter.addFragment(HomeBannerFragment(R.drawable.img_home_banner2))
        homeBannerVPAdapter.addFragment(HomeBannerFragment(R.drawable.img_home_banner3))
        binding.homeBannerVp.adapter = homeBannerVPAdapter
        binding.homeBannerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        startAutoSlide(homeBannerVPAdapter)

        requestLocationPermission()

        return binding.root
    }

    private fun setupOneHourRecyclerView() {
        locationRVAdapter = LocationRVAdapter(emptyList())
        binding.homeOneHourRecommendRv.adapter = locationRVAdapter
        binding.homeOneHourRecommendRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        locationRVAdapter.setMyItemCLickListener(object: LocationRVAdapter.MyItemClickListener {
            override fun onItemClick(location: Location) {
                changeLocationDetailFragment(location)
            }
        })
    }

    private fun setupPopularRecyclerView() {
        popularRVAdapter = PopularRVAdapter(emptyList())
        binding.homePopularRv.adapter = popularRVAdapter
        binding.homePopularRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        popularRVAdapter.setMyItemCLickListener(object: PopularRVAdapter.MyItemClickListener {
            override fun onItemClick(location: Location) {
                changeLocationDetailFragment(location)
            }
        })
    }

    private fun changeLocationDetailFragment(location: Location) {
        val bundle = Bundle().apply {
            putInt("locationId", location.id)
        }
        val locationDetailFragment = LocationDetailFragment().apply {
            arguments = bundle
        }

        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, locationDetailFragment)
            .addToBackStack(null)
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

    private fun requestLocationPermission() {
        when {
            ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED -> {
                getLocation()
            }
            ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) -> {
                // 권한 필요 이유를 사용자에게 설명
                binding.currentLocationTv2.text = "위치 권한이 필요합니다."
            }
            else -> {
                // 권한 요청
                requestLocationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    private fun getLocation() {
        // 위치 권한 체크
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
        } else {
            // 권한이 없으면 요청
            requestLocationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            for (location in locationResult.locations) {
                updateLocationText(location)
            }
        }
    }

    private fun getAddressFromLocation(latitude: Double, longitude: Double): String {
        return try {
            val geocoder = Geocoder(requireContext(), Locale.getDefault())

            val addresses = geocoder.getFromLocation(latitude, longitude, 1) // 최대 1개의 주소 가져오기
            if (addresses != null && addresses.isNotEmpty()) {
                addresses[0].getAddressLine(0) // 전체 주소 (도로명 주소 포함)
            } else {
                "주소를 찾을 수 없습니다."
            }
        } catch (e: Exception) {
            e.printStackTrace()
            "주소 변환 중 오류 발생: ${e.message}"
        }
    }

    private fun updateLocationText(location: android.location.Location) {
        val latitude = location.latitude
        val longitude = location.longitude
        val address = getAddressFromLocation(latitude, longitude)

        val text = address
        binding.currentLocationTv2.text = text
    }
}