package ferrandez.daniel.data.repositories

import ferrandez.daniel.data.model.MovieEntity
import ferrandez.daniel.data.providers.remote.RemoteTheMovieDBProvider
import io.reactivex.Single
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val remoteTheMovieDBProvider: RemoteTheMovieDBProvider
) : MoviesRepository {
    override fun getNowPlayingRepository(): Single<List<MovieEntity>> =
        remoteTheMovieDBProvider.getNowPlaying()

}