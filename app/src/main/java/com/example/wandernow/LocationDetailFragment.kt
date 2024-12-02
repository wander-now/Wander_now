package com.example.wandernow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.wandernow.databinding.FragmentLocationDetailBinding
import com.example.wandernow.dataclass.Location
import com.example.wandernow.homefragment.LocationRVAdapter
import com.example.wandernow.viewmodel.LocationViewModel

class LocationDetailFragment: Fragment() {
    lateinit var binding : FragmentLocationDetailBinding
    private var locationdetailDatas = ArrayList<LocationDetail>()
    private var locationAttractionDatas = ArrayList<LocationAttraction>()
    private val viewModel: LocationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLocationDetailBinding.inflate(inflater,container,false)

        binding.locationDetailBackBtn.setOnClickListener {
            requireActivity().onBackPressed() // 이전 화면으로 돌아가기
        }


        val locationId = arguments?.getInt("locationId")
        Log.d("LocationDetailFragment", "Received locationId: $locationId")

        locationId?.let { id ->
            viewModel.getLocationById(id) { location ->
                if (location != null) {
                    displayLocationDetails(location) // 위치 정보를 UI에 표시
                } else {
                    Log.e("LocationDetailFragment", "Location not found")
                }
            }
        }

        locationdetailDatas.apply{
            add(LocationDetail(R.drawable.profile, "주니",4.0,"짚라인과 수상 레포츠를 즐기면서 스릴을 만끽했어요. 친구들과 함께라면 더욱 즐거울 것 같아요!"))
            add(LocationDetail(R.drawable.profile, "일상탈출여행자",5.0,"자전거를 타고 섬을 한 바퀴 돌았는데, 바람을 맞으며 느끼는 기분이 정말 좋았습니다. 꼭 다시 방문하고 싶어요!"))
            add(LocationDetail(R.drawable.profile, "여행하는고양이",4.0,"가평의 북한강은 정말 아름다워요. 자전거를 타며 강변을 따라 달리니 스트레스가 확 날아갔습니다"))
        }

        locationAttractionDatas.apply{
            add(LocationAttraction(R.drawable.nami_island, "남이섬"))
            add(LocationAttraction(R.drawable.morning_carm, "아침고요수목원"))
            add(LocationAttraction(R.drawable.france, "쁘티프랑스"))
        }

        val locationDetailRVAdapter = LocationDetailRVAdapter(locationdetailDatas)
        binding.locationDetailReviewRv.adapter = locationDetailRVAdapter
        binding.locationDetailReviewRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val locationAttractionRVAdapter = LocationAttractionRVAdapter(locationAttractionDatas)
        binding.locationDetailAttractionRv.adapter = locationAttractionRVAdapter
        binding.locationDetailAttractionRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        return binding.root
    }

    private fun displayLocationDetails(location: Location) {
        Log.d("LocationDetailFragment", "Location details: Name: ${location.name}, Star: ${location.star}")
        binding.locationDetailName.text = location.name
        binding.locationDetailStarTv.text = location.star.toString()
        binding.locationDetailDescription.text = location.description

        // 이미지 로딩
        if (!location.imgPath.isNullOrEmpty()) {
            Glide.with(this)
                .load(location.imgPath)
                .into(binding.locationDetailIv)
        } else {
            binding.locationDetailIv.setImageResource(R.drawable.img_gapyeong) // 기본 이미지
        }
    }

}