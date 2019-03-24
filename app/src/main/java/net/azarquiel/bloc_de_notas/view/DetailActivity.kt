package net.azarquiel.bloc_de_notas.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import net.azarquiel.bloc_de_notas.R
import net.azarquiel.bloc_de_notas.Utilidades.Utilidades
import net.azarquiel.bloc_de_notas.model.Nota
import org.jetbrains.anko.toast
import java.util.*

class DetailActivity : AppCompatActivity() {

    private var isAdd: Boolean = true
    private lateinit var nota: Nota

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        isAdd=intent.getBooleanExtra("isAdd",true)
        if(!isAdd){
            nota=intent.getSerializableExtra("nota") as Nota
            etTituloDetail.setText(nota.titulo)
            etDescriptionDetail.setText(nota.texto)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_shave ->{
                enviarDatos()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun enviarDatos() {
        val note = Nota(if(!isAdd) nota.id else null , Date(), etTituloDetail.text.toString(), etDescriptionDetail.text.toString())
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("note", note)
        setResult(Activity.RESULT_OK,intent)
        finish()
    }
}
