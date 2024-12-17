package com.example.wandernow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.wandernow.Triplist
import com.example.wandernow.repository.TripDetailRepository

class TripDetailViewModel: ViewModel() {
    private val repository = TripDetailRepository()

    fun getTriplists(): LiveData<List<Triplist>>{
        return repository.getTriplists()
    }

    fun getTripListById(tripListId: Int, callback: (Triplist?)->Unit) {
        repository.getTripListById(tripListId,callback)
    }

}
