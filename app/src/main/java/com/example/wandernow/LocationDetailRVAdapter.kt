package com.example.wandernow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wandernow.databinding.ReviewCardViewBinding

class LocationDetailRVAdapter(private var locationDetail: ArrayList<LocationDetail>): RecyclerView.Adapter<LocationDetailRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): LocationDetailRVAdapter.ViewHolder {

        val binding:ReviewCardViewBinding = ReviewCardViewBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder:LocationDetailRVAdapter.ViewHolder, position: Int) {
        holder.bind(locationDetail[position])
    }

    override fun getItemCount(): Int {
        return locationDetail.size
    }

    inner class ViewHolder(val binding: ReviewCardViewBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(locationDetail: LocationDetail){
            binding.Profile.setImageResource(locationDetail.profileImg!!)
            binding.Name.text= locationDetail.name
            binding.Review.text= locationDetail.review
        }

    }
}