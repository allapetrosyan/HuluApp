package huluapplication.huluapplication.huluapplication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import huluapplication.R
import huluapplication.huluapplication.huluapplication.adapter.FavoritePageAdapter
import huluapplication.huluapplication.huluapplication.model.FilmItem
import huluapplication.huluapplication.huluapplication.viewmodel.MyViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.lang.reflect.Type


class SavedFragment : Fragment() {
    private lateinit var recAdapter: FavoritePageAdapter
    private lateinit var recyclerView: RecyclerView
    private val viewModel: MyViewModel by sharedViewModel()
    private var allFilmItems = ArrayList<FilmItem>()
    private var favoriteList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recSaved)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_saved, container, false)
    }


        fun newInstance() = SavedFragment()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        favoriteList = ArrayList(loadSharedPreferencesLogList())

        recyclerView.layoutManager = LinearLayoutManager(activity,
            LinearLayoutManager.VERTICAL,false)
        viewModel.getMutableLiveData()
        viewModel.filmList.observe(viewLifecycleOwner, Observer {

            allFilmItems.clear()
            for (item in it) {
                allFilmItems.addAll(item.listOfFilmItem)
            }

            val onlyFavorites = ArrayList<FilmItem>()
            for (favoriteKey in favoriteList) {
                for (favoriteItem in allFilmItems) {
                    if (favoriteItem.filmNameText == favoriteKey){
                        favoriteItem.isItemFavorite = true
                        onlyFavorites.add(favoriteItem)
                    }
                }
            }
            recAdapter = FavoritePageAdapter(onlyFavorites)
            recAdapter.setFavoriteList(favoriteList)
            recyclerView.adapter = recAdapter

            Log.d("dwd","dwd " + onlyFavorites.size)
        })

    }


    fun loadSharedPreferencesLogList(): List<String> {
        var savedCollage: List<String> = ArrayList()
        context?.let {
            val mPrefs: SharedPreferences =
                it.getSharedPreferences("PhotoCollage", Context.MODE_PRIVATE)
            val gson = Gson()
            val json = mPrefs.getString("myJson", "")
            savedCollage = if (json!!.isEmpty()) {
                ArrayList()
            } else {
                val type: Type = object : TypeToken<List<String?>?>() {}.type
                gson.fromJson(json, type)
            }
        }
        return savedCollage
    }
    }

