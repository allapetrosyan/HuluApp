package huluapplication.huluapplication.huluapplication.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import huluapplication.R
import huluapplication.huluapplication.huluapplication.activity.AboutFilmItemsActivity
import huluapplication.huluapplication.huluapplication.model.FilmItem

class FavoritePageAdapter (var favoriteItems: List<FilmItem>):
    RecyclerView.Adapter<FavoritePageAdapter.ViewHolder>() {
    private var favoriteList = ArrayList<String>()


    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        val favoritePageFilmName: TextView = view.findViewById(R.id.savedPageFilmName)
        val favoritePageSmallImg: ImageView = view.findViewById(R.id.savedPageSmallImg)
        var saveBtn = view.findViewById<ImageButton>(R.id.saved)
        var watchBtn = view.findViewById<Button>(R.id.watchNow)

}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rec_saved,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val myFavItem = favoriteItems[position]
        val bigImgForFavPage: String = myFavItem.filmSmallImg
        val filmTitle: String = myFavItem.filmNameText

        Glide.with(holder.favoritePageSmallImg.context).load(bigImgForFavPage).into(holder.favoritePageSmallImg)
        holder.favoritePageFilmName.text = filmTitle

        holder.watchBtn.setOnClickListener { v ->
            val activity = v!!.context as AppCompatActivity
            val filmActivityIntent = Intent(v!!.context, AboutFilmItemsActivity::class.java)
            filmActivityIntent.putExtra("film_data",favoriteItems[position])
            activity.startActivityForResult(filmActivityIntent,1)
        }

        holder.saveBtn.setOnClickListener {
            var filmName = ""
            favoriteItems[position]?.let {
                filmName = it.filmNameText
            }

            if (favoriteItems[position].isItemFavorite) {
                favoriteList.remove(filmName)
                val newItems = ArrayList(favoriteItems)
                newItems.remove(favoriteItems[position])
                favoriteItems = newItems.toList()
                notifyDataSetChanged()
            }

            saveSharedPreferencesLogList(holder.saveBtn.context,favoriteList.toList())
        }
    }
    override fun getItemCount(): Int {
        return favoriteItems.size
    }


    fun saveSharedPreferencesLogList(context: Context,collageList: List<String?>?) {
        val mPrefs = context?.getSharedPreferences("PhotoCollage",
            AppCompatActivity.MODE_PRIVATE
        )
        val prefsEditor = mPrefs?.edit()
        val gson = Gson()
        val json = gson.toJson(collageList)
        prefsEditor?.putString("myJson", json)
        prefsEditor?.commit()
    }
    fun setFavoriteList(items:ArrayList<String>) {
        favoriteList.clear()
        favoriteList.addAll(items)
    }

}

























/* inner class ViewHolder internal constructor(view: View): RecyclerView.ViewHolder(view) {
     val favoritePageFilmName: TextView = view.findViewById(R.id.savedPageFilmName)
   //  val favoritePageRating: TextView = view.findViewById(R.id.ratingInJson)
     val favoritePageBigImg: ImageView = view.findViewById(R.id.savedPageSmallImg)
     var saveBtn = view.findViewById<Button>(R.id.watchNow)
 }

 override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
     val view = LayoutInflater.from(parent.context).inflate(R.layout.rec_saved,parent,false)
     return ViewHolder(view)
 }

 override fun onBindViewHolder(holder: ViewHolder, position: Int) {
     val myFavItem = favoriteItems[position]
     val bigImgForFavPage: String = myFavItem.filmBigImg
     val filmTitle: String = myFavItem.filmNameText
    // val rating :String = myFavItem.filmRating

     Glide.with(holder.favoritePageBigImg.context).load(bigImgForFavPage).into(holder.favoritePageBigImg)
     holder.favoritePageFilmName.text = filmTitle
    // holder.favoritePageRating.text = rating

     holder.itemView.setOnClickListener { v ->
         val activity = v!!.context as AppCompatActivity
         val filmActivityIntent = Intent(v!!.context, AboutFilmItemsActivity::class.java)
         filmActivityIntent.putExtra("film_data",favoriteItems[position])
         activity.startActivityForResult(filmActivityIntent,1)
     }

     holder.saveBtn.setOnClickListener {
         var filmName = ""
         favoriteItems[position]?.let {
             filmName = it.filmNameText
         }

         if (favoriteItems[position].isItemFavorite) {
             //savedBtn.setBackgroundResource(R.drawable.white_hearth)
             favoriteList.remove(filmName)
             val newItems = ArrayList(favoriteItems)
             newItems.remove(favoriteItems[position])
             favoriteItems = newItems.toList()
             notifyDataSetChanged()
         }

         saveSharedPreferencesLogList(holder.saveBtn.context,favoriteList.toList())
     }
 }

 override fun getItemCount(): Int {
     return favoriteItems.size
 }


 fun saveSharedPreferencesLogList(context: Context, collageList: List<String?>?) {
     val mPrefs = context?.getSharedPreferences("PhotoCollage",
         AppCompatActivity.MODE_PRIVATE
     )
     val prefsEditor = mPrefs?.edit()
     val gson = Gson()
     val json = gson.toJson(collageList)
     prefsEditor?.putString("myJson", json)
     prefsEditor?.commit()
 }

 fun setFavoriteList(items:ArrayList<String>) {
     favoriteList.clear()
     favoriteList.addAll(items)
 }

}*/
