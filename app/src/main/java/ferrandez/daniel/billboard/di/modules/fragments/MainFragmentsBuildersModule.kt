package ferrandez.daniel.billboard.ferrandez.daniel.billboard.di.modules.fragments

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.ui.BaseMovieFragment
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.ui.moviedetails.MovieDetailsFragment
import ferrandez.daniel.billboard.ui.nowplaying.MoviesNowPlayingFragment
import ferrandez.daniel.billboard.ui.wantedToWatch.MoviesWantedToWatchFragment

@Module
abstract class MainFragmentsBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMoviesNowPlayingFragmentInjector(): MoviesNowPlayingFragment

    @ContributesAndroidInjector
    abstract fun contributeMoviesPopularFragmentInjector(): MoviesWantedToWatchFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieDetailsFragmentInjector(): MovieDetailsFragment

    @ContributesAndroidInjector
    abstract fun contributBaseMovieFragmentInjector(): BaseMovieFragment
}