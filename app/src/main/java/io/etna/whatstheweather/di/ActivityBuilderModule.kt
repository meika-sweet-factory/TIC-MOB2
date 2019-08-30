package io.etna.whatstheweather.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.etna.whatstheweather.di.main.MainFragmentBuildersModule
import io.etna.whatstheweather.di.main.MainModule
import io.etna.whatstheweather.di.main.MainViewModelsModule
import io.etna.whatstheweather.ui.main.MainActivity

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(
        modules = [
            MainFragmentBuildersModule::class,
            MainViewModelsModule::class,
            MainModule::class
        ]
    )
    abstract fun constributeMainAcitvity(): MainActivity
}