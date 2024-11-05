package com.example.wandernow
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wandernow.databinding.ItemLocationAttractionBinding

class LocationAttractionRVAdapter(private var locationAttraction: ArrayList<LocationAttraction>): RecyclerView.Adapter<LocationAttractionRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): LocationAttractionRVAdapter.ViewHolder {

        val binding: ItemLocationAttractionBinding = ItemLocationAttractionBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(locationAttraction[position])
    }


    override fun getItemCount(): Int {
        return locationAttraction.size
    }

    inner class ViewHolder(val binding: ItemLocationAttractionBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(locationAttraction: LocationAttraction){
            binding.locationAttractionImgIv.setImageResource(locationAttraction.locationImg!!)
            binding.locationAttractionNameTv.text= locationAttraction.locationName
        }

    }
}