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
import ferrandez.daniel.billboard.di.AppInjector
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.di.component.AppComponent
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject

class App : Application(), ViewModelStoreOwner, HasActivityInjector, HasServiceInjector {

    private var realm: Realm? = null

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

    /* =====================================================
    ================= LifeCylce Methods ====================
    ========================================================*/

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
        instance = this
        initRealm()
    }

    /* =====================================================
    =================== Relalm Methods =====================
    ========================================================*/

    /* Set Simple Instance of Realm because we don't neet to encrypt nothing */
    private fun initRealm(): Realm? {
        Realm.init(this)
        val realmConfiguration: RealmConfiguration = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()

        Realm.setDefaultConfiguration(realmConfiguration)
        val realm = Realm.getInstance(realmConfiguration)
        realm.beginTransaction()
        realm.commitTransaction()
        return realm
    }

    private fun closeRealm() {
        realm?.close()
    }
}