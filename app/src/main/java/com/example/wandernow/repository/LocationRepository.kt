package com.example.wandernow.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.wandernow.dataclass.Location
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LocationRepository {
    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("location")

    fun getLocations(): LiveData<List<Location>> {
        val locationsLiveData = MutableLiveData<List<Location>>()

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val locationsList = mutableListOf<Location>()
                for (childSnapshot in snapshot.children) {
                    val location = childSnapshot.getValue(Location::class.java)
                    if (location != null) {
                        locationsList.add(location)
                    }
                }
                locationsLiveData.postValue(locationsList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("LocationRepository", "Database error: ${error.message}")
            }
        })

        return locationsLiveData
    }

    fun getLocationById(locationId: Int, callback: (Location?) -> Unit) {
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (childSnapshot in snapshot.children) {
                    val location = childSnapshot.getValue(Location::class.java)
                    // ID가 일치하는지 확인
                    if (location != null && location.id == locationId) {
                        callback(location) // ID가 일치하는 경우
                        return
                    }
                }
                callback(null) // ID가 일치하는 경우가 없으면 null 반환
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("LocationRepository", "Error fetching location: ${error.message}")
                callback(null)
            }
        })
    }
}
