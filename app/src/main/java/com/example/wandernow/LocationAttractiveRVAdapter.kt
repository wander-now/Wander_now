package com.example.wandernow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wandernow.databinding.LocationAttractionViewBinding

class LocationAttractiveRVAdapter(private var locationAttractive: ArrayList<LocationAttractive>): RecyclerView.Adapter<LocationAttractiveRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): LocationAttractiveRVAdapter.ViewHolder {

        val binding: LocationAttractionViewBinding = LocationAttractionViewBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(locationAttractive[position])
    }


    override fun getItemCount(): Int {
        return locationAttractive.size
    }

    inner class ViewHolder(val binding: LocationAttractionViewBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(locationAttractive: LocationAttractive){
            binding.namiIslandImgIv.setImageResource(locationAttractive.locationImg!!)
            binding.locationName.text= locationAttractive.locationName
        }

    }
}