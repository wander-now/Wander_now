package com.example.wandernow.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.wandernow.Location
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class LocationRepository {
    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("location")

    fun getLocations(): LiveData<List<Location>> {
        val locationsLiveData = MutableLiveData<List<Location>>()

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val locationsList = mutableListOf<Location>()
                for (childSnapshot in snapshot.children) {
                    val location = childSnapshot.getValue(Location::class.java)
                    location?.let { locationsList.add(it)}
                }
                locationsLiveData.postValue(locationsList)
            }

            override fun onCancelled(error: DatabaseError) {
                // 에러 처리
            }
        })

        return locationsLiveData
    }

}
