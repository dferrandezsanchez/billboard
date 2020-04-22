package ferrandez.daniel.remote.services.themoviedb

import ferrandez.daniel.remote.executors.JobExecutor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

data class RemoteTheMovieDBServiceConfig(
    var token: String,
    var baseUrl: String,
    var debug: Boolean,
    var language: String
)

abstract class RemoteTheMovieDBService<T>
constructor(c: Class<T>, private val config: RemoteTheMovieDBServiceConfig) {
    protected var service: T

    init {
        service = initApiService().create(c)
        /* Create a CognitoUserPool instance */
    }

    private fun initApiService(): Retrofit {
        val builder = OkHttpClient.Builder()
            .addInterceptor(getLoggingInterceptor())
            .addInterceptor(CustomInterceptor())

        return Retrofit.Builder().baseUrl(config.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .callbackExecutor(JobExecutor())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(builder.build()).build()
    }

    private fun getLoggingInterceptor(): Interceptor = HttpLoggingInterceptor()
        .apply {
            level = if (config.debug)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }

    inner class CustomInterceptor : Interceptor {
        private lateinit var newRequest: Request

        override fun intercept(chain: Interceptor.Chain): Response {
            return sendRequest(chain)
        }

        private fun sendRequest(chain: Interceptor.Chain): Response {
            val request: Request = chain.request()

            newRequest = request.newBuilder()
                .header("EventTime", System.currentTimeMillis().toString())
                .header("Content-Type", "application/json;charset=utf-8")
                .header("Authorization", "Bearer ${config.token}")
                .build()
            // try the request
            return chain.proceed(newRequest)
        }
    }
}