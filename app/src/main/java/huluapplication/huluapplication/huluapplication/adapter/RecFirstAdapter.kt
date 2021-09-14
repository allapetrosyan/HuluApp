package huluapplication.huluapplication.huluapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import huluapplication.R
import huluapplication.huluapplication.huluapplication.model.FilmGlobalData

class RecFirstAdapter(var items: ArrayList<FilmGlobalData>): RecyclerView.Adapter<RecFirstAdapter.ViewHolder>() {
    private var context: Context? = null
    private var packageNameList = ArrayList<String>()


    inner class ViewHolder internal constructor(view: View): RecyclerView.ViewHolder(view){
        var packageName: TextView = view.findViewById(R.id.packageName)
        var recView: RecyclerView = view.findViewById(R.id.recyclerSecond)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rec_first,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val myItem = items[position]
        val filmPackageName: String = myItem.title
        holder.packageName.text = filmPackageName
        val secondAdapter = SecondRecAdapter(items[position].listOfFilmItem)
        val llm = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        holder.recView.layoutManager = llm
        holder.recView.adapter = secondAdapter


    /*    items[position]?.let {
            filmPackageName = it.title
            packageNameList.addAll(listOf(filmPackageName))
            val arrayList = ArrayList(filmGlobalDatas)
        }*/
    }


}