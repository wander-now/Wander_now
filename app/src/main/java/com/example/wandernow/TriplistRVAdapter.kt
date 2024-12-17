package com.example.wandernow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wandernow.databinding.ItemRecordBinding

class TriplistRVAdapter(private var tripLists: List<Triplist>)
    :RecyclerView.Adapter<TriplistRVAdapter.ViewHolder>() {

    interface MyItemClickListener{
        fun onItemClick(triplist: Triplist)
    }

    private lateinit var myItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        myItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TriplistRVAdapter.ViewHolder {
        val binding: ItemRecordBinding = ItemRecordBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TriplistRVAdapter.ViewHolder, position: Int) {
        holder.bind(tripLists[position])
        holder.itemView.setOnClickListener {
            myItemClickListener.onItemClick(tripLists[position])
        }
    }

    override fun getItemCount(): Int {
        return tripLists.size
    }

    inner class ViewHolder (val binding:ItemRecordBinding ): RecyclerView.ViewHolder(binding.root){

        fun bind(triplist: Triplist){
            binding.tripListDateTv.text = triplist.date
            binding.tripListLocationTv.text = triplist.location

            val imageUrl = triplist.coverImg
            if (!imageUrl.isNullOrEmpty()) {
                Glide.with(binding.root.context)
                    .load(imageUrl)
                    .into(binding.tripListImgIv)
            } else {
                binding.tripListImgIv.setImageResource(R.drawable.img_gapyeong) // 기본 이미지 설정
            }
        }

    }
    fun updateTriplists(newTripList: List<Triplist>) {
        this.tripLists= newTripList
        notifyDataSetChanged()
    }
}