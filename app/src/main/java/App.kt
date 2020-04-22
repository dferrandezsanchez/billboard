package ferrandez.daniel.billboard

import android.app.Activity
import android.app.Application
import android.app.Service
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.di.AppInjector
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.di.component.AppComponent
import javax.inject.Inject

class App : Application(), ViewModelStoreOwner, HasActivityInjector, HasServiceInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var serviceInjector: DispatchingAndroidInjector<Service>

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = activityInjector
    override fun serviceInjector(): AndroidInjector<Service> = serviceInjector

    private val appViewModelStore: ViewModelStore by lazy {
        ViewModelStore()
    }

    override fun getViewModelStore(): ViewModelStore {
        return appViewModelStore
    }

    companion object {
        lateinit var instance: App
    }

    var appComponent: AppComponent = AppInjector.init(this)
        @VisibleForTesting
        set

}