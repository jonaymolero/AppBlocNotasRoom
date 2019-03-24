package net.azarquiel.bloc_de_notas.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

@Dao
interface NotaDao {
    @Query("SELECT * from nota ORDER BY fecha DESC")
    fun getAllNotas(): LiveData<List<Nota>>

    @Insert
    fun insert(nota:Nota)

    @Query("DELETE FROM nota WHERE id=:id")
    fun delete(id:Int)

    @Update
    fun update(nota: Nota)

    /*@Query("SELECT * FROM nota WHERE titulo LIKE '%'+:titulo+'%' ")
    fun buscarPorTitulo(titulo:String): List<Nota>*/
}