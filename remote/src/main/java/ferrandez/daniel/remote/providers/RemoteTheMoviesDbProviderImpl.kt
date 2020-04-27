package ferrandez.daniel.remote.providers

import ferrandez.daniel.data.model.MovieEntity
import ferrandez.daniel.data.providers.remote.RemoteTheMovieDBProvider
import ferrandez.daniel.remote.mappers.asDataEntity
import ferrandez.daniel.remote.services.themoviedb.RemoteTheMovieDBService
import ferrandez.daniel.remote.services.themoviedb.RemoteTheMovieDBServiceConfig
import ferrandez.daniel.remote.services.themoviedb.TheMovieDbService
import io.reactivex.Single
import javax.inject.Inject

class RemoteTheMoviesDbProviderImpl @Inject constructor(
    val serviceConfig: RemoteTheMovieDBServiceConfig
) :
    RemoteTheMovieDBService<TheMovieDbService>(
        TheMovieDbService::class.java,
        serviceConfig), RemoteTheMovieDBProvider {
    override fun getNowPlaying(): Single<List<MovieEntity>> {
        return service.getNowPlaying(serviceConfig.language).map { it.results.asDataEntity() }
    }
}