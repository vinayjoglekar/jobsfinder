package com.jovinz.jobsfindingapp.ui

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.jovinz.jobsfindingapp.R
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_jobs_listing.*


class JobsListingFragment : Fragment(R.layout.fragment_jobs_listing) {
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController(view)
        abc.setOnClickListener {
            val data = DataToSend("Here is the data to send")
            val bundle = bundleOf(
                "texttoshow" to abc.text,
                "data" to data
            )

            navController.navigate(R.id.list_to_details, bundle)
        }
    }

}

@Parcelize
data class DataToSend(var string: String) : Parcelable