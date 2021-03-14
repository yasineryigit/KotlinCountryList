package com.ossovita.kotlincountrylist.util

import android.app.DownloadManager
import android.content.Context
import android.widget.ImageView
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ossovita.kotlincountrylist.R

//Bu metod ImageView objesine bağlanacak
fun ImageView.downloadFromUrl(url: String?, progressDrawable: CircularProgressDrawable){

    val options = RequestOptions()
            .placeholder(progressDrawable)
            .error(R.mipmap.ic_launcher)
    Glide.with(context)
            .setDefaultRequestOptions(options)
            .load(url)
            .into(this)//hangi imageView'a koyulacağını söylüyoruz

}

fun placeHolderProgressBar(context: Context):CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth=8f
        centerRadius=40f
        start()
    }
}

@BindingAdapter("android:downloadUrl")
fun downloadImage(view: ImageView,url:String?){
    view.downloadFromUrl(url, placeHolderProgressBar(view.context))
}







//Extension
/*
fun String.MyExtension(myParameter: String){
    println(myParameter)
}*/

