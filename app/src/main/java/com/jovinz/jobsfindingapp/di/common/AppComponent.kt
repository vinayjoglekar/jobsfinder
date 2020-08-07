package com.jovinz.jobsfindingapp.di.common

import com.jovinz.jobsfindingapp.app.BaseApplication
import com.jovinz.jobsfindingapp.di.jobs.JobsModule
import com.jovinz.jobsfindingapp.di.viewmodels.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

//remains for the entire lifetime of the app. Inject base application into this component.
//AndroidInjectionModule to inject the application class into the framework
//for simplification, consider components as services and activities and fragments as clients.
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class, // for injecting application class
        ActivityBuilderModule::class, // provides activities, as sub components behind the scenes
        AppModule::class, // provides objects which will be available for scope of entire app
        ViewModelFactoryModule::class //
    ]
)
interface AppComponent : AndroidInjector<BaseApplication?> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: BaseApplication?): Builder?
        fun build(): AppComponent?
    }
}