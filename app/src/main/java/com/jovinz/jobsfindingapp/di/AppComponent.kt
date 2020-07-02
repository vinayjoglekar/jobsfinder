package com.jovinz.jobsfindingapp.di

import com.jovinz.jobsfindingapp.BaseApplication
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
        AndroidInjectionModule::class,
        ActivityBuilderModule::class,
        AppModule::class,
        ViewModelFactoryModule::class
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