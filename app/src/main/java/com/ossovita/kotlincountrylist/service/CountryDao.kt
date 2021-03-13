package com.ossovita.kotlincountrylist.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ossovita.kotlincountrylist.model.Country

@Dao
interface CountryDao {
    //Data access object
    @Insert
    suspend fun insertAll(vararg countries: Country):List<Long>//burada long listesiyle primary Idleri döndürecek


    //Insert->INSERT.INTO
    //suspend->coroutine, pause&resume
    //vararg-> multiple country objects / belirsisz sayıda country objesi vereceğimiz için
    //List<Long> -> primary keys döndürür


    @Query("SELECT * FROM country") //Modelde data class adı country olduğu için
    suspend fun getAllCountries():List<Country>//bütün countryleri getir

    @Query("SELECT * FROM country WHERE uuid = :countryId") //parametre olarak gelen id'deki ülkeyi getirir
    suspend fun getCountry(countryId: Int):Country //Country döndürecek

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()


}