package huluapplication.huluapplication.huluapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import huluapplication.R
import huluapplication.huluapplication.huluapplication.adapter.NotPageAdapter


class NotificationFragment : Fragment() {
    private lateinit var recAdapter: NotPageAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private fun initRecView(view:View){
        recyclerView =  view.findViewById(R.id.notRec)
        recyclerView.layoutManager = LinearLayoutManager(activity,
            LinearLayoutManager.VERTICAL,false)
        recyclerView.adapter = recAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.notRec)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         val view = inflater.inflate(R.layout.fragment_notification, container, false)
        initRecView(view)
        return view
    }

        fun newInstance() =
            NotificationFragment()
}