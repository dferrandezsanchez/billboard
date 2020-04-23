package ferrandez.daniel.billboard.ferrandez.daniel.billboard.common

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders

class GlideClient(
    val mContext : Context
) {


    fun loadImage(url: String, view : ImageView) {
        val completeURL = "https://api.themoviedb.org/3/movie/now_playing"
        val glideUrl = GlideUrl(
            "$completeURL$url", LazyHeaders.Builder()
                .addHeader(
                    "Authorization",
                    "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1NTY4OGFhNTkzMTljYmZkNWE3MjdhMzcyNmFhMjlkYyIsInN1YiI6IjVkOTE4OWYyMTA5Y2QwMDAyMTQ1ZjBmYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.gXhQJTOnMOcxZAJ6uHm7zKvIrg5xliPT2jxdEACVFyw"
                )
                .build()
        )

        Glide.with(mContext)
            .load(glideUrl)
            .into(view)
    }
}