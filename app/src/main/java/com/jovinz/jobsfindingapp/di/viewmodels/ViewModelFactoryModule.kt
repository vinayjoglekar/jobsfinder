package com.jovinz.jobsfindingapp.di.viewmodels

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    //exactly same as using @provides annotation and then returning the object.
    //@binds is more efficient as it creates less number of classes
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelsProviderFactory): ViewModelProvider.Factory
}