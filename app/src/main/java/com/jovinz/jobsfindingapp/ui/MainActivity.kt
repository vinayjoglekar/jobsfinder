package com.jovinz.jobsfindingapp.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.jovinz.jobsfindingapp.R
import com.jovinz.jobsfindingapp.di.viewmodels.ViewModelsProviderFactory
import com.jovinz.jobsfindingapp.ui.viewmodels.JobsViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    private lateinit var jobsViewModel: JobsViewModel

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager

    @Inject
    lateinit var viewModelsProviderFactory: ViewModelsProviderFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestManager.load(logo).into(sampleImage)
        jobsViewModel =
            ViewModelProvider(this, viewModelsProviderFactory)[JobsViewModel::class.java]
        jobsViewModel.getJobsData()
    }
}