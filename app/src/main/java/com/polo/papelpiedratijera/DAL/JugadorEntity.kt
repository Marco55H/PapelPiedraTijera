package com.polo.papelpiedratijera.DAL

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jugadores")
data class JugadorEntity (
    @PrimaryKey(autoGenerate = true)
    var name:String = "",
    var partidasJugadas:Int = 0,
    var partidasGanadas:Int = 0,
    var luchasGanadas:Int = 0
)
