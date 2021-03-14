package com.ossovita.kotlincountrylist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Country (
    @ColumnInfo(name="name")//oluşturulacak sütunların değerlerini burada verebiliyoruz
    @SerializedName("name")
    val countryName: String?,

    @ColumnInfo(name="region")//for Database
    @SerializedName("region")//for API
    val countryRegion: String?,

    @ColumnInfo(name="capital")
    @SerializedName("capital")
    val countryCapital: String?,

    @ColumnInfo(name="currency")
    @SerializedName("currency")
    val countryCurrency: String?,

    @ColumnInfo(name="language")
    @SerializedName("language")
    val countryLanguage: String?,

    @ColumnInfo(name="flag")
    @SerializedName("flag")
    val imageUrl: String?
)
{
    @PrimaryKey(autoGenerate = true) //otomatik olarak primary key atansın mı: true
    var uuid: Int =0
}
