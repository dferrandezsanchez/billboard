package ferrandez.daniel.data.providers.remote

import ferrandez.daniel.data.model.MovieEntity
import io.reactivex.Single

interface RemoteTheMovieDBProvider {
    fun getNowPlaying(): Single<List<MovieEntity>>
}