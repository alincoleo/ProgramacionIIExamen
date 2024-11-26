package cl.alincoleo.android.examenprogramacionii.ClaseServicio

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class GastoBasico(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    val tipoGasto :String,
    val monto :String,
    val fecha : String//Date

)
