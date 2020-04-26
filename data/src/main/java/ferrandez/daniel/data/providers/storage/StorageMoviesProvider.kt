package ferrandez.daniel.data.providers.storage

import ferrandez.daniel.data.model.MovieEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface StorageMoviesProvider {
    // Persistence
    fun insertAllMovies(movieList: List<MovieEntity>): Completable
    fun bindAllMovies(): Flowable<List<MovieEntity>>
    // Movies Wanted to watch actions
    fun setAsWantToWatch(movie: MovieEntity): Completable
    fun bindAllMoviesWantedToWatch(): Flowable<List<MovieEntity>>
}