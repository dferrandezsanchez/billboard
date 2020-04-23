package ferrandez.daniel.billboard.ferrandez.daniel.billboard.di.modules

import dagger.Binds
import dagger.Module
import dagger.Provides
import ferrandez.daniel.data.providers.remote.RemoteTheMovieDBProvider
import ferrandez.daniel.remote.providers.RemoteTheMoviesDbProviderImpl
import ferrandez.daniel.remote.services.themoviedb.RemoteTheMovieDBServiceConfig
import javax.inject.Singleton


@Module
abstract class RemoteModule {


    @Module
    companion object {
        private val token =
            "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1NTY4OGFhNTkzMTljYmZkNWE3MjdhMzcyNmFhMjlkYyIsInN1YiI6IjVkOTE4OWYyMTA5Y2QwMDAyMTQ1ZjBmYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.gXhQJTOnMOcxZAJ6uHm7zKvIrg5xliPT2jxdEACVFyw"
        private val baseUrl = "https://api.themoviedb.org"
        private val debug = true
        private val language = "es-ES"

        @Provides
        @JvmStatic
        @Singleton
        fun provideRemoteServiceConfig(): RemoteTheMovieDBServiceConfig =
            RemoteTheMovieDBServiceConfig(
                token,
                baseUrl,
                debug,
                language
            )
    }

    @Binds
    abstract fun bindRemoteTheMovieDBProviderImpl(
        remoteTheMovieDBProvider: RemoteTheMoviesDbProviderImpl
    ): RemoteTheMovieDBProvider
}