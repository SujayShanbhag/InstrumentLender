package com.courage.instrumentlender.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.courage.instrumentlender.R
import com.courage.instrumentlender.activity.DescriptionActivity
import com.courage.instrumentlender.model.Instrument
import com.squareup.picasso.Picasso

class InstrumentAdapter(val context : Context,val itemList : ArrayList<Instrument>)
    :RecyclerView.Adapter<InstrumentAdapter.InstrumentViewHolder>() {
    class InstrumentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val instrumentName : TextView=view.findViewById(R.id.instrumentName)
        val instrumentPrice : TextView=view.findViewById(R.id.instrumentPrice)
        val instrumentImg : ImageView =view.findViewById(R.id.instrumentImg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstrumentViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.single_instrument,parent,false)

        return InstrumentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: InstrumentViewHolder, position: Int) {
        val instrument=itemList[position]
        holder.instrumentName.text=instrument.instrumentName
        holder.instrumentPrice.text=instrument.instrumentPrice
        Picasso.get().load(instrument.instrumentImg).error(R.drawable.default_instrument_img).into(holder.instrumentImg)

        //old code -> holder.item.setOnClickListener {
        holder.itemView.setOnClickListener {
            val intent= Intent(context, DescriptionActivity::class.java)
            intent.putExtra("instrument_id",instrument.instrumentId)
            context.startActivity(intent)
        }
    }
}
