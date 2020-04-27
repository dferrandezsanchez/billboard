package ferrandez.daniel.billboard.ferrandez.daniel.billboard.di.modules

import dagger.Binds
import dagger.Module
import ferrandez.daniel.data.providers.storage.StorageMoviesProvider
import ferrandez.daniel.storage.providers.StorageMoviesProviderImpl

@Module
abstract class StorageModule {

    /**
     * This companion object annotated as a module is necessary in order to provide dependencies
     * statically in case the wrapping module is an abstract class (to use binding)
     */
    @Module
    companion object {
        /**
        @Provides
        @JvmStatic
        fun provideSomething(): Something {
        return InstanceOfSomething
        }*/
    }

    @Binds
    abstract fun bindStorageMoviesProvider(
        storageMoviesProviderImpl: StorageMoviesProviderImpl
    ): StorageMoviesProvider
}