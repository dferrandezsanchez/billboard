package ferrandez.daniel.data.repositories

import ferrandez.daniel.data.model.MovieEntity
import ferrandez.daniel.data.providers.remote.RemoteTheMovieDBProvider
import ferrandez.daniel.data.providers.storage.StorageMoviesProvider
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val remoteTheMovieDBProvider: RemoteTheMovieDBProvider,
    private val storageMoviesProvider: StorageMoviesProvider
) : MoviesRepository {
    override fun getNowPlayingRepository(): Single<List<MovieEntity>> =
        remoteTheMovieDBProvider.getNowPlaying()

    override fun setWantToWatch(movie: MovieEntity): Completable =
        storageMoviesProvider.setAsWantToWatch(movie)

    override fun bindAllWantedToWatchMovies(): Flowable<List<MovieEntity>> =
        storageMoviesProvider.bindAllMoviesWantedToWatch()

}