package ferrandez.daniel.billboard.ui.nowplaying

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import ferrandez.daniel.billboard.R
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.di.Injectable
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.model.UIMovie
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.ui.nowplaying.MoviesNowPlayingAdapter
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.ui.viewmodel.NowPlayingViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_movies_now_playing.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


/**
 * A simple [Fragment] subclass.
 */
class MoviesNowPlayingFragment : Fragment(), Injectable {

    val moviesList = ArrayList<UIMovie>()
    val filteredMoviesList = ArrayList<UIMovie>()

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
        val layoutManager =
            GridLayoutManager(requireContext(), 2)
        rvNowPlaying?.layoutManager = layoutManager
        val adapter =
            MoviesNowPlayingAdapter(filteredMoviesList, this)
        rvNowPlaying?.adapter = adapter
        nowPlayingViewModel.nowPlayingList.observe(this,
            Observer<List<UIMovie>> { list ->
                moviesList.addAll(list)
                filteredMoviesList.addAll(moviesList)
                adapter.notifyDataSetChanged()
            })

        svNowPlayingSearchBar.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                //Do nothing
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterResults(newText)
                return true
            }

        })
    }

    private fun filterResults(newText: String) {
        filteredMoviesList.clear()
        when(newText.isBlank()){
            true -> filteredMoviesList.addAll(moviesList)
            else -> {
                moviesList.forEach {
                    if(it.title.toLowerCase(Locale.getDefault()).contains(newText.toLowerCase(Locale.getDefault()))) filteredMoviesList.add(it)
                }
            }
        }
        rvNowPlaying?.adapter?.notifyDataSetChanged()
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
