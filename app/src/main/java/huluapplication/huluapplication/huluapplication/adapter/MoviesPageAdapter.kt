package huluapplication.huluapplication.huluapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import huluapplication.R
import huluapplication.huluapplication.huluapplication.model.FilmItem

class MoviesPageAdapter(var moviesItems: List<FilmItem>):
    RecyclerView.Adapter<MoviesPageAdapter.ViewHolder>() {


    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        val movImgSmall = view.findViewById<ImageView>(R.id.moviesSmallImg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rec_movies,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val myMovItem = moviesItems[position]
        val bigImgForMovPage: String = myMovItem.filmSmallImg
        Glide.with(holder.movImgSmall.context).load(bigImgForMovPage).into(holder.movImgSmall)

    }

    override fun getItemCount(): Int {
       return moviesItems.size
    }

}