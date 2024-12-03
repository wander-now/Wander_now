//package com.example.wandernow.repository
//
//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.example.wandernow.dataclass.Triplist
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ValueEventListener
//
//class TripListRepository {
//    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("triplist")
//
//    fun getTripList(): LiveData<List<Triplist>> {
//        val triplistLiveData = MutableLiveData<List<Triplist>>()
//
//        database.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val tripList = mutableListOf<Triplist>()
//                for (childSnapshot in snapshot.children) {
//                    val triplist = childSnapshot.getValue(Triplist::class.java)
//                    if (triplist != null) {
//                        tripList.add(triplist)
//                    }
//                }
//                triplistLiveData.postValue(tripList)
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.e("LocationRepository", "Database error: ${error.message}")
//            }
//        })
//
//        return triplistLiveData
//    }
//}
