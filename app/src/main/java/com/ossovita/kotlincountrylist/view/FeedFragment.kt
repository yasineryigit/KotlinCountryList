package com.ossovita.kotlincountrylist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ossovita.kotlincountrylist.R
import com.ossovita.kotlincountrylist.adapter.CountryAdapter
import com.ossovita.kotlincountrylist.model.Country
import com.ossovita.kotlincountrylist.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*


class FeedFragment : Fragment() {
    //private final FeedViewModel viewModel; aşağıdaki bu demektir
    private lateinit var viewModel : FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        countryList.layoutManager = LinearLayoutManager(context)
        countryList.adapter = countryAdapter

        val myString = "James"

        swipeRefreshLayout.setOnRefreshListener {
            countryList.visibility=View.INVISIBLE
            countryError.visibility=View.GONE
            countryLoading.visibility=View.VISIBLE
            viewModel.refreshData()
            swipeRefreshLayout.isRefreshing=false

        }



        /*
        fragment_button.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment();
            Navigation.findNavController(view).navigate(action);

        }*/

        observeLiveData()
    }

    //veri çekmek için bir metod
    private fun observeLiveData(){
        //viewModel'dan gelen countries listesini dinliyoruz
        viewModel.countries.observe(viewLifecycleOwner, Observer {countries->
            //countries boş değilse visible yap
            countries?.let {
                countryList.visibility=View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }
        })
        //viewModel'den gelen countryError boolean'ını dinliyoruz.
        //Lifecycleowner parametresi olarak viewlifecycleowner veriyoruz
        viewModel.countryError.observe(viewLifecycleOwner, Observer {error->
            error?.let {
                if(it){//hata varsa hata mesajını göster
                    countryError.visibility=View.VISIBLE
                }else{
                    countryError.visibility=View.GONE
                }
            }
        })
        //viewModel'dan gelen countryLoading boolean'ını dinliyoruz
        viewModel.countryLoading.observe(viewLifecycleOwner, Observer {loading->
            loading?.let {
                if(it){
                    countryLoading.visibility=View.VISIBLE
                    countryList.visibility=View.GONE
                    countryError.visibility=View.GONE
                }else{
                    countryLoading.visibility=View.GONE
                }
            }
        })
    }


}