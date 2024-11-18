package com.example.wandernow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wandernow.Location
import com.example.wandernow.repository.LocationRepository
import com.google.firebase.database.FirebaseDatabase


class LocationViewModel(private val repository: LocationRepository) : ViewModel() {
    val locations: LiveData<List<Location>> = repository.getLocations()
}
