package com.example.wandernow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wandernow.databinding.FragmentTriplistBinding
import com.example.wandernow.viewmodel.TripDetailViewModel


class TripListFragment :Fragment(){
    lateinit var binding: FragmentTriplistBinding
    private val viewModel:TripDetailViewModel by activityViewModels() // viewModel의 객체를 Fragment로 가져올 때 사용
    private lateinit var triplistRVAdapter : TriplistRVAdapter

    var bundle = Bundle()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTriplistBinding.inflate(inflater,container,false)

        binding.addListBtn.setOnClickListener{
            Toast.makeText(requireContext(),"Button Clicked!!", Toast.LENGTH_SHORT).show()
        }

        setupTripListRecyclerView()
        observeTripLists()

        return binding.root
    }

    private fun setupTripListRecyclerView() {
        triplistRVAdapter = TriplistRVAdapter(emptyList())
        binding.tripRV.adapter = triplistRVAdapter
        binding.tripRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        triplistRVAdapter.setMyItemClickListener(object:TriplistRVAdapter.MyItemClickListener{
            override fun onItemClick(triplist: Triplist) {
                changeTripDetailFragment(triplist)
            }
        })
    }

    private fun changeTripDetailFragment(triplist: Triplist) {
        val bundle = Bundle().apply {
            putInt("triplistId", triplist.id)
        }
        val tripDetailFragment = TripDetailFragment().apply {
            arguments = bundle
        }

        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, tripDetailFragment)
            .addToBackStack(null)
            .commitAllowingStateLoss()

    }

    private fun observeTripLists() {
        viewModel.getTriplists().observe(viewLifecycleOwner) { triplists ->

            triplistRVAdapter.updateTriplists(triplists)
        }

    }
}
