package com.example.wandernow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.wandernow.databinding.FragmentTripdetailBinding
import com.example.wandernow.viewmodel.TripDetailViewModel

class TripDetailFragment: Fragment() {

    lateinit var binding:FragmentTripdetailBinding
    private val viewModel: TripDetailViewModel by activityViewModels()

    private lateinit var imageView1: ImageView
    private lateinit var imageView2: ImageView
    private lateinit var imageView3: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTripdetailBinding.inflate(inflater, container, false)

        binding.tripDetailBackBtn.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        imageView1 = binding.hsIv1
        imageView2 = binding.hsIv2
        imageView3 = binding.hsIv3

        val triplistId = arguments?.getInt("triplistId")

        triplistId?.let { id ->
            viewModel.getTripListById(id) { triplist ->
                if (triplist != null) {
                    displayLocationDetails(triplist) // 위치 정보를 UI에 표시
                } else {
                    Log.e("TripLisstDetailFragment", "Triplist not found")
                }
            }
        }

        return binding.root
    }

    private fun displayLocationDetails(triplist: Triplist) {
        binding.tripDetailMemoTextTv.text = triplist.memo
        binding.tripDetailWithTextTv.text = triplist.with
        binding.tripDetailLocationDetailTextTv.text = triplist.detail
        binding.tripDetailWeatherIc.text = triplist.weather
        binding.tripDetailLocationTv.text = triplist.location
        binding.tripDetailLocationDetailTextTv.text = triplist.location

        if(!triplist.Img1.isNullOrEmpty()) {
            Glide.with(this)
                .load(triplist.Img1)
                .into(imageView1)
        }
        if(!triplist.Img2.isNullOrEmpty()) {
            Glide.with(this)
                .load(triplist.Img2)
                .into(imageView2)
        }
        if(!triplist.Img3.isNullOrEmpty()) {
            Glide.with(this)
                .load(triplist.Img3)
                .into(imageView3)
        }
    }
}