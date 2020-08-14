package com.jovinz.jobsfindingapp.ui.fragments

import android.os.Bundle
import android.view.View
import com.jovinz.jobsfindingapp.R
import dagger.android.support.DaggerFragment

class JobDetailFragment : DaggerFragment(R.layout.fragment_job_detail) {

    private lateinit var jobId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        jobId = arguments?.getString("jobId").toString()
//        textToShow = arguments?.getString("texttoshow").toString()
//        dataToSend = arguments?.getParcelable("data")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        ttt.text = "$textToShow : \n${dataToSend?.string}"
    }

}