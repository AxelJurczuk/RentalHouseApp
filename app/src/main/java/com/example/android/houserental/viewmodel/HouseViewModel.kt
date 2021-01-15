package com.example.android.houserental.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.houserental.data.HouseDataSet
import com.example.android.houserental.data.Result
import com.example.android.houserental.domain.model.House

class HouseViewModel : ViewModel() {

    private val listData = MutableLiveData<Result>()
    private val houseDataSet = HouseDataSet()
    private var listResult:List<House> = emptyList()

    init {
        getHouseList()
    }

    private fun getHouseList() {
        houseDataSet.getHouseList(object : HouseDataSet.OnResultCallBack {
            override fun onResult(result: Result) {
                if (result is Result.Success) {
                    val sortedList = result.list.sortedBy{ it.price }
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
}