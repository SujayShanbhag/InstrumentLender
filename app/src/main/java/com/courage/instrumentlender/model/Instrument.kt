package com.courage.instrumentlender.model

import java.util.Date

data class Instrument (
    val instrumentId : Int,
    val instrumentName : String,
    val instrumentPrice : String,
    val instrumentDescription : String,
    val instrumentLenderName: String,
    val instrumentLenderContact : String,
    val from : Date,
    val till : Date,
    val instrumentImg : String
)