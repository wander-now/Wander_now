package com.example.wandernow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wandernow.databinding.ItemRecordBinding

class TriplistRVAdapter(private var tripList: ArrayList<Triplist>)
    :RecyclerView.Adapter<TriplistRVAdapter.ViewHolder>() {

    interface MyItemClickListener{
        fun onItemClick(triplist: Triplist)
    }

    private lateinit var myItemClickListener: MyItemClickListener

    fun setMyItemCLickListener(itemClickListener: MyItemClickListener){
        myItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TriplistRVAdapter.ViewHolder {
        val binding: ItemRecordBinding = ItemRecordBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TriplistRVAdapter.ViewHolder, position: Int) {
        holder.bind(tripList[position])
        holder.itemView.setOnClickListener {
            myItemClickListener.onItemClick(tripList[position])
        }
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