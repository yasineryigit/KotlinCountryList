package com.ossovita.kotlincountrylist.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ossovita.kotlincountrylist.model.Country

@Database(entities = arrayOf(Country::class),version = 1)
abstract class CountryDatabase : RoomDatabase(){

    abstract fun countryDao() : CountryDao//bu classı kullanan sınıflar bu countryDao döndüren constructorı barındırmak zorundadır

    //Singleton/Aşağıdaki işlemlerde amacımız bu sınıfımızdan tek bir obje oluşturulması
    companion object{
        @Volatile
        private var instance : CountryDatabase?=null
        private var lock = Any()
        //instance varsa direkt döndürecek, yoksa senktronize bir şekilde bu instance oluşturacak diyoruz
        operator fun invoke(context: Context)=instance ?: synchronized(lock){//aynı anda 2 thread gelip bu objeye ulaşamasın diyorsak bunu kullanıyoruz.async'in tam tersi
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        //Database'i oluşturacağımız fonksiyon
        private fun makeDatabase(context: Context) = Room.databaseBuilder(
                context.applicationContext,CountryDatabase::class.java,"countrydatabase"
        ).build()

    }
}