package ferrandez.daniel.data.repositories

import ferrandez.daniel.data.model.MovieEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface MoviesRepository {
    fun getNowPlayingRepository(): Single<List<MovieEntity>>
    fun setWantToWatch(movie: MovieEntity): Completable
    fun bindAllWantedToWatchMovies(): Flowable<List<MovieEntity>>
}