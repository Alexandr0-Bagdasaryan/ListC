package com.example.listc.models

import androidx.lifecycle.ViewModel
import com.example.listc.data.Countries

class CountryModel:ViewModel() {
    var CountryList = mutableListOf<Countries>(
        Countries("USA","328000000","Country1"),
        Countries("China","1408000000","Country2"),
        Countries("Russia","14400000","Country3")
    )
    private var currentIndex = 0

    val getCurrentIndex:Int
        get() = currentIndex

    val currentCountryName: String
        get() = CountryList[currentIndex].name

    val currentCountryPopulation: String
        get() = CountryList[currentIndex].population

    val currentCountryDescription: String
        get() = CountryList[currentIndex].description

    fun moveToNext(){
        currentIndex = (currentIndex + 1) % CountryList.size
    }

    fun moveToPrev(){
        currentIndex = (CountryList.size + currentIndex - 1) % CountryList.size
    }
    fun addCountry(country:Countries){
        CountryList.add(country)
    }

    fun deleteCountry(index:Int){
        CountryList.removeAt(index)
    }

    fun ChangeCountry(index: Int,name:String,population:String,description:String){
        CountryList[index].name=name
        CountryList[index].population=population
        CountryList[index].description=description
    }


}