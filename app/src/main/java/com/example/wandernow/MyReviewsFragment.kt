package com.example.wandernow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wandernow.databinding.FragmentMyReviewsBinding



class MyReviewsFragment : Fragment() {
    var binding: FragmentMyReviewsBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyReviewsBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.imageButton?.setOnClickListener{
            findNavController().navigate(R.id.action_myReviewsFragment_to_accSettingsFragment)
        }

        binding?.recyclerView?.layoutManager = LinearLayoutManager(context)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}