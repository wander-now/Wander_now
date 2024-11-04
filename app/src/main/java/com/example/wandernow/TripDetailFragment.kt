package com.example.wandernow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wandernow.databinding.FragmentTripdetailBinding

class TripDetailFragment: Fragment() {
    lateinit var binding:FragmentTripdetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTripdetailBinding.inflate(inflater, container, false)

        return binding.root
    }
}