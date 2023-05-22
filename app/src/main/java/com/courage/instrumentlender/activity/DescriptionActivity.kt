package com.courage.instrumentlender.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.courage.instrumentlender.R
import com.courage.instrumentlender.fragment.HomeFragment
import com.courage.instrumentlender.model.Instrument
import java.text.SimpleDateFormat

class DescriptionActivity : AppCompatActivity() {
    lateinit var instrumentImage : ImageView
    lateinit var instrumentName: TextView
    lateinit var instrumentPrice : TextView
    lateinit var instrumentOwnerName : TextView
    lateinit var instrumentOwnerNumber : TextView
    lateinit var btnCallOwner : Button
    lateinit var btnAddToCart : Button
    lateinit var instrumentDescription : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

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

        instrumentImage=findViewById(R.id.instrumentImage)
        instrumentName=findViewById(R.id.instrumentName)
        instrumentPrice=findViewById(R.id.instrumentPrice)
        instrumentOwnerName=findViewById(R.id.instrumentOwnerName)
        instrumentDescription=findViewById(R.id.instrumentDescription)
        instrumentOwnerNumber=findViewById(R.id.instrumentOwnerNumber)
        btnCallOwner=findViewById(R.id.btnCallOwner)
        btnAddToCart=findViewById(R.id.btnAddToCart)

        var instrumentId=0
        if(intent!=null){
            instrumentId=intent.getIntExtra("instrument_id",0)!!.toInt()
        }
        val instrument=instrumentList[instrumentId]


        instrumentName.text = instrument!!.instrumentName
        instrumentPrice.text=instrument!!.instrumentPrice
        instrumentOwnerName.text = instrument!!.instrumentLenderName
        instrumentDescription.text = instrument!!.instrumentDescription
        instrumentOwnerNumber.text = instrument!!.instrumentLenderContact
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

        btnAddToCart.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("instrument_id",instrumentId)
            startActivity(intent)
        }
        btnCallOwner.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data= Uri.parse("tel:" + instrument.instrumentLenderContact)
            startActivity(dialIntent)
        }
    }
}