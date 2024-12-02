package com.example.wandernow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.wandernow.databinding.FragmentAccSettingsBinding

class AccSettingsFragment : Fragment() {

    var binding: FragmentAccSettingsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccSettingsBinding.inflate(inflater)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.button1?.setOnClickListener {
            findNavController().navigate(R.id.action_accSettingsFragment_to_securityFragment)
        }
        binding?.button2?.setOnClickListener {
            findNavController().navigate(R.id.action_accSettingsFragment_to_personalInfoFragment)
        }
        binding?.button3?.setOnClickListener {
            findNavController().navigate(R.id.action_accSettingsFragment_to_myReviewsFragment)
        }
    }

}