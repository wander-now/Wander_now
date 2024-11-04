package com.example.wandernow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wandernow.repository.LocationRepository
import java.io.UncheckedIOException

const val UNCHECKED_LOCATION = "Gapyeong"

class LocationViewModel:ViewModel() {
    private val _location = MutableLiveData<String>(UNCHECKED_LOCATION)
    val location : LiveData<String> get() = _location

    private val repository = LocationRepository()
    init {
        repository.observeLocation(_location)
    }
}