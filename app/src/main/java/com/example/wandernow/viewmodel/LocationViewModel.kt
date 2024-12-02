package com.example.wandernow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.wandernow.dataclass.Location
import com.example.wandernow.repository.LocationRepository


class LocationViewModel() : ViewModel() {
    private val repository = LocationRepository()

    fun getLocations(): LiveData<List<Location>> {
        return repository.getLocations()
    }

}
