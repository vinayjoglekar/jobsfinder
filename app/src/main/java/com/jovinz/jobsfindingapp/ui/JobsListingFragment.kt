package com.jovinz.jobsfindingapp.ui

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.jovinz.jobsfindingapp.R
import com.jovinz.jobsfindingapp.data.ResultData
import com.jovinz.jobsfindingapp.di.viewmodels.ViewModelsProviderFactory
import com.jovinz.jobsfindingapp.ui.viewmodels.JobsViewModel
import com.jovinz.jobsfindingapp.utils.VerticalSpacingItemDecoration
import com.jovinz.jobsfindingapp.utils.gone
import com.jovinz.jobsfindingapp.utils.visible
import dagger.android.support.DaggerFragment
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_jobs_listing.*
import javax.inject.Inject


class JobsListingFragment : DaggerFragment(R.layout.fragment_jobs_listing) {
    private lateinit var navController: NavController

    private lateinit var jobsViewModel: JobsViewModel

    @Inject
    lateinit var adapter: JobsRecyclerViewAdapter

    @Inject
    lateinit var verticalSpacingItemDecoration: VerticalSpacingItemDecoration

    @Inject
    lateinit var viewModelsProviderFactory: ViewModelsProviderFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpViewModel()
        jobsViewModel.getJobsData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
        recyclerviewJobs.addItemDecoration(verticalSpacingItemDecoration)
        recyclerviewJobs.adapter = adapter
        setObservers()
//        abc.setOnClickListener {
//            val data = DataToSend("Here is the data to send")
//            val bundle = bundleOf(
//                "texttoshow" to abc.text,
//                "data" to data
//            )

//            navController.navigate(R.id.list_to_details, bundle)
//        }
    }

    private fun setObservers() {
        jobsViewModel.livedata.observe(viewLifecycleOwner, Observer { value ->
            when (value) {
                is ResultData.Loading -> {
                    progressJobsListing.visible()
                }
                is ResultData.Success -> {
                    progressJobsListing.gone()
                    value.data?.let {
                        adapter.setJobs(it)
                    }
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