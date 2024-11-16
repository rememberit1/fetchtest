package com.example.fetchtest.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//the json data starts off as an array of this object
@JsonClass(generateAdapter = true)
data class OneItem(
    @Json(name = "id")
    val id: Int,
    @Json(name = "listId")
    val listId: Int,
    @Json(name = "name")
    val name: String?
)