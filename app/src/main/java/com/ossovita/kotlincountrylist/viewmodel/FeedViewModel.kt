package com.ossovita.kotlincountrylist.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ossovita.kotlincountrylist.model.Country
import com.ossovita.kotlincountrylist.service.CountryAPI
import com.ossovita.kotlincountrylist.service.CountryAPIService
import com.ossovita.kotlincountrylist.service.CountryDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FeedViewModel(application: Application) : BaseViewModel(application){

    private val countryApiService = CountryAPIService()//CountryAPIService oluşturuyoruz
    private val disposable = CompositeDisposable()
    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        getDataFromAPI()
    }

    private fun getDataFromAPI(){
        countryLoading.value=true
        disposable.add(
             countryApiService.getData()
                     .subscribeOn(Schedulers.newThread())
                     .observeOn(AndroidSchedulers.mainThread())
                     .subscribeWith(object : DisposableSingleObserver<List<Country>>(){
                         override fun onSuccess(t: List<Country>) {
                            showCountries(t)
                            storeInSQLite(t)

                         }

                         override fun onError(e: Throwable) {
                            countryLoading.value=false
                             countryError.value=true

                         }

                     })
        )
    }
    private fun showCountries(countryList:List<Country>){
        countries.value=countryList
        countryError.value=false
        countryLoading.value=false
    }

    private fun storeInSQLite(list: List<Country>){
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            dao.deleteAllCountries()
            val listLong = dao.insertAll(*list.toTypedArray())
            var i = 0
            while(i<list.size){
                list[i].uuid = listLong[i].toInt()
                i++
            }
            showCountries(list)

        }
    }
}