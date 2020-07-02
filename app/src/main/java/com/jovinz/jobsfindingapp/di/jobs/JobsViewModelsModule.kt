package com.jovinz.jobsfindingapp.di.jobs

import androidx.lifecycle.ViewModel
import com.jovinz.jobsfindingapp.di.viewmodels.ViewModelKey
import com.jovinz.jobsfindingapp.ui.viewmodels.JobsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class JobsViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(JobsViewModel::class)
    abstract fun bindJobsViewModel(viewModel: JobsViewModel): ViewModel

}