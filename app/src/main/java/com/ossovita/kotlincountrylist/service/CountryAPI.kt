package com.ossovita.kotlincountrylist.service

import com.ossovita.kotlincountrylist.model.Country
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST

interface CountryAPI {
    //Burada retrofitte hangi işlemleri yapacağımızı yazacağız
    //https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json
    //BASE_URL -> https://raw.githubusercontent.com
    //EXT -> atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json

    //GET,POST
    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getCountries(): Single<List<Country>>


}