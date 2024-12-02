package com.example.wandernow.homefragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wandernow.R
import com.example.wandernow.databinding.ItemPopularBinding
import com.example.wandernow.dataclass.Location

class PopularRVAdapter(
    private var popularLocations: List<Location>
): RecyclerView.Adapter<PopularRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPopularBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(popularLocations[position])
    }

    override fun getItemCount(): Int {
        return popularLocations.size.coerceAtMost(5)
    }

    inner class ViewHolder(val binding: ItemPopularBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(location: Location) {
            binding.itemPopularMarkerTv.text = location.name
            binding.itemPopularStarTv.text = location.star.toString()
            binding.itemPopularTagTv1.text = location.tag1
            binding.itemPopularTagTv2.text = location.tag2
            binding.itemPopularTagTv3.text = location.tag3

            val imageUrl = location.imgPath
            if (!imageUrl.isNullOrEmpty()) {
                Glide.with(binding.root.context)
                    .load(imageUrl)
                    .into(binding.itemPopularCoverImgIv)
            } else {
                binding.itemPopularCoverImgIv.setImageResource(R.drawable.img_gapyeong) // 기본 이미지 설정
            }
        }
    }

    fun updatePopularLocations(newLocation: List<Location>) {
        this.popularLocations = newLocation
        notifyDataSetChanged()
    }
}