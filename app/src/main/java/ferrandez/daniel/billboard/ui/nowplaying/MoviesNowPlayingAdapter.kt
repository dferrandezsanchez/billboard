package ferrandez.daniel.billboard.ferrandez.daniel.billboard.ui.nowplaying

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ferrandez.daniel.billboard.App
import ferrandez.daniel.billboard.R
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.common.GlideClient
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.model.UIMovie
import ferrandez.daniel.billboard.ui.nowplaying.MoviesNowPlayingFragment
import kotlinx.android.synthetic.main.item_movie_list.view.*

class MoviesNowPlayingAdapter(
    private val movieList: ArrayList<UIMovie>,
    val listener: MoviesNowPlayingFragment
) :
    RecyclerView.Adapter<MoviesNowPlayingAdapter.ViewHolder>() {

    val glideClient = GlideClient(App.instance)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun bind(
            movie: UIMovie
        ) {
            itemView.tvMovieTitle.text = movie.title
            itemView.tvMovieSubTitle.text = movie.overview
            Glide.with(listener)
                .load("https://image.tmdb.org/t/p/w500/${movie.backdrop_path}")
                .centerCrop()
                .into(itemView.ivMovieImage)
        }
    }
}