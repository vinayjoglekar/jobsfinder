package com.jovinz.jobsfindingapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.jovinz.jobsfindingapp.R
import kotlinx.android.synthetic.main.fragment_job_detail.*

class JobDetailFragment : Fragment(R.layout.fragment_job_detail) {

    private lateinit var textToShow: String
    private var dataToSend: DataToSend? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        textToShow = arguments?.getString("texttoshow").toString()
        dataToSend = arguments?.getParcelable("data")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ttt.text = "$textToShow : \n${dataToSend?.string}"
    }

}