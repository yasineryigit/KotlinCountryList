package com.ossovita.kotlincountrylist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ossovita.kotlincountrylist.model.Country

class CountryViewModel: ViewModel() {
    //Bu viewModel'da sadece country objesi dinlenecek
    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom(){
        val country = Country("Turkey","Asia","Ankara","TRY","Turkish","www.ss.com")
        countryLiveData.value=country
    }


}