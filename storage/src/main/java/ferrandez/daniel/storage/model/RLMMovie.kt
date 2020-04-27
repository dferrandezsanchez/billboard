package ferrandez.daniel.storage.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RLMMovie(
    var adult: Boolean = false,
    var backdrop_path: String = "",
    var genre_ids: RealmList<Int> = RealmList(),
    @PrimaryKey
    var id: Int = -1,
    var original_language: String = "",
    var original_title: String = "",
    var overview: String = "",
    var popularity: Double = 0.0,
    var poster_path: String = "",
    var release_date: String = "",
    var title: String = "",
    var video: Boolean = false,
    var vote_average: Double = 0.0,
    var vote_count: Int = -1,
    var wantToWatch: Boolean = false
) : RealmObject()