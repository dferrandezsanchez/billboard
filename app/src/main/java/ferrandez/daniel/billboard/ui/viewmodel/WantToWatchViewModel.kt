package ferrandez.daniel.billboard.ferrandez.daniel.billboard.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.di.Injectable
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.mappers.asUIEntity
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.model.UIMovie
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.usecases.BindAllWantedToWatchMoviesUseCase
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.usecases.SetMovieWantToWatchUseCase
import io.reactivex.disposables.Disposable
import javax.inject.Inject

open class WantToWatchViewModel @Inject constructor(
    private val wantToWatchUseCase: SetMovieWantToWatchUseCase,
    private val bindAllWantedToWatchMoviesUseCase: BindAllWantedToWatchMoviesUseCase
) : ViewModel(), Injectable {

    val wantedToWatchList = MutableLiveData<List<UIMovie>>()

    fun setWantToWatch(movie: UIMovie): Disposable {
        return wantToWatchUseCase.execute(movie)
            .onErrorComplete()
            .subscribe()
    }

    fun bindAllWantToWatchMovies(): Disposable {
        return bindAllWantedToWatchMoviesUseCase.execute()
            .map { wantedToWatchList.postValue(it.asUIEntity()) }
            .subscribe()
    }
}