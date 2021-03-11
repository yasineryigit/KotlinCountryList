package com.ossovita.kotlincountrylist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ossovita.kotlincountrylist.R


class FeedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*
        fragment_button.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment();
            Navigation.findNavController(view).navigate(action);

        }*/
    }


}