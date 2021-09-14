package huluapplication.huluapplication.huluapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import huluapplication.R
import huluapplication.huluapplication.huluapplication.model.FilmItem

class RecomendedAdapter (var recItems: List<FilmItem>): RecyclerView.Adapter<RecomendedAdapter.ViewHolder>() {


    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        val recPageImg = view.findViewById<ImageView>(R.id.recImg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rec_recomend,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val myRecItem = recItems[position]
        val imgForRecPage: String = myRecItem.filmSmallImg
        Glide.with(holder.recPageImg.context).load(imgForRecPage).into(holder.recPageImg)
    }

    override fun getItemCount(): Int {
        return recItems.size
    }
}