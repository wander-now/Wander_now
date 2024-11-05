package com.example.wandernow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wandernow.databinding.ItemReviewBinding

class LocationDetailRVAdapter(private var locationDetail: ArrayList<LocationDetail>): RecyclerView.Adapter<LocationDetailRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): LocationDetailRVAdapter.ViewHolder {

        val binding:ItemReviewBinding = ItemReviewBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder:LocationDetailRVAdapter.ViewHolder, position: Int) {
        holder.bind(locationDetail[position])
    }

    override fun getItemCount(): Int {
        return locationDetail.size
    }

    inner class ViewHolder(val binding: ItemReviewBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(locationDetail: LocationDetail){
            binding.reviewProfileIv.setImageResource(locationDetail.profileImg!!)
            binding.reviewNameTv.text= locationDetail.name
            binding.reviewTextTv.text = locationDetail.review
        }

    }
}