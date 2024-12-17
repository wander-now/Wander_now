package com.example.wandernow.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.wandernow.Triplist
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TripDetailRepository{
    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("triplist")

    fun getTriplists(): LiveData<List<Triplist>> {
        val triplistLiveData = MutableLiveData<List<Triplist>>()

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val tripLists = mutableListOf<Triplist>()
                for (childSnapshot in snapshot.children) {
                    val triplist = childSnapshot.getValue(Triplist::class.java)
                    if (triplist != null) {
                        tripLists.add(triplist)
                    }
                }
                triplistLiveData.postValue(tripLists)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("TripListRepository", "Database error: ${error.message}")
            }
        })

        return triplistLiveData
    }

    fun getTripListById(triplistId: Int, callback: (Triplist?) -> Unit) {
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (childSnapshot in snapshot.children) {
                    val triplist = childSnapshot.getValue(Triplist::class.java)

                    if (triplist != null && triplist.id == triplistId) {
                        callback(triplist)
                        return
                    }
                }
                callback(null)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("TripListRepository", "Error fetching location: ${error.message}")
                callback(null)
            }
        })
    }
}
