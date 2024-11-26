package cl.alincoleo.android.examenprogramacionii.ClaseServicio

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GastoBasicoDAO {
        @Query("SELECT * FROM GastoBasico ORDER BY id")
        suspend fun getAll(): List<GastoBasico>

        @Insert
        suspend fun insertAll(vararg gastoBasico: GastoBasico)

        @Delete
        suspend fun delete(gastoBasico: GastoBasico)


}