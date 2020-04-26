package ferrandez.daniel.billboard.ferrandez.daniel.billboard.di.modules.fragments

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ferrandez.daniel.billboard.ui.nowplaying.MoviesNowPlayingFragment
import ferrandez.daniel.billboard.ui.wantedToWatch.MoviesWantedToWatchFragment

@Module
abstract class MainFragmentsBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMoviesNowPlayingFragmentInjector(): MoviesNowPlayingFragment

    @ContributesAndroidInjector
    abstract fun contributeMoviesPopularFragmentInjector(): MoviesWantedToWatchFragment
}