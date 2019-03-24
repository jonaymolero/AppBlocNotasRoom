package net.azarquiel.bloc_de_notas.model

import android.arch.persistence.room.*
import android.content.Context

@Database(entities = [Nota::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class NotaDB: RoomDatabase() {
    abstract fun notaDao():NotaDao
    companion object {
        @Volatile
        private var INSTANCE: NotaDB? = null

        fun getDatabase(context: Context): NotaDB? {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            NotaDB::class.java, "notaDB"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}
