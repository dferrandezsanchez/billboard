package ferrandez.daniel.billboard.ui.nowplaying

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import ferrandez.daniel.billboard.R
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.di.Injectable
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.model.UIMovie
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.ui.nowplaying.MoviesNowPlayingAdapter
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.ui.viewmodel.NowPlayingViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_movies_now_playing.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class MoviesNowPlayingFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var nowPlayingViewModel: NowPlayingViewModel

    private val disposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_now_playing, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindViewModel()
        bindData()
    }

    private fun bindData() {
        val moviesList = ArrayList<UIMovie>()
        rvNowPlaying?.layoutManager = LinearLayoutManager(context)
        val adapter =
            MoviesNowPlayingAdapter(moviesList, this)
        rvNowPlaying?.adapter = adapter
        nowPlayingViewModel.nowPlayingList.observe(this, object : Observer<List<UIMovie>> {
            override fun onChanged(list: List<UIMovie>) {
                moviesList.addAll(list)
                adapter.notifyDataSetChanged()
            }

        })
    }

    private fun bindViewModel() {
        activity?.let {
            nowPlayingViewModel = ViewModelProviders
                .of(it, viewModelFactory)
                .get(NowPlayingViewModel::class.java)
        }

        nowPlayingViewModel.getNowPlaying().addTo(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
