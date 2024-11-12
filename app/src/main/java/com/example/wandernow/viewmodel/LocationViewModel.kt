package com.example.wandernow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wandernow.Location
import com.example.wandernow.repository.LocationRepository
import com.google.firebase.database.core.Repo
import java.io.UncheckedIOException

const val UNCHECKED_LOCATION = "Gapyeong"

class LocationViewModel:ViewModel() {
    private val repo = LocationRepository()
    fun fetchData() : LiveData<MutableList<Location>> {
        val mutableData = MutableLiveData<MutableList<Location>>()
        repo.getData().observeForever {
            mutableData.value = it
        }
        return mutableData

    }
}