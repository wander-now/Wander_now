package com.example.wandernow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wandernow.databinding.ItemOneHourRecommendBinding

class LocationRVAdapter(private var locationList: ArrayList<Location>): RecyclerView.Adapter<LocationRVAdapter.ViewHolder>() {
    interface MyItemClickListener{
        fun onItemClick(location: Location)
    }

    private lateinit var myItemClickListener: MyItemClickListener
    fun setMyItemCLickListener(itemClickListener: MyItemClickListener){
        myItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): LocationRVAdapter.ViewHolder {
        val binding: ItemOneHourRecommendBinding = ItemOneHourRecommendBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationRVAdapter.ViewHolder, position: Int) {
        holder.bind(locationList[position])
        holder.itemView.setOnClickListener{myItemClickListener.onItemClick(locationList[position])}
    }

    override fun getItemCount(): Int = locationList.size

    inner class ViewHolder(val binding: ItemOneHourRecommendBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(location: Location) {
            binding.itemTimeTv2.text = location.time
            binding.itemMarkerTv.text = location.name
            binding.itemStarTv.text = location.star.toString()
            binding.itemTagTv1.text = location.tag1
            binding.itemTagTv2.text = location.tag2
            binding.itemTagTv2.text = location.tag2
            binding.itemCoverImgIv.setImageResource(location.coverImg!!)
        }
    }
}