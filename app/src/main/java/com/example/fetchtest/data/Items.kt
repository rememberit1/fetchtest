package com.example.fetchtest.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Items : ArrayList<OneItem>()