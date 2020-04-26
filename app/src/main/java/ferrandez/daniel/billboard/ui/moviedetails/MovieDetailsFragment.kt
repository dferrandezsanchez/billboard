package ferrandez.daniel.billboard.ferrandez.daniel.billboard.ui.moviedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import ferrandez.daniel.billboard.R
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.di.Injectable
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.ui.viewmodel.NowPlayingViewModel
import kotlinx.android.synthetic.main.fragment_movie_details.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class MovieDetailsFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var nowPlayingViewModel: NowPlayingViewModel

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
        nowPlayingViewModel.selectedMovie.observe(this, Observer {
            it.let { data ->
                Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500/${data.backdrop_path}")
                    .centerCrop()
                    .into(ivMovieDetailsBackdrop)

                tvMovieDetailsTitle.text = data.title
                tvMovieDetailsOverview.text = data.overview
            }
        })
    }

    private fun bindViewModel() {
        activity?.let {
            nowPlayingViewModel = ViewModelProviders
                .of(it, viewModelFactory)
                .get(NowPlayingViewModel::class.java)
        }
    }
}
