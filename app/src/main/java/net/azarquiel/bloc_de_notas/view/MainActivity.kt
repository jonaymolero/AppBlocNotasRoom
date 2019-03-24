package net.azarquiel.bloc_de_notas.view

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import net.azarquiel.bloc_de_notas.R
import net.azarquiel.bloc_de_notas.Utilidades.Utilidades
import net.azarquiel.bloc_de_notas.adapter.CustomAdapter
import net.azarquiel.bloc_de_notas.model.Nota
import net.azarquiel.bloc_de_notas.model.NotaViewModel
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity(),SearchView.OnQueryTextListener {

    companion object {
        private const val REQUEST_ADD=0
    }
    private lateinit var adapter:CustomAdapter
    private lateinit var notaViewModel: NotaViewModel
    private lateinit var searchView: SearchView
    private var isAdd:Boolean=true
    private lateinit var listaNotas:ArrayList<Nota>
    private lateinit var listaOriginal:ArrayList<Nota>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        listaNotas= ArrayList()
        listaOriginal=ArrayList()
        notaViewModel = ViewModelProviders.of(this).get(NotaViewModel::class.java)
        notaViewModel.lista_notas.observe(this, Observer { notas ->

            notas?.let {
                listaNotas.clear()
                listaOriginal.clear()
                listaNotas.addAll(it)
                listaOriginal.addAll(it)
                adapter.setNotas(listaNotas)
            }
        })
        showdata()
        fab.setOnClickListener { addNota() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_ADD) {
            if(isAdd){
                val note = data!!.getSerializableExtra("note") as Nota
                notaViewModel.insert(note)
            }else{
                val note = data!!.getSerializableExtra("note") as Nota
                notaViewModel.update(note)
            }
        }
    }

    private fun showdata() {
        adapter= CustomAdapter(this,R.layout.rownotas,notaViewModel)
        rvNotas.layoutManager=LinearLayoutManager(this)
        rvNotas.adapter=adapter
    }

    private fun addNota(){
        isAdd=true
        enviarDatosIntent(null)
    }

    fun onClickEditarNota(v:View){
        isAdd=false
        val nota=v.tag as Nota
        enviarDatosIntent(nota)
    }

    fun enviarDatosIntent(nota: Nota?){
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra("isAdd", isAdd)
        if(!isAdd){
            intent.putExtra("nota",nota)
        }
        startActivityForResult(intent, REQUEST_ADD)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        // ************* <Filtro> ************
        val searchItem = menu.findItem(R.id.search)
        searchView = searchItem.actionView as SearchView
        searchView.setQueryHint("Search...")
        searchView.setOnQueryTextListener(this)
        // ************* </Filtro> ************

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.search->{
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(query: String?): Boolean {
        listaNotas.clear()
        listaNotas.addAll(listaOriginal.filter { p -> Utilidades.minusculas(p.titulo).contains(Utilidades.minusculas(query!!)) })
        adapter.setNotas(listaNotas)
        return false
    }
}
