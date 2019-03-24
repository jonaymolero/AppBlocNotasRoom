package net.azarquiel.bloc_de_notas.Utilidades

import android.content.Context
import android.text.format.DateFormat
import java.security.AccessControlContext
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object Utilidades {
    fun cambiarFecha(fecha:Date):String{
        var df=SimpleDateFormat("dd/MM/yyyy HH:mm")
        return df.format(fecha)
    }
    fun minusculas(frase:String?):String{
        return frase!!.toLowerCase()
    }
}