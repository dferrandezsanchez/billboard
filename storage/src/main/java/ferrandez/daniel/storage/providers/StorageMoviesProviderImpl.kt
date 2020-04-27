package ferrandez.daniel.storage.providers

import ferrandez.daniel.data.model.MovieEntity
import ferrandez.daniel.data.providers.storage.StorageMoviesProvider
import ferrandez.daniel.storage.BillBoardRealmService
import ferrandez.daniel.storage.mappers.asDataEntity
import ferrandez.daniel.storage.mappers.asStorageEntity
import ferrandez.daniel.storage.model.RLMMovie
import io.reactivex.Completable
import io.reactivex.Flowable
import io.realm.Realm
import javax.inject.Inject

class StorageMoviesProviderImpl @Inject constructor() : StorageMoviesProvider {
    private val realmService = BillBoardRealmService<RLMMovie>()

    override fun insertAllMovies(movieList: List<MovieEntity>): Completable =
        realmService.save(movieList.map { it.asStorageEntity() })

    override fun bindAllMovies(): Flowable<List<MovieEntity>> =
        realmService.findAll<RLMMovie>().map { it.asDataEntity() }

    override fun setAsWantToWatch(movie: MovieEntity): Completable =
        realmService.save(movie.asStorageEntity())

    override fun bindAllMoviesWantedToWatch(): Flowable<List<MovieEntity>> =
        Realm.getDefaultInstance().where(RLMMovie::class.java)
            .equalTo("wantToWatch", true)
            .let {
                realmService.findByQuery(it).map { data -> data.asDataEntity() }
            }
}