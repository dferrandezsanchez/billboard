package ferrandez.daniel.billboard.ui.nowplaying

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import ferrandez.daniel.billboard.R
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.model.UIMovie
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.ui.BaseMovieFragment
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.ui.nowplaying.MoviesNowPlayingAdapter
import kotlinx.android.synthetic.main.fragment_movies_now_playing.*
import java.util.*
import kotlin.collections.ArrayList


/**
 * A simple [Fragment] subclass.
 */
class MoviesNowPlayingFragment : BaseMovieFragment() {

    private val moviesList = ArrayList<UIMovie>()
    private val filteredMoviesList = ArrayList<UIMovie>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_now_playing, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindData()
    }

    private fun bindData() {
        setUpRecyclerView()
        setUpSearchBar()
    }

    private fun setUpRecyclerView() {
        val layoutManager =
            GridLayoutManager(requireContext(), 2)
        rvNowPlaying?.layoutManager = layoutManager
        val adapter =
            MoviesNowPlayingAdapter(filteredMoviesList, this)
        rvNowPlaying?.adapter = adapter
        nowPlayingViewModel.nowPlayingList.observe(this,
            Observer { list ->
                moviesList.clear()
                moviesList.addAll(list)
                filterResults("")
                adapter.notifyDataSetChanged()
            })
    }

    private fun setUpSearchBar() {
        svNowPlayingSearchBar.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
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
                tvNowPlayingEmpty.visibility = View.VISIBLE
                rvNowPlaying.visibility = View.GONE
            }
            else -> {
                tvNowPlayingEmpty.visibility = View.GONE
                rvNowPlaying.visibility = View.VISIBLE
            }
        }
        rvNowPlaying?.adapter?.notifyDataSetChanged()
    }
}
