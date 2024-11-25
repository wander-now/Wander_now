package com.example.wandernow.repository

import android.content.Intent
import android.util.Log
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.wandernow.Location
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

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
}
