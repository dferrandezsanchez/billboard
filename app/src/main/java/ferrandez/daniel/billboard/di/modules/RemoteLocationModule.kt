package ferrandez.daniel.billboard.ferrandez.daniel.billboard.di.modules

import android.annotation.SuppressLint
import android.os.Build
import android.provider.Settings
import dagger.Binds
import dagger.Module
import dagger.Provides
import ferrandez.daniel.billboard.App
import ferrandez.daniel.billboard.BuildConfig
import javax.inject.Singleton


@Module
abstract class RemoteLocationModule {


    @Module
    companion object {

        @SuppressLint("HardwareIds")
        private var androidId =
            Settings.Secure.getString(App.instance.contentResolver, Settings.Secure.ANDROID_ID)

        private val context = App.instance
        private val token =
            "eyJraWQiOiJ3dnFaTUJBWk9hVXNnMlU4TFNuQTVRaXFlYVJKYUpuQnVVWEJtSHFSMVQwPSIsImFsZyI6IlJTMjU2In0.eyJhdF9oYXNoIjoiMnpHcFYwR1JjaHJqS0NMa096N01BZyIsInN1YiI6ImU5N2E4YmViLTJlNGEtNGVjYS1hM2FjLTkxNWYyNzJiYjhkZSIsImNvZ25pdG86Z3JvdXBzIjpbIlVTRVJTIl0sImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJpc3MiOiJodHRwczpcL1wvY29nbml0by1pZHAuZXUtd2VzdC0xLmFtYXpvbmF3cy5jb21cL2V1LXdlc3QtMV9aVkFTWVZDTmgiLCJjb2duaXRvOnVzZXJuYW1lIjoiMTc2Mjc1NjBTIiwiY3VzdG9tOmlkQmVuZWYiOiI3MTA5MTU5IiwiY29nbml0bzpyb2xlcyI6WyJhcm46YXdzOmlhbTo6MDMxMTE4NjQ0MTc1OnJvbGVcL3NlcnZpY2Utcm9sZVwvUk9MRV9VU0VSIl0sImF1ZCI6IjJxMnNwYjZrazMwanZlczRhMjhpOGlmcnZsIiwiZXZlbnRfaWQiOiJhMWExZGFlZS02M2FlLTQyOTctOTNmZC03YWI1N2Q4NTdjNjciLCJ0b2tlbl91c2UiOiJpZCIsImF1dGhfdGltZSI6MTU2NzY2ODQ0OSwiY3VzdG9tOm1lcmlkaWFub1VzZXJJZCI6IkNCUzA3OTk1OTciLCJleHAiOjE1Njc2NzIwNDksImlhdCI6MTU2NzY2ODQ0OSwiZW1haWwiOiJkZXZlbG9wZXJzQGlubm92YWFzdi5jb20ifQ.JN89Rfopr9aCbmd2BmSKum_1Duzm1a84dPXQiXD55zUqO57Q_jiF52SsyvH5RYj6g6Zm2zgrF33FfOCHCATQYA9NBbfi94DeCgcxWYpGcTn0x5NvrIJc2XqGlGCul1l1QDI_xdM96c_RgsX_vncSM4F0M2AODG6962DnCxGotyLt0kc6KZh8xJvAWB3LJWAUy7IMC4b1EVXA-ZqOEGb8fG9R7TwZLXPKjyw3Clg6BveOzXWzEYB2e4s7NkPmgsTUfL3_rBWaoxjAhIswk6_aFXdXY0HGFGoq1W5Fg7JTjTz7K0vO-jwEvV_vJnq1kkIqsxjrFmoYZwbKKH-SkDNbsg"
        private val baseUrl = BuildConfig.LOCATION_URL
        private val debug = true
        private val device =
            "$androidId;${Build.MODEL};Android ${Build.VERSION.RELEASE};${BuildConfig.VERSION_NAME};"


        @Provides
        @JvmStatic
        @Singleton
        fun provideRemoteLocationServiceConfig(): RemoteLocationServiceConfig =
            RemoteLocationServiceConfig(
                token,
                baseUrl,
                debug,
                provideAWSCognito(context),
                device
            )
    }

    @Binds
    abstract fun bindRemoteLocationProviderImpl(
        remoteCuadroProvider: RemoteLocationProviderImpl
    ): RemoteLocationProvider

}