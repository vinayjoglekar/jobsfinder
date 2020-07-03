package com.jovinz.jobsfindingapp.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.jovinz.jobsfindingapp.R
import com.jovinz.jobsfindingapp.data.ResultData
import com.jovinz.jobsfindingapp.di.viewmodels.ViewModelsProviderFactory
import com.jovinz.jobsfindingapp.ui.viewmodels.JobsViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    private lateinit var jobsViewModel: JobsViewModel

    @Inject
    lateinit var viewModelsProviderFactory: ViewModelsProviderFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpViewModel()
        setObservers()

        supportFragmentManager.beginTransaction().replace(R.id.main_container, JobsListingFragment(),"").commit()
    }

    private fun setObservers() {
        jobsViewModel.getJobsData().observe(this, Observer { value ->
            when (value) {
                is ResultData.Loading -> {
                }
                is ResultData.Success -> {
                    Log.d("MainActivity", value.data?.size.toString())
                }
            }
        })
    }

    private fun setUpViewModel() {
        jobsViewModel =
            ViewModelProvider(this, viewModelsProviderFactory)[JobsViewModel::class.java]
    }
}