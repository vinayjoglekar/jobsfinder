package com.jovinz.jobsfindingapp.ui.activity

import android.os.Bundle
import com.jovinz.jobsfindingapp.R
import dagger.android.support.DaggerAppCompatActivity


class MainActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


}