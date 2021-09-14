package huluapplication.huluapplication.huluapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import huluapplication.R
import huluapplication.huluapplication.huluapplication.model.FilmItem

class NotPageAdapter(var notItems: List<FilmItem>): RecyclerView.Adapter<NotPageAdapter.ViewHolder>() {


    inner class ViewHolder internal constructor(view: View): RecyclerView.ViewHolder(view) {
        val notPageImg = view.findViewById<ImageView>(R.id.notPageSmallImg)
        val notPageAboutFilm = view.findViewById<TextView>(R.id.notPageAboutFilm)
        val notWatchNow = view.findViewById<Button>(R.id.watchNow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rec_notification,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val myNotItem = notItems[position]
        val imgForNotPage: String = myNotItem.filmSmallImg
        val filmTxt :String = myNotItem.aboutFilm
        Glide.with(holder.notPageImg.context).load(imgForNotPage).into(holder.notPageImg)
        holder.notPageAboutFilm.text = filmTxt

    }

    override fun getItemCount(): Int {
        return  notItems.size
    }

}