package ferrandez.daniel.billboard.ferrandez.daniel.billboard.di.component

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import ferrandez.daniel.billboard.App
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.di.modules.*

import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        ApplicationModule::class,
        ViewModelsModule::class,
        ActivitiesModule::class,
        DataModule::class,
        RemoteModule::class,
        StorageModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
    fun context(): Context
}