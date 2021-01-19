package com.example.android.houserental.viewmodel

import android.location.Location
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.houserental.data.HouseDataSet
import com.example.android.houserental.data.Result
import com.example.android.houserental.domain.model.House
import java.math.RoundingMode
import kotlin.math.*

class HouseViewModel : ViewModel() {

    private val listData = MutableLiveData<Result>()
    private val houseDataSet = HouseDataSet()
    private var listResult:List<House> = emptyList()
    private var location:Location?=null

    init {
        getHouseList()
    }

    private fun getHouseList() {
        houseDataSet.getHouseList(object : HouseDataSet.OnResultCallBack {
            override fun onResult(result: Result) {
                if (result is Result.Success) {
                    val sortedList = result.list.sortedBy { it.price }
                    val destination = Location("destination")
                    for (item in sortedList.indices) {
                        destination.latitude = sortedList[item].latitude
                        destination.longitude = sortedList[item].longitude
                        /*
                        sortedList[item].distance = "${location?.distanceTo(destination).toString()} km"
                         */
                        var distanceNow = 0.0
                        if (location != null) {
                            distanceNow = convertToKilometers(
                                destination.latitude,
                                location?.latitude ?: 0.0,
                                destination.longitude,
                                location?.longitude ?: 0.0
                            ) }
                        //else: in null case could potentially show a dialog to turn on gps to get distance
                        sortedList[item].distance = "${distanceNow.toBigDecimal().setScale(1, RoundingMode.UP)} km"
                    }
                    result.list = sortedList
                    listResult = result.list
                }
                listData.value = result
            }
        })
    }

    fun getHouseListLiveData(): LiveData<Result> {
        return listData
    }

    fun setFilteredHouseListLiveData(query: String?) {
        val filteredList = listResult.filter { it.zip.contains(query!!) }
        val newResult = Result.Success(filteredList)
        listData.value= newResult
    }
    fun setLocation(currentLocation: Location?){
        location=currentLocation
    }

    private fun convertToKilometers(lat1:Double, lat2:Double, long1:Double, long2:Double):Double{
        val latitudeOne= Math.toRadians(lat1)
        val latitudeTwo= Math.toRadians(lat2)
        val longitudeOne= Math.toRadians(long1)
        val longitudeTwo= Math.toRadians(long2)

        val dLon = longitudeTwo-longitudeOne
        val dLat = latitudeTwo-latitudeOne
        val a = sin(dLat / 2).pow(2.0) + cos(lat1) * cos(lat2) * sin(dLon / 2)
            .pow(2.0)
        val c = 2 * asin(sqrt(a))
        // Radius of earth in kilometers
        val r = 6371
        return(c*r)
    }

}