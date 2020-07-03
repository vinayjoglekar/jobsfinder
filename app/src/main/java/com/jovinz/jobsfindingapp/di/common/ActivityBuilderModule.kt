package com.jovinz.jobsfindingapp.di.common

import com.jovinz.jobsfindingapp.di.jobs.JobsFragmentBuildersModule
import com.jovinz.jobsfindingapp.di.jobs.JobsModule
import com.jovinz.jobsfindingapp.di.jobs.JobsViewModelsModule
import com.jovinz.jobsfindingapp.ui.MainActivity
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
abstract class ActivityBuilderModule {

    //provides activity to the AppComponent. [potential client for injection to AppComponent]

    @ContributesAndroidInjector(
        modules = [JobsFragmentBuildersModule::class, JobsViewModelsModule::class, JobsModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity

    // For the above code, behind the scenes, this code is generated.
    // @Subcomponent is created which is provided to app Component

//    @Binds
//    @IntoMap
//    @ClassKey(MainActivity::class)
//    abstract fun bindAndroidInjectorFactory(
//        builder: MainActivitySubcomponent.Factory?
//    ): AndroidInjector.Factory<*>?
//
//    @Subcomponent(modules = [JobsFragmentBuildersModule::class, JobsViewModelsModule::class, JobsModule::class])
//    interface MainActivitySubcomponent : AndroidInjector<MainActivity?> {
//        @Subcomponent.Factory
//        interface Factory : AndroidInjector.Factory<MainActivity?>
//    }

}