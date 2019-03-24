package net.azarquiel.bloc_de_notas.model

import android.app.Application
import android.arch.lifecycle.LiveData
import org.jetbrains.anko.doAsync


class NotaRepository(application: Application) {

    val notaDao = NotaDB.getDatabase(application)!!.notaDao()
    // select
    val lista_notas: LiveData<List<Nota>> = notaDao.getAllNotas()
    // insert
    fun insert(nota:Nota) {
        doAsync {
            notaDao.insert(nota)
        }
    }
    // delete
    fun delete(id:Int) {
        doAsync {
            notaDao.delete(id)
        }
    }
    //update
    fun update(nota: Nota){
        doAsync {
            notaDao.update(nota)
        }
    }
}
