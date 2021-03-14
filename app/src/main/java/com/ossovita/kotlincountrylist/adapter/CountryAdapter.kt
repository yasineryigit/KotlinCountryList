package com.ossovita.kotlincountrylist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ossovita.kotlincountrylist.R
import com.ossovita.kotlincountrylist.databinding.ItemCountryBinding
import com.ossovita.kotlincountrylist.model.Country
import com.ossovita.kotlincountrylist.util.downloadFromUrl
import com.ossovita.kotlincountrylist.util.placeHolderProgressBar
import com.ossovita.kotlincountrylist.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.item_country.view.*

class CountryAdapter(val countryList: ArrayList<Country>): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(),CountryClickListener {

    class CountryViewHolder(var view: ItemCountryBinding): RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
       var inflater = LayoutInflater.from(parent.context)
       //val view = inflater.inflate(R.layout.item_country,parent,false)
       val view = DataBindingUtil.inflate<ItemCountryBinding>(inflater,R.layout.item_country,parent,false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.view.country=countryList[position]
        holder.view.listener=this
        /*holder.view.name.text=countryList[position].countryName
      holder.view.region.text=countryList[position].countryRegion

      holder.view.setOnClickListener{
          //Seçilen objenin uuidsi olunca parametre olarak onu göndereceğiz
          val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryList[position].uuid)
          Navigation.findNavController(it).navigate(action)
      }
      holder.view.imageView.downloadFromUrl(countryList[position].imageUrl, placeHolderProgressBar(holder.view.context))

*/

    }
    //Verilerde değişme olduğunda countryList'i gelen parametreyle güncelliyoruz
    fun updateCountryList(newCountryList:List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

    override fun onCountryClicked(v: View) {
        super.onCountryClicked(v)
        val uuid = v.countryUuidText.text.toString().toInt()
        val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }
}