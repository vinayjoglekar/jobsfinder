package com.jovinz.jobsfindingapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.jovinz.jobsfindingapp.R
import com.jovinz.jobsfindingapp.data.Category
import com.jovinz.jobsfindingapp.data.ResultData
import com.jovinz.jobsfindingapp.di.viewmodels.ViewModelsProviderFactory
import com.jovinz.jobsfindingapp.ui.adapter.JobsPagedAdapter
import com.jovinz.jobsfindingapp.ui.adapter.JobsRecyclerViewAdapter
import com.jovinz.jobsfindingapp.ui.viewmodels.JobsViewModel
import com.jovinz.jobsfindingapp.utils.VerticalSpacingItemDecoration
import com.jovinz.jobsfindingapp.utils.gone
import com.jovinz.jobsfindingapp.utils.visible
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_jobs_listing.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.subscribe
import kotlinx.coroutines.launch
import javax.inject.Inject


class JobsListingFragment : DaggerFragment(R.layout.fragment_jobs_listing) {

    private lateinit var navController: NavController

    private lateinit var jobsViewModel: JobsViewModel

    private var category: Category? = null

    @Inject
    lateinit var adapter: JobsRecyclerViewAdapter

    @Inject
    lateinit var jobsPagedAdapter: JobsPagedAdapter

    @Inject
    lateinit var verticalSpacingItemDecoration: VerticalSpacingItemDecoration

    @Inject
    lateinit var viewModelsProviderFactory: ViewModelsProviderFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        category = arguments?.getParcelable("category")
        setUpViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        activity?.title = category?.category.toString()
        recyclerviewJobs.addItemDecoration(verticalSpacingItemDecoration)
        recyclerviewJobs.setHasFixedSize(true)
//        recyclerviewJobs.adapter = adapter
        recyclerviewJobs.adapter = jobsPagedAdapter
        adapter.setItemAction { id ->
            navController.navigate(
                R.id.list_to_details, bundleOf("jobId" to Category(id))
            )
        }
        setObservers()
    }

    private fun setObservers() {
        jobsViewModel.fetchJobs(category?.category.toString())
            .observe(viewLifecycleOwner, Observer { value ->
                when (value) {
                    is ResultData.Loading -> {
//                        tvNoJobs.gone()
//                        progressJobsListing.visible()
                    }
                    is ResultData.Success -> {
//                        progressJobsListing.gone()
//                        if (value.data.isNullOrEmpty()) {
//                            tvNoJobs.text = getString(R.string.no_jobs_available_for_this_category)
//                            tvNoJobs.visible()
//                            return@Observer
//                        }
//                        value.data.let {
//                            adapter.setJobs(it)
//                        }
                    }
                    is ResultData.Failed -> {
//                        tvNoJobs.text = getString(R.string.error_mmsg)
//                        tvNoJobs.visible()
//                        progressJobsListing.gone()
                    }
                }
            })

        lifecycleScope.launch {
            jobsViewModel.fetchPagedJobs(category?.category.toString()).collect {
                jobsPagedAdapter.submitData(it)
            }
        }

        jobsPagedAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh == LoadState.Loading) {
                tvNoJobs.gone()
                progressJobsListing.visible()
            } else {
                progressJobsListing.gone()

                // getting the error
                val error = when {
                    loadState.prepend is LoadState.Error ->
                        loadState.prepend as LoadState.Error
                    loadState.append is LoadState.Error ->
                        loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error ->
                        loadState.refresh as LoadState.Error
                    else -> null
                }
                error?.let {
                    tvNoJobs.text = getString(R.string.error_mmsg)
                    tvNoJobs.visible()
                }
            }
        }
    }

    private fun setUpViewModel() {
        jobsViewModel =
            ViewModelProvider(this, viewModelsProviderFactory)[JobsViewModel::class.java]
    }

}