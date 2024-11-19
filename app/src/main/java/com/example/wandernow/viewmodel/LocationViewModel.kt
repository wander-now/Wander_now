package com.example.wandernow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.wandernow.Location
import com.example.wandernow.repository.LocationRepository


class LocationViewModel() : ViewModel() {
    private val repository = LocationRepository()
    val locations: LiveData<List<Location>> = repository.getLocations()
}
