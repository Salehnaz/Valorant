package com.example.viewtest

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("/valorant")
    fun getData():Call<List<MyDataItem>>
}