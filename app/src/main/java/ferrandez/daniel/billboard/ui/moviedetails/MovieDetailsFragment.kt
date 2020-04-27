package ferrandez.daniel.billboard.ferrandez.daniel.billboard.ui.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import ferrandez.daniel.billboard.R
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.ui.BaseMovieFragment
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.ui.viewmodel.NowPlayingViewModel
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.ui.viewmodel.WantToWatchViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_movie_details.*

/**
 * A simple [Fragment] subclass.
 */
class MovieDetailsFragment : BaseMovieFragment() {

    private val disposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindViewModel()
        bindData()
    }

    private fun bindData() {
        setWantToWatchButtonState()
        nowPlayingViewModel.selectedMovie.observe(this, Observer {
            it.let { data ->
                Glide.with(this)
                    .load("https://image.tmdb.org/t/p/original/${data.backdrop_path}")
                    .centerCrop()
                    .into(ivMovieDetailsBackdrop)

                tvMovieDetailsTitle.text = data.title
                tvMovieDetailsOverview.text = data.overview
                when (data.wantToWatch) {
                    true -> {
                        btnSetWantToWatchMovieDetails.background = ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.primary_button_rounded_shape
                        )
                        btnSetWantToWatchMovieDetails.setTextColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.colorAccent
                            )
                        )
                        btnSetWantToWatchMovieDetails.setText(getString(R.string.watched))
                    }
                    else -> {
                        btnSetWantToWatchMovieDetails.background = ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.white_button_rounded_shape
                        )
                        btnSetWantToWatchMovieDetails.setTextColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.colorPrimaryDark
                            )
                        )
                        btnSetWantToWatchMovieDetails.setText(getString(R.string.want_to_watch))
                    }
                }
            }
        })
        btnSetWantToWatchMovieDetails.setOnClickListener {
            nowPlayingViewModel.selectedMovie.value?.let { movie ->
                movie.apply {
                    wantToWatch = !wantToWatch
                    nowPlayingViewModel.selectedMovie.postValue(this)
                    wantToWatchViewModel.setWantToWatch(this).addTo(disposable)
                }
            }
        }
    }

    private fun setWantToWatchButtonState() {
        wantToWatchViewModel.wantedToWatchList.value?.let {
            it.forEach {
                if (it.id == nowPlayingViewModel.selectedMovie.value?.id) {
                    nowPlayingViewModel.selectedMovie.value.apply {
                        this?.wantToWatch = true
                        nowPlayingViewModel.selectedMovie.postValue(this)
                    }
                }
            }
        }
    }

    private fun bindViewModel() {
        activity?.let {
            nowPlayingViewModel = ViewModelProviders
                .of(it, viewModelFactory)
                .get(NowPlayingViewModel::class.java)
        }

        activity?.let {
            wantToWatchViewModel = ViewModelProviders
                .of(it, viewModelFactory)
                .get(WantToWatchViewModel::class.java)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
