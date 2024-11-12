package com.example.wandernow.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.wandernow.Location
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class LocationRepository {
    fun getData() : LiveData<MutableList<Location>> {

        val mutableData = MutableLiveData<MutableList<Location>>()
        val database = Firebase.database("https://console.firebase.google.com/project/wandernow-4df68/database/wandernow-4df68-default-rtdb/data/~2F?hl=ko")
        val myRef = database.getReference("location")

        myRef.addValueEventListener(object : ValueEventListener {

            val listData : MutableList<Location> = mutableListOf<Location>()

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){
                    listData.clear() //실시간 데이터 업데이트 시 리사이클러뷰 데이터 중복 방지

                    for ( userSnapshot in snapshot.children ){
                        val getData = userSnapshot.getValue(Location::class.java)
                        listData.add(getData!!)

                        mutableData.value = listData
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
        return mutableData

    }
}