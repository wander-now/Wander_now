package com.example.wandernow

data class Location(
    val time:Int = 0,
    val name:String = "",
    val star: Double = 0.0,
    val tag1:String = "",
    val tag2:String = "",
    val tag3:String = "",
    var imgPath: String? = null
)