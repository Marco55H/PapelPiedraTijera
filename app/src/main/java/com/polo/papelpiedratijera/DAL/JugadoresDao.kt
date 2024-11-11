package com.polo.papelpiedratijera.DALs

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.polo.papelpiedratijera.DAL.JugadorEntity

@Dao
interface JugadoresDao {
    @Query("SELECT * FROM jugadores")
    suspend fun getTodo(): List<JugadorEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertar(jugadores: JugadorEntity):Long

    @Update
    suspend fun actualizar(jugadores: JugadorEntity)

    @Delete
    suspend fun borrar(jugadores: JugadorEntity)
}