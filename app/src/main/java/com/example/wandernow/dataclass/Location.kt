package com.example.wandernow.dataclass

data class Location(
    val id:Int = 0,
    val time:Int = 0,
    val name:String = "",
    val star: Double = 0.0,
    val tag1:String = "",
    val tag2:String = "",
    val tag3:String = "",
    val description:String = "",
    var imgPath: String? = null
)