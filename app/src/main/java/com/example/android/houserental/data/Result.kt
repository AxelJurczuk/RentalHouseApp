package com.example.android.houserental.data

import com.example.android.houserental.domain.model.House

sealed class Result{
    data class Success (var list: List<House>):Result()
    data class Failure (val error: String):Result()
}
