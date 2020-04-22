package ferrandez.daniel.remote.services.themoviedb

import ferrandez.daniel.remote.model.NWMovieList
import io.reactivex.Single
import retrofit2.http.POST

interface TheMovieDbService {

    /* ======================================================
    ======================  NOW IN CINEMAS ==================
    =========================================================
     */

    @POST("3/movie/now_playing")
    fun getNowPlaying(): Single<NWMovieList>
}