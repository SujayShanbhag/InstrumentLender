package com.courage.instrumentlender.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.courage.instrumentlender.R
import com.courage.instrumentlender.adapter.InstrumentAdapter
import com.courage.instrumentlender.model.Instrument
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var recyclerHome : RecyclerView
    lateinit var layoutManager : LayoutManager
    lateinit var recyclerAdapter : InstrumentAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_home, container, false)

        recyclerHome=view.findViewById(R.id.recyclerHome)
        layoutManager= GridLayoutManager(activity as Context,2)



        val dateFormat = SimpleDateFormat("dd-MM-yyyy")

        // Create a string representing the desired date
        val dateString = "10-05-2023"
        val toDate="20-05-2023"
            // Parse the string into a Date object using the custom format
        val date = dateFormat.parse(dateString)
        val lastDate=dateFormat.parse(toDate)
        val instrumentObject=Instrument(1,"Grail","6000",
            "An Acoustic guitar","9986441734",date,lastDate,
            "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1392344536l/366522.jpg")

        val instrumentList= arrayListOf<Instrument>(instrumentObject)


        recyclerAdapter= InstrumentAdapter(activity as Context,instrumentList)
        recyclerHome.adapter=recyclerAdapter
        recyclerHome.layoutManager=layoutManager

        recyclerHome.addItemDecoration(
            DividerItemDecoration(
                recyclerHome.context,
                (layoutManager as GridLayoutManager).orientation
            )
        )

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}