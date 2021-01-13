package com.example.android.houserental.data

import android.util.Log
import com.example.android.houserental.domain.model.House
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class HouseDataSet {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://intern.docker-dev.d-tt.nl/api/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val api: ApiHouses = retrofit.create(ApiHouses::class.java)

    fun getHouseList(callBack:OnResultCallBack) {

        api.getHouses().enqueue(object : Callback<List<House>> {
            override fun onResponse(call: Call<List<House>>, response: Response<List<House>>) {

                if (response.isSuccessful) {

                   val list = response.body()!!
                    callBack.onResult(Result.Success(list))

                } else {
                    callBack.onResult((Result.Failure("Something went wrong")))

                }
            }
            override fun onFailure(call: Call<List<House>>, t: Throwable) {
                callBack.onResult((Result.Failure(t.localizedMessage)))
            }
        })
    }

    interface OnResultCallBack {
        fun onResult(result:Result)
    }
}