package ferrandez.daniel.remote.mappers

import ferrandez.daniel.data.model.MovieEntity
import ferrandez.daniel.remote.model.NWMovie
import io.reactivex.Single

fun NWMovie.asDataEntity(): MovieEntity {
    return MovieEntity(
        adult,
        backdrop_path,
        genre_ids,
        id,
        original_language,
        original_title,
        overview,
        popularity,
        poster_path,
        release_date,
        title,
        video,
        vote_average,
        vote_count
    )
}

fun Single<NWMovie>.asDataEntity(): Single<MovieEntity> {
    return this.map { it.asDataEntity() }
}

fun List<NWMovie>.asDataEntity(): List<MovieEntity> =
    map { it.asDataEntity() }

fun List<MovieEntity>.asRemoteEntity(): List<NWMovie> =
    map { it.asRemoteEntity() }

fun MovieEntity.asRemoteEntity(): NWMovie {
    return NWMovie(
        adult,
        backdrop_path,
        genre_ids,
        id,
        original_language,
        original_title,
        overview,
        popularity,
        poster_path,
        release_date,
        title,
        video,
        vote_average,
        vote_count
    )
}