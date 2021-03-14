package com.ossovita.kotlincountrylist.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ossovita.kotlincountrylist.model.Country
import com.ossovita.kotlincountrylist.service.CountryDatabase
import kotlinx.coroutines.launch

class CountryViewModel(application: Application): BaseViewModel(application) {
    //Bu viewModel'da sadece country objesi dinlenecek
    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom(uuid: Int){
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            val country = dao.getCountry(uuid)
            countryLiveData.value=country
        }
    }


}