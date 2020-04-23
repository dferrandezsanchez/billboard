package ferrandez.daniel.billboard.ferrandez.daniel.billboard.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.di.Injectable
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.model.UIMovie
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.usecases.GetNowPlayingMoviesUseCase
import io.reactivex.disposables.Disposable
import javax.inject.Inject

open class NowPlayingViewModel @Inject constructor(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase
) : ViewModel(), Injectable {

    val nowPlayingList = MutableLiveData<List<UIMovie>>()

    fun getNowPlaying(): Disposable {
        return getNowPlayingMoviesUseCase.execute()
            .map { nowPlayingList.postValue(it) }
            .doOnError {}
            .subscribe()
    }
}