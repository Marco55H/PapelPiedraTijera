package com.polo.papelpiedratijera.DAL

import androidx.room.RoomDatabase
import androidx.room.Database
import com.polo.papelpiedratijera.DALs.JugadoresDao


@Database(entities = [JugadorEntity::class], version =1)
abstract class JugadoresDatabase:RoomDatabase(){
    abstract fun tareaDao(): JugadoresDao
}