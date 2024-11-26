package cl.alincoleo.android.examenprogramacionii

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.alincoleo.android.examenprogramacionii.ClaseServicio.BaseDeDatos
import cl.alincoleo.android.examenprogramacionii.ClaseServicio.GastoBasico
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppServiciosBasicos()
        }
    }
}

@Composable
fun AppServiciosBasicos(
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = "Formulario"
    ){
        composable("Formulario"){
            FormularioUI( onClickirAListado = {navController.navigate("Listado")})
        }
        composable("Listado"){
            ListadoUI(onClickirAFormulario = {navController.navigate("Formulario")})
        }
    }
}

@Composable
fun ListadoUI(
    onClickirAFormulario:()->Unit={}
) {
    Column {
        Text("hola listado")
        Button(onClick = {onClickirAFormulario()}) {
            Text("ir a inicio")
        }
    }

}

@Composable
fun FormularioUI(
    onClickirAListado:()->Unit={}
) {
    val contexto = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    Column {
        var tipoGasto by remember { mutableStateOf("Luz") }
        var monto by remember { mutableStateOf("30000") }
        var fecha by remember { mutableStateOf("2024-11-25") }

        TextField(
            value = tipoGasto,
            onValueChange = {tipoGasto=it},
            label={ Text("tipo de gasto")}
        )

        TextField(
            value = monto,
            onValueChange = {monto=it},
            label={ Text("monto boleta/fatura")}
        )

        TextField(
            value = fecha,
            onValueChange = {fecha=it},
            label={ Text("fecha formato AAAA-MM-DD")}
        )

        Button(onClick = {
            coroutineScope.launch(Dispatchers.IO){
                val bd = BaseDeDatos.getInstance(contexto)
                val dao = bd.gastoBasicoDAO()
                val gastoBasico=GastoBasico(
                    tipoGasto = tipoGasto,
                    monto = monto,
                    fecha = fecha
                )
                dao.insertAll(gastoBasico)
            }

        }) {Text("Registrar medición")}

        Button(onClick = {onClickirAListado()}) {
            Text("ir a listado")
        }
    }

}