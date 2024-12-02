package com.example.wandernow.dataclass

data class TripDetail(
    val detail: String? = null,
    val images: List<String?> = emptyList(),
    val location: String? = null,
    val memo: String? = null,
    val weather: String? = null,
    val with: String? = null
)
