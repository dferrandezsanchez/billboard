package ferrandez.daniel.data.repositories

import ferrandez.daniel.data.model.MovieEntity
import io.reactivex.Single

interface MoviesRepository {
    fun getNowPlayingRepository(): Single<List<MovieEntity>>
}