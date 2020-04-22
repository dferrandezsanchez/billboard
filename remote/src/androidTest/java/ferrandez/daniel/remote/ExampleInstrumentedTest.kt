package ferrandez.daniel.remote

import androidx.test.ext.junit.runners.AndroidJUnit4
import ferrandez.daniel.data.providers.remote.RemoteTheMovieDBProvider
import ferrandez.daniel.remote.providers.RemoteTheMoviesDbProviderImpl
import ferrandez.daniel.remote.services.themoviedb.RemoteTheMovieDBServiceConfig
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private lateinit var config: RemoteTheMovieDBServiceConfig

    @Before
    fun setup() {
        config = RemoteTheMovieDBServiceConfig(
            token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1NTY4OGFhNTkzMTljYmZkNWE3MjdhMzcyNmFhMjlkYyIsInN1YiI6IjVkOTE4OWYyMTA5Y2QwMDAyMTQ1ZjBmYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.gXhQJTOnMOcxZAJ6uHm7zKvIrg5xliPT2jxdEACVFyw",
            baseUrl = "https://api.themoviedb.org",
            debug = true,
            language = "es-ES"
        )
    }

    lateinit var remoteTheMovieDBProvider: RemoteTheMovieDBProvider


    @Test
    fun getNowPlaying() {
        remoteTheMovieDBProvider = RemoteTheMoviesDbProviderImpl(config)
        val results = remoteTheMovieDBProvider.getNowPlaying()

        results
            .test()
            .assertNoErrors()
    }
}
