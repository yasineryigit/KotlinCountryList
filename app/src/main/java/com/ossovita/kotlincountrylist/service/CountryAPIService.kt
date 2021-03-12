package com.ossovita.kotlincountrylist.service

import com.ossovita.kotlincountrylist.model.Country
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountryAPIService {
    //BASE_URL -> https://raw.githubusercontent.com
    //EXT -> atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json

    private val BASE_URL = "https://raw.githubusercontent.com"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CountryAPI::class.java)
    //Servisin içinde api fonksiyonunu kullanmış olduk
    fun getData() : Single<List<Country>>{
        return api.getCountries()
    }


}