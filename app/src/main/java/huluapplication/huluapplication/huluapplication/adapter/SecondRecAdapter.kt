package huluapplication.huluapplication.huluapplication.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import huluapplication.R
import huluapplication.huluapplication.huluapplication.activity.AboutFilmItemsActivity
import huluapplication.huluapplication.huluapplication.model.FilmItem

class SecondRecAdapter(var item: List<FilmItem>) : RecyclerView.Adapter<SecondRecAdapter.ViewHolder>() {

    var bool:Boolean = false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecondRecAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rec_second,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SecondRecAdapter.ViewHolder, position: Int) {
        val mySecondItem = item[position]


        val smallImgForHomePage: String = mySecondItem.filmSmallImg
        val filTitle: String = mySecondItem.filmNameText
        Glide.with(holder.imgSmall.context).load(smallImgForHomePage).into(holder.imgSmall)

        holder.itemView.setOnClickListener { v ->
            val activity = v!!.context as AppCompatActivity
            val filmActivityIntent = Intent(v!!.context, AboutFilmItemsActivity::class.java)
            filmActivityIntent.putExtra("film_data",item[position])
            activity.startActivityForResult(filmActivityIntent,1)
        }

    }

    override fun getItemCount(): Int {
        return item.size
    }
    inner class ViewHolder internal constructor(view: View): RecyclerView.ViewHolder(view){
        val imgSmall: ImageView = view.findViewById(R.id.imgSmall)

    }
}