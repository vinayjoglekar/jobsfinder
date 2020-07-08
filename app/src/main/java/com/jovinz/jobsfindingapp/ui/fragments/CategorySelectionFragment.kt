package com.jovinz.jobsfindingapp.ui.fragments

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.jovinz.jobsfindingapp.R
import com.jovinz.jobsfindingapp.data.ResultData
import com.jovinz.jobsfindingapp.di.viewmodels.ViewModelsProviderFactory
import com.jovinz.jobsfindingapp.ui.adapter.CategoriesRecyclerViewAdapter
import com.jovinz.jobsfindingapp.ui.viewmodels.JobsViewModel
import com.jovinz.jobsfindingapp.utils.gone
import com.jovinz.jobsfindingapp.utils.visible
import dagger.android.support.DaggerFragment
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_category_selection.*
import javax.inject.Inject

class CategorySelectionFragment : DaggerFragment(R.layout.fragment_category_selection),
    OnItemClickListener {

    lateinit var navController: NavController

    private lateinit var jobsViewModel: JobsViewModel

    @Inject
    lateinit var viewModelsProviderFactory: ViewModelsProviderFactory

    @Inject
    lateinit var adapter: CategoriesRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpViewModel()
    }

    private fun setUpViewModel() {
        jobsViewModel =
            ViewModelProvider(this, viewModelsProviderFactory)[JobsViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        recyclerviewCategories.layoutManager = GridLayoutManager(context, 2)
        recyclerviewCategories.adapter = adapter


        jobsViewModel.getCategories().observe(viewLifecycleOwner, Observer { value ->
            when (value) {
                is ResultData.Loading -> {
                    progressCatSelection.visible()
                }
                is ResultData.Success -> {
                    progressCatSelection.gone()
                    value.data?.let {
                        value.data.response?.let { adapter.setCategories(it) }
                        adapter.onItemClickListener = this
                    }
                }
            }
        })

    }

    override fun <T> onItemClick(data: T) {
        navController.navigate(
            R.id.category_to_jobs, bundleOf(
                "category" to Category(data.toString())
            )
        )
    }


}

interface OnItemClickListener {
    fun <T> onItemClick(data: T)
}

@Parcelize
data class Category(var category: String) : Parcelable
