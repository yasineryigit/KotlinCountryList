package com.ossovita.kotlincountrylist.service

import androidx.room.Database
import com.ossovita.kotlincountrylist.model.Country

@Database(entities = arrayOf(Country::class),version = 1)
abstract class CountryDatabase {
    abstract fun countryDao() : CountryDao//bu classı kullanan sınıflar bu countryDao döndüren constructorı barındırmak zorundadır

    //Singleton
    companion object{
        @Volatile
        private var instance : CountryDatabase?=null
        private var lock = Any()
        operator fun invoke()=instance ?: synchronized(lock){

        }

    }
}