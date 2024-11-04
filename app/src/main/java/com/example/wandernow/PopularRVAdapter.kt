package com.example.wandernow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wandernow.databinding.ItemOneHourRecommendBinding
import com.example.wandernow.databinding.ItemPopularBinding

class PopularRVAdapter(private var locationList: ArrayList<Location>): RecyclerView.Adapter<PopularRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PopularRVAdapter.ViewHolder {
        val binding: ItemPopularBinding = ItemPopularBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularRVAdapter.ViewHolder, position: Int) {
        holder.bind(locationList[position])
    }

    override fun getItemCount(): Int = locationList.size

    inner class ViewHolder(val binding: ItemPopularBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(location: Location) {
            binding.itemPopularMarkerTv.text = location.name
            binding.itemPopularStarTv.text = location.star.toString()
            binding.itemPopularTagTv1.text = location.tag1
            binding.itemPopularTagTv2.text = location.tag2
            binding.itemPopularTagTv2.text = location.tag2
            binding.itemPopularCoverImgIv.setImageResource(location.coverImg!!)
        }
    }
}