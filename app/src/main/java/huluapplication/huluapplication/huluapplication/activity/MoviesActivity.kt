package huluapplication.huluapplication.huluapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import huluapplication.R
import huluapplication.huluapplication.huluapplication.adapter.MoviesPageAdapter
import huluapplication.huluapplication.huluapplication.adapter.RecomendedAdapter
import huluapplication.huluapplication.huluapplication.model.FilmGlobalData
import huluapplication.huluapplication.huluapplication.viewmodel.ViewModelForActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesActivity : AppCompatActivity() {
    private var janr:String? = ""
    private lateinit var recAdapter: MoviesPageAdapter
    private lateinit var recyclerView: RecyclerView
    private val viewModel: ViewModelForActivity by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        recyclerView = findViewById(R.id.moviesRec)
        /*  viewModel.filmList.observe(this, Observer {
            initRecView(it)
        })*/


        janr = intent.getStringExtra("film_data")
        Log.d("dwd", "janr on received Activity = " + janr)

        /*}
    private fun initRecView(list: List<FilmGlobalData>){
        val arrayListOfMovItems = ArrayList(list[0].listOfFilmItem)
        recAdapter = MoviesPageAdapter(arrayListOfMovItems)
        recyclerView.adapter = recAdapter
        recyclerView.layoutManager = GridLayoutManager(this,3)
    }*/

    }
}