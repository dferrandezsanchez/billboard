package ferrandez.daniel.billboard.ferrandez.daniel.billboard.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import ferrandez.daniel.billboard.R
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.di.Injectable
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.model.UIMovie
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.ui.viewmodel.NowPlayingViewModel
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.ui.viewmodel.WantToWatchViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

open class BaseMovieFragment : Fragment(), Injectable {

    private val disposable = CompositeDisposable()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var nowPlayingViewModel: NowPlayingViewModel
    lateinit var wantToWatchViewModel: WantToWatchViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    private fun bindViewModel() {
        activity?.let {
            nowPlayingViewModel = ViewModelProviders
                .of(it, viewModelFactory)
                .get(NowPlayingViewModel::class.java)

            nowPlayingViewModel.getNowPlaying().addTo(disposable)
            wantToWatchViewModel = ViewModelProviders
                .of(it, viewModelFactory)
                .get(WantToWatchViewModel::class.java)
            wantToWatchViewModel.bindAllWantToWatchMovies().addTo(disposable)
        }
    }

    fun onItemClick(movie: UIMovie) {
        nowPlayingViewModel.selectedMovie.postValue(movie)
        findNavController().navigate(R.id.movieDetails)
    }
}