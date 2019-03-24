package net.azarquiel.bloc_de_notas.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.rownotas.view.*
import net.azarquiel.bloc_de_notas.Utilidades.Utilidades
import net.azarquiel.bloc_de_notas.model.Nota
import net.azarquiel.bloc_de_notas.model.NotaViewModel
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton

class CustomAdapter(
        val context: Context,
        val layout: Int,
        val notaViewModel: NotaViewModel
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private var dataList: List<Nota> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context,notaViewModel)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setNotas(notas: List<Nota>) {
        this.dataList = notas
        notifyDataSetChanged()
    }

    class ViewHolder( viewlayout: View, val context: Context,val notaViewModel: NotaViewModel
    ) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Nota){
            itemView.tvFechaRow.text=Utilidades.cambiarFecha(dataItem.fecha)
            itemView.tvTituloRow.text=dataItem.titulo
            itemView.tag=dataItem
            itemView.setOnLongClickListener{eliminarNota(dataItem)}
        }

        fun eliminarNota(nota:Nota):Boolean{
            context.alert("¿Seguro eliminar ${nota.titulo}?", "Confirm") {
                yesButton {notaViewModel.delete(nota.id!!) }
                noButton {}
            }.show()
            return true
        }
    }
}
