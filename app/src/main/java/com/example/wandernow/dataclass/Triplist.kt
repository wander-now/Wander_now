package com.example.wandernow

data class Triplist (
    val id:Int = 0,
    val date: String = "",
    val Img1:String? = null,
    val Img2: String? = null,
    val Img3: String? = null,
    var coverImg:String? = null,
    val location: String = "",
    val memo:String = "",
    val with: String ="",
    val weather: String = "",
    val detail: String = ""
)