package com.ossovita.kotlincountrylist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ossovita.kotlincountrylist.model.Country

class FeedViewModel : ViewModel(){
    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        val country = Country("Turkey","Asia","Ankara","TRY","Turkish","www.ss.com");
        val country2 = Country("Italy","Europe","Roma","Euro","Italian","www.ss.com");
        val country3 = Country("Germany","Europe","Berlin","Euro","German","www.ss.com");

        val countryList = arrayListOf<Country>(country,country2,country3);
        countries.value = countryList
        countryError.value = false
        countryLoading.value = false
    }
}