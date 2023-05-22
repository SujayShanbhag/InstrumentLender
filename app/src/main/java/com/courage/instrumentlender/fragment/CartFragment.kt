package com.courage.instrumentlender.fragment

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.courage.instrumentlender.R
import com.courage.instrumentlender.activity.MainActivity
import com.courage.instrumentlender.model.Instrument
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONObject
import java.text.SimpleDateFormat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CartFragment(val instrumentId : Int) : Fragment(), PaymentResultListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var instrumentImage : ImageView
    lateinit var instrumentDescription : TextView
    lateinit var instrumentName : TextView
    lateinit var instrumentPrice : TextView
    lateinit var cartEmpty : TextView
    lateinit var cart : RelativeLayout
    lateinit var subTotal : TextView
    lateinit var total : TextView
    lateinit var btnMakePayment : Button
    lateinit var instrument : Instrument

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_cart, container, false)


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

        subTotal=view.findViewById(R.id.subTotal)
        total=view.findViewById(R.id.total)
        cart=view.findViewById(R.id.cart)
        cartEmpty = view.findViewById(R.id.txtEmptyCart)
        instrumentImage = view.findViewById(R.id.instrumentImage)
        instrumentName = view.findViewById(R.id.instrumentName)
        instrumentPrice = view.findViewById(R.id.instrumentPrice)
        instrumentDescription = view.findViewById(R.id.instrumentDescription)
        btnMakePayment=view.findViewById(R.id.btnMakePayment)



        Checkout.preload(activity)
        if(instrumentId!=-1) {
            instrument = instrumentList[instrumentId]


            cartEmpty.visibility = View.GONE
            instrumentName.text = instrument.instrumentName
            instrumentPrice.text = instrument.instrumentPrice
            instrumentDescription.text = instrument.instrumentDescription
            subTotal.text=instrument.instrumentPrice
            total.text=instrument.instrumentPrice
            instrumentImage.setImageResource(
                if (instrument.instrumentImg == "guitar")
                    R.drawable.guitar
                else if (instrument.instrumentImg == "piano")
                    R.drawable.piano
                else if (instrument.instrumentImg == "drums")
                    R.drawable.drums
                else if (instrument.instrumentImg == "violin")
                    R.drawable.violin
                else if (instrument.instrumentImg == "electric_guitar")
                    R.drawable.electric_guitar
                else if (instrument.instrumentImg == "harmonium")
                    R.drawable.harmonium
                else if (instrument.instrumentImg == "flute")
                    R.drawable.flute
                else
                    R.drawable.default_instrument_img
            )
        }
        else {
            cart.visibility=View.GONE
        }
        btnMakePayment.setOnClickListener {
            savePayments(instrument.instrumentPrice.trim().toInt())
        }
        return view
    }
    private fun savePayments(amount : Int) {
        val checkout = Checkout()
        checkout.setKeyID("F4gS7zimSVG9W0b9rWPhuxEV")
        try {
            val options = JSONObject()
            options.put("name","Melody Swaps")
            options.put("description","Happy Playing")
            //options.put("image",R.drawable.logo)
            options.put("theme.color","#F9D949")
            options.put("currency","INR")
            options.put("amount",amount*100)

            val retryObj=JSONObject()
            retryObj.put("enabled",true)
            retryObj.put("max_count",4)
            options.put("retry",retryObj)

            checkout.open(activity,options)

        }catch (e : Exception){

        }
    }


    override fun onPaymentSuccess(p0: String?) {
        Toast.makeText(context,"Payment Successfull!!",Toast.LENGTH_SHORT).show()
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        Toast.makeText(context,"Payment failed!!",Toast.LENGTH_SHORT).show()
    }


}