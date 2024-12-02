package com.example.wandernow

import android.provider.CallLog.Locations
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemAnimator.ItemHolderInfo
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.wandernow.databinding.ItemRecordBinding
import com.example.wandernow.dataclass.Triplist

class TriplistRVAdapter(private var tripList: ArrayList<Triplist>):RecyclerView.Adapter<TriplistRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TriplistRVAdapter.ViewHolder {

        val binding: ItemRecordBinding = ItemRecordBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TriplistRVAdapter.ViewHolder, position: Int) {
        holder.bind(tripList[position])
    }

    override fun getItemCount(): Int {
        return tripList.size
    }

    inner class ViewHolder (val binding:ItemRecordBinding ): RecyclerView.ViewHolder(binding.root){

        fun bind(triplist: Triplist){
            binding.tripListDateTv.text = triplist.date
            binding.tripListImgIv.setImageResource(triplist.coverImg!!)
            binding.tripListLocationTv.text = triplist.location
        }

    }
}