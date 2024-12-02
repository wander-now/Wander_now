package com.example.wandernow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wandernow.databinding.FragmentHomeBannerBinding

class HomeBannerFragment(val imgRes : Int) : Fragment() {
    lateinit var  binding : FragmentHomeBannerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBannerBinding.inflate(inflater, container ,false)

        binding.homeBanner.setImageResource(imgRes)
        return binding.root
    }
}