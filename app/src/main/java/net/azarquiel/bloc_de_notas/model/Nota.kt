package net.azarquiel.bloc_de_notas.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "nota")
data class Nota (@PrimaryKey(autoGenerate = true)
                 @ColumnInfo(name = "id") // nombre en tabla
                 var id: Int?=0,          // atributo en entity
                 @ColumnInfo(name = "fecha")
                 var fecha:Date=Date(),
                 @ColumnInfo(name = "titulo")
                 var titulo:String="",
                 @ColumnInfo(name = "texto")
                 var texto:String=""):Serializable
