package ferrandez.daniel.billboard.ferrandez.daniel.billboard.ui.nowplaying

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ferrandez.daniel.billboard.R
import ferrandez.daniel.billboard.ferrandez.daniel.billboard.model.UIMovie
import ferrandez.daniel.billboard.ui.nowplaying.MoviesNowPlayingFragment
import kotlinx.android.synthetic.main.item_movie_list.view.*

class MoviesNowPlayingAdapter(
    private val movieList: ArrayList<UIMovie>,
    val listener: MoviesNowPlayingFragment
) :
    RecyclerView.Adapter<MoviesNowPlayingAdapter.ViewHolder>() {
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
            val mRoundCornetTransform = RoundedCorners(40)
            itemView.tvMovieTitle.text = movie.title
            itemView.tvMovieRate.text = movie.vote_average.toString()
            Glide.with(listener)
                .load("https://image.tmdb.org/t/p/w500/${movie.poster_path}")
                .transform(mRoundCornetTransform)
                .into(itemView.ivMovieImage)

            itemView.rootView.setOnClickListener {
                listener.onItemClick(movie)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: UIMovie)
    }
}