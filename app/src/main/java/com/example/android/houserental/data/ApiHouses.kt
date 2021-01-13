package com.example.android.houserental.data

import com.example.android.houserental.domain.model.House
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiHouses {

    //my key is:  98bww4ezuzfePCYFxJEWyszbUXc7dxRx

    @Headers("Access-Key:98bww4ezuzfePCYFxJEWyszbUXc7dxRx")
    //buscar name para ek headeer
    @GET("house")
    fun getHouses (): Call<List<House>>
}