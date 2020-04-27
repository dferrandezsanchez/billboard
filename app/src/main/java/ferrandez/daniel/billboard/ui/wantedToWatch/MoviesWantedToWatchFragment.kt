package ferrandez.daniel.billboard.ui.wantedToWatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import ferrandez.daniel.billboard.R
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.di.Injectable
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.model.UIMovie
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.ui.BaseMovieFragment
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.ui.nowplaying.MoviesNowPlayingAdapter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_movies_popular.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class MoviesWantedToWatchFragment : BaseMovieFragment(), Injectable {

    private val moviesList = ArrayList<UIMovie>()
    private val filteredMoviesList = ArrayList<UIMovie>()

    private val disposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_popular, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindData()
    }

    private fun bindData() {
        val layoutManager =
            GridLayoutManager(requireContext(), 2)
        rvWantedToWatch?.layoutManager = layoutManager
        val adapter =
            MoviesNowPlayingAdapter(filteredMoviesList, this)
        rvWantedToWatch?.adapter = adapter
        wantToWatchViewModel.wantedToWatchList.observe(this,
            Observer { list ->
                moviesList.clear()
                moviesList.addAll(list)
                filterResults("")
                adapter.notifyDataSetChanged()
            })

        svWantedToWatchSearchBar.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
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
        when (newText.isBlank()) {
            true -> filteredMoviesList.addAll(moviesList)
            else -> {
                moviesList.forEach {
                    if (it.title.toLowerCase(Locale.getDefault())
                            .contains(newText.toLowerCase(Locale.getDefault()))
                    ) filteredMoviesList.add(it)
                }
            }
        }
        when (filteredMoviesList.isEmpty()) {
            true -> {
                tvWantedToWatchEmpty.visibility = View.VISIBLE
                rvWantedToWatch.visibility = View.GONE
            }
            else -> {
                tvWantedToWatchEmpty.visibility = View.GONE
                rvWantedToWatch.visibility = View.VISIBLE
            }
        }
        rvWantedToWatch?.adapter?.notifyDataSetChanged()
    }
}
