package com.example.wandernow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wandernow.databinding.FragmentTriplistBinding

class TripListFragment :Fragment(){
    lateinit var binding: FragmentTriplistBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTriplistBinding.inflate(inflater, container, false)

        return binding.root
    }
}