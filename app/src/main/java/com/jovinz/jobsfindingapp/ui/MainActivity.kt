package com.jovinz.jobsfindingapp.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import com.bumptech.glide.RequestManager
import com.jovinz.jobsfindingapp.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestManager.load(logo).into(sampleImage)

    }
}