package huluapplication.huluapplication.huluapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import huluapplication.R
import huluapplication.huluapplication.huluapplication.adapter.CustomDropDownAdapter
import huluapplication.huluapplication.huluapplication.adapter.RecFirstAdapter
import huluapplication.huluapplication.huluapplication.model.Model
import huluapplication.huluapplication.huluapplication.viewmodel.MyViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import android.content.Intent
import huluapplication.huluapplication.huluapplication.activity.MoviesActivity
import huluapplication.huluapplication.huluapplication.adapter.MoviesPageAdapter


class HomeFragment : Fragment()  {
    private lateinit var recAdapter: RecFirstAdapter
    private val viewModel: MyViewModel by sharedViewModel()
    private lateinit var recyclerView: RecyclerView
    private lateinit var spinner04: Spinner
    private lateinit var modelList: List<Model>
    private lateinit var movBtn :Button
    private lateinit var tvBtn :Button

    private fun initRecView(view:View){
        recyclerView =  view.findViewById(R.id.recFirst)
        recyclerView.layoutManager = LinearLayoutManager(activity,
            LinearLayoutManager.VERTICAL,false)
        getData()
    }

    private fun getData(){
        viewModel.getMutableLiveData()
        viewModel.filmList.observe(viewLifecycleOwner, { filmGlobalDatas->
            Log.d("dwd","jhgf")
            val arrayList = ArrayList(filmGlobalDatas)
            arrayList.removeAt(0)
            recAdapter = RecFirstAdapter(arrayList)
            recyclerView.adapter = recAdapter


            modelList =  readFromAsset()
            val customDropDownAdapter = activity?.let { CustomDropDownAdapter(it, modelList) }
            spinner04.adapter = customDropDownAdapter

        })

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         spinner04 = view.findViewById(R.id.spinner04)
          movBtn = view.findViewById(R.id.moviesBtn)
          tvBtn = view.findViewById(R.id.tvShowsBtn)

        movBtn.setOnClickListener{
            val intent = Intent(activity, MoviesActivity::class.java)
            startActivity(intent)
        }

        tvBtn.setOnClickListener{
            val intent = Intent(activity, MoviesActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_home, container, false)
        initRecView(view)
        return view
    }
    companion object{
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
    private fun readFromAsset(): List<Model> {
        val file_name = "android_version.json"


        val bufferReader = context?.assets?.open(file_name)?.bufferedReader()

        val json_string = bufferReader.use {
            it?.readText()
        }
        val gson = Gson()
        return gson.fromJson(json_string, Array<Model>::class.java).toList()
    }
}

