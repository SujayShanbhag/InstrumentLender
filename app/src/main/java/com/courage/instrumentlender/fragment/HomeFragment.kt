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

        var instrumentObject= Instrument(0,"Grail","6000",
            "An Acoustic guitar","Sujay","9986441734",date,lastDate,
            "guitar")

        val instrumentList= arrayListOf<Instrument>()
        instrumentList.add(instrumentObject)
        instrumentObject= Instrument(1,"yamaha","1100",
            "An electric piano","akshay","9380036809",date,lastDate,
            "piano")
        instrumentList.add(instrumentObject)

        instrumentObject= Instrument(2,"Bansuri","700",
            "An Indian flute","Akash","9449119083",date,lastDate,
            "flute")
        instrumentList.add(instrumentObject)

        instrumentObject= Instrument(3,"Premium ","1200",
            "An Indian premium Harmonium ","Madhu","9480189653",date,lastDate,
            "harmonium")
        instrumentList.add(instrumentObject)
        instrumentObject= Instrument(4,"gigmaker","1500",
            "Drum Sets-Acoustic Drums","Ashruf","7348914120",date,lastDate,
            "drums")
        instrumentList.add(instrumentObject)

        instrumentObject= Instrument(5,"ibanez GRX20Z ","1100",
            "A GIO Series Electric Guitar (Black Night)","Dhiren","7624801511",date,lastDate,
            "electric_guitar")
        instrumentList.add(instrumentObject)

        instrumentObject= Instrument(6,"maple","900",
            "A German violin","pooja","9380037809",date,lastDate,
            "violin")
        instrumentList.add(instrumentObject)



        recyclerAdapter= InstrumentAdapter(activity as Context,instrumentList)
        recyclerHome.adapter=recyclerAdapter
        recyclerHome.layoutManager=layoutManager



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