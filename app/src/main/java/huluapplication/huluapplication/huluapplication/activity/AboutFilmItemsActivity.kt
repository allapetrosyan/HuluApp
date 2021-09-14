package huluapplication.huluapplication.huluapplication.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.ImageButton
import android.widget.ImageView

import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import huluapplication.R
import huluapplication.huluapplication.huluapplication.HomeFragment
import huluapplication.huluapplication.huluapplication.adapter.RecomendedAdapter
import huluapplication.huluapplication.huluapplication.model.FilmGlobalData
import huluapplication.huluapplication.huluapplication.model.FilmItem
import huluapplication.huluapplication.huluapplication.viewmodel.ViewModelForActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.reflect.Type

class AboutFilmItemsActivity : AppCompatActivity() {

    private lateinit var recAdapter: RecomendedAdapter
    private val viewModel: ViewModelForActivity by viewModel()
    private lateinit var recyclerView: RecyclerView
    var currentSelectedItem: FilmItem? = null
    private lateinit var savedBtn: ImageButton
    private var isItemFavorite = false
    private var favoriteList = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_item)
        savedBtn = findViewById(R.id.saveDataInSavedFragment)
        val playYoutubeBtn: ImageButton = findViewById(R.id.youtubePlay)
        val bigImg = findViewById<ImageView>(R.id.bigImg)
        val backHomePageBtn: ImageButton = findViewById(R.id.backHomePage)
        val filmName: TextView = findViewById(R.id.filmName)
        val aboutFilm: TextView = findViewById(R.id.aboutFilm)
        val rating: TextView = findViewById(R.id.ratingInJson)
        val releaseDate: TextView = findViewById(R.id.releaseDataInJson)
        val genres: TextView = findViewById(R.id.genresInJson)
        recyclerView = findViewById(R.id.recomendRec)
          viewModel.getMutableLiveData()
        viewModel.filmList.observe(this, Observer {
            initRecView(it)
        })


        backHomePageBtn.setOnClickListener {
            val fragment: Fragment = HomeFragment.newInstance()
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.conteinerFilms, fragment).commit()
            finish()
        }
        currentSelectedItem = intent.getParcelableExtra("film_data")

        favoriteList = ArrayList(loadSharedPreferencesLogList())
        favoriteList.let {
            if (it.isNotEmpty() && it.contains(currentSelectedItem?.filmNameText)) {
                isItemFavorite = true
            }
        }
        if (isItemFavorite) {
            savedBtn.setBackgroundResource(R.drawable.selectedsave)
        } else {
            savedBtn.setBackgroundResource(R.drawable.saved)
        }

        playYoutubeBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(currentSelectedItem?.filmYoutube))
            startActivity(intent)
        }

        currentSelectedItem?.let {
            Glide.with(applicationContext).load(it.filmBigImg).into(bigImg)
            filmName.text = it.filmNameText
            aboutFilm.text = it.aboutFilm
            rating.text = it.filmRating
            releaseDate.text = it.filmReleaseDate
            genres.text = it.filmGenres
        }
        savedBtn.setOnClickListener {
            var filmName = ""
            currentSelectedItem?.let {
                filmName = it.filmNameText
            }

            if (isItemFavorite) {
                savedBtn.setBackgroundResource(R.drawable.saved)
                favoriteList.remove(filmName)
            } else {
                savedBtn.setBackgroundResource(R.drawable.selectedsave)
                if (favoriteList.contains(filmName)) {
                    return@setOnClickListener
                }
                favoriteList.add(filmName)
            }
            saveSharedPreferencesLogList(favoriteList.toList())
        }
    }


    fun loadSharedPreferencesLogList(): List<String> {
        var savedCollage: List<String> = ArrayList()
        val mPrefs: SharedPreferences =
            applicationContext.getSharedPreferences("PhotoCollage", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = mPrefs.getString("myJson", "")
        savedCollage = if (json!!.isEmpty()) {
            ArrayList()
        } else {
            val type: Type = object : TypeToken<List<String?>?>() {}.type
            gson.fromJson(json, type)
        }
        return savedCollage
    }

    fun saveSharedPreferencesLogList(collageList: List<String?>?) {
        val mPrefs = applicationContext.getSharedPreferences("PhotoCollage", MODE_PRIVATE)
        val prefsEditor = mPrefs.edit()
        val gson = Gson()
        val json = gson.toJson(collageList)
        prefsEditor.putString("myJson", json)
        prefsEditor.commit()
    }

    private fun initRecView(list: List<FilmGlobalData>){
        val arrayListOfSearchItems = ArrayList(list[0].listOfFilmItem)
        recAdapter = RecomendedAdapter(arrayListOfSearchItems)
        recyclerView.adapter = recAdapter
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,
            false)
    }
}
