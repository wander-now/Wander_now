package com.example.wandernow.homefragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wandernow.R
import com.example.wandernow.databinding.ItemOneHourRecommendBinding
import com.example.wandernow.dataclass.Location

class LocationRVAdapter(private var locations: List<Location>)
    : RecyclerView.Adapter<LocationRVAdapter.ViewHolder>() {


    interface MyItemClickListener{
        fun onItemClick(location: Location)
    }

    private lateinit var myItemClickListener: MyItemClickListener

    fun setMyItemCLickListener(itemClickListener: MyItemClickListener){
        myItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemOneHourRecommendBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(locations[position])
        holder.itemView.setOnClickListener {
            myItemClickListener.onItemClick(locations[position])
        }
    }

    override fun getItemCount(): Int {
        return locations.size.coerceAtMost(5)
    }

    inner class ViewHolder(val binding: ItemOneHourRecommendBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(location: Location) {
            binding.itemTimeTv2.text = location.time.toString()
            binding.itemMarkerTv.text = location.name
            binding.itemStarTv.text = location.star.toString()
            binding.itemTagTv1.text = location.tag1
            binding.itemTagTv2.text = location.tag2
            binding.itemTagTv3.text = location.tag3

            val imageUrl = location.imgPath
            if (!imageUrl.isNullOrEmpty()) {
                Glide.with(binding.root.context)
                    .load(imageUrl)
                    .into(binding.itemCoverImgIv)
            } else {
                binding.itemCoverImgIv.setImageResource(R.drawable.img_gapyeong) // 기본 이미지 설정
            }
        }
    }

    fun updateLocations(newLocation: List<Location>) {
        this.locations = newLocation
        notifyDataSetChanged()
    }
}
