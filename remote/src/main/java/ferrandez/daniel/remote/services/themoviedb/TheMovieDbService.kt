package ferrandez.daniel.remote.services.themoviedb

import ferrandez.daniel.remote.model.NWMovieList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDbService {

    /* ======================================================
    ======================  NOW IN CINEMAS ==================
    =========================================================
     */

    @GET("3/movie/now_playing")
    fun getNowPlaying(@Query("language") language: String): Single<NWMovieList>
}