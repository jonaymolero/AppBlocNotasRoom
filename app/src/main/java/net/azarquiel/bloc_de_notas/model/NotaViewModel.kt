package net.azarquiel.bloc_de_notas.model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

class NotaViewModel (application: Application) : AndroidViewModel(application) {

    private var repository: NotaRepository= NotaRepository(application)

    var lista_notas: LiveData<List<Nota>> = repository.lista_notas

    fun insert(nota: Nota) {
        repository.insert(nota)
    }

    fun delete(id: Int) {
        repository.delete(id)
    }

    fun update(nota: Nota){
        repository.update(nota)
    }
}
