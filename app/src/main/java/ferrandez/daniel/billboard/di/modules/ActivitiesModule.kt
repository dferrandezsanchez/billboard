package ferrandez.daniel.billboard.ferrandez.daniel.billboard.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.di.modules.fragments.MainFragmentsBuildersModule
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.ui.MainActivity

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector(modules = [(MainFragmentsBuildersModule::class)])
    abstract fun contributeMainActivityInjector(): MainActivity

}