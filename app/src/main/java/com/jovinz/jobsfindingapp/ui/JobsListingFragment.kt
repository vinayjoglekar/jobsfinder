package com.jovinz.jobsfindingapp.ui

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jovinz.jobsfindingapp.R
import com.jovinz.jobsfindingapp.data.ResultData
import com.jovinz.jobsfindingapp.data.jobs.JobsByLangResponseItem
import com.jovinz.jobsfindingapp.di.viewmodels.ViewModelsProviderFactory
import com.jovinz.jobsfindingapp.ui.viewmodels.JobsViewModel
import com.jovinz.jobsfindingapp.utils.VerticalSpacingItemDecoration
import dagger.android.support.DaggerFragment
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_jobs_listing.*
import javax.inject.Inject


class JobsListingFragment : DaggerFragment(R.layout.fragment_jobs_listing) {
//    private lateinit var navController: NavController

    private lateinit var jobsViewModel: JobsViewModel

    @Inject
    lateinit var adapter: JobsRecyclerViewAdapter

    @Inject
    lateinit var verticalSpacingItemDecoration: VerticalSpacingItemDecoration

    @Inject
    lateinit var viewModelsProviderFactory: ViewModelsProviderFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerviewJobs.addItemDecoration(verticalSpacingItemDecoration)
        recyclerviewJobs.adapter = adapter
        setUpViewModel()
        setObservers()

//        navController = findNavController(view)
//        abc.setOnClickListener {
//            val data = DataToSend("Here is the data to send")
//            val bundle = bundleOf(
//                "texttoshow" to abc.text,
//                "data" to data
//            )
//
//            navController.navigate(R.id.list_to_details, bundle)
//        }
    }

    private fun setObservers() {
        jobsViewModel.getJobsData().observe(viewLifecycleOwner, Observer { value ->
            when (value) {
                is ResultData.Loading -> {
                    progressJobsListing.visibility = View.VISIBLE
                }
                is ResultData.Success -> {
                    progressJobsListing.visibility = View.GONE
                    Toast.makeText(context, "inside JobsListingFragment", Toast.LENGTH_LONG).show()
                    Log.d("MainActivity", value.data?.size.toString())
                    value.data?.let { adapter.setJobs(it) }
                }
            }
        })
    }

    private fun setUpViewModel() {
        jobsViewModel =
            ViewModelProvider(this, viewModelsProviderFactory)[JobsViewModel::class.java]
    }

}

@Parcelize
data class DataToSend(var string: String) : Parcelable