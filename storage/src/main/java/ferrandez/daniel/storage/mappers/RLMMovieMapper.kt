package ferrandez.daniel.storage.mappers

import ferrandez.daniel.data.model.MovieEntity
import ferrandez.daniel.storage.model.RLMMovie
import io.reactivex.Single

fun RLMMovie.asDataEntity(): MovieEntity {
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
        vote_count,
        wantToWatch
    )
}

fun Single<RLMMovie>.asDataEntity(): Single<MovieEntity> {
    return this.map { it.asDataEntity() }
}

fun List<RLMMovie>.asDataEntity(): List<MovieEntity> =
    map { it.asDataEntity() }

fun List<MovieEntity>.asStorageEntity(): List<RLMMovie> =
    map { it.asStorageEntity() }

fun MovieEntity.asStorageEntity(): RLMMovie {
    return RLMMovie(
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
        vote_count,
        wantToWatch
    )
}