package cl.alincoleo.android.examenprogramacionii.ClaseServicio

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GastoBasico::class], version =1)
abstract class BaseDeDatos :RoomDatabase() {
    abstract  fun gastoBasicoDAO():GastoBasicoDAO

    //lógica para instanciar la BBDD
    companion object{
        @Volatile
        private var instance:BaseDeDatos? = null

        fun getInstance(context: Context):BaseDeDatos{
            return instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    BaseDeDatos::class.java,
                    "GastoBasico.db"
                ).build()
            }.also { instance = it }
        }
    }
}