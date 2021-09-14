package huluapplication.huluapplication.huluapplication.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import huluapplication.huluapplication.huluapplication.activity.MoviesActivity
import huluapplication.R
import huluapplication.huluapplication.huluapplication.model.FilmItem
import huluapplication.huluapplication.huluapplication.model.Model


class CustomDropDownAdapter (private val context: Context, var dataSource: List<Model>)
    : BaseAdapter() {
   // var selectedFilmItem: FilmItem? = null


    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val vh: ItemHolder
        if (convertView == null) {
            view = inflater.inflate(R.layout.custom_spinner_item, parent, false)
            vh = ItemHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemHolder
        }
        vh.label.text = dataSource[position].name

        val id = context.resources.getIdentifier(dataSource[position].url,
            "drawable", context.packageName)
        vh.img.setBackgroundResource(id)

       // selectedFilmItem = intent.getParcelableExtra("film_data")
        vh.label.setOnClickListener {
            /*val intent = Intent(context, MoviesActivity::class.java)
            context.startActivity(intent)*/

            val activity = view.context as AppCompatActivity
            val filmActivityIntent = Intent(view.context, MoviesActivity::class.java)
            Log.d("dwd","janr = " + dataSource[position])
            filmActivityIntent.putExtra("film_data", dataSource[position].name)
            activity.startActivity(filmActivityIntent)
        }
        return view
    }


    override fun getItem(position: Int): Any? {
        return dataSource[position]
    }

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong();
    }

    private class ItemHolder(row: View?) {
        val label: TextView
        val img: ImageView

        init {
            label = row?.findViewById(R.id.text) as TextView
            img = row?.findViewById(R.id.img) as ImageView
        }
    }

}