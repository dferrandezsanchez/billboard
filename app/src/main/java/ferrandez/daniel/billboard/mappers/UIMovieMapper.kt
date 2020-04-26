package ferrandez.daniel.billboard.ferrandez.daniel.billboard.mappers

import ferrandez.daniel.billboard.ferrandez.daniel.billboard.model.UIMovie
import ferrandez.daniel.data.model.MovieEntity
import io.reactivex.Single

fun UIMovie.asDataEntity(): MovieEntity {
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

fun Single<UIMovie>.asDataEntity(): Single<MovieEntity> {
    return this.map { it.asDataEntity() }
}

fun List<UIMovie>.asDataEntity(): List<MovieEntity> =
    map { it.asDataEntity() }

fun List<MovieEntity>.asUIEntity(): List<UIMovie> =
    map { it.asUIEntity() }

fun MovieEntity.asUIEntity(): UIMovie {
    return UIMovie(
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
        wantToWatch ?: false
    )
}