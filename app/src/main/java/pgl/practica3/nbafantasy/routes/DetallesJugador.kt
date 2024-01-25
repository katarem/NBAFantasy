package pgl.practica3.nbafantasy.routes

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pgl.practica3.nbafantasy.R
import pgl.practica3.nbafantasy.model.BaseDeDatos
import pgl.practica3.nbafantasy.model.Jugador
import pgl.practica3.nbafantasy.model.JugadorDetails

@Composable
fun DetallesJugador(navController: NavController?, jugador: Jugador){
    val jugadorDetails = getDetallesJugador(jugador.nombre)
    val colorEquipo = setColorEquipo(jugador.equipo)
    Column(
        modifier = Modifier
            .background(colorEquipo)
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "${jugador.nombre} - ${jugador.equipo}", fontSize = 20.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth())
        Image(painter = painterResource(id = jugadorDetails.img), contentDescription = "a",
            contentScale = ContentScale.Inside, modifier = Modifier.height(300.dp))
        Text(text = jugadorDetails.description, modifier = Modifier.fillMaxWidth().padding(10.dp),
            textAlign = TextAlign.Justify)
    }
    BackHandler {
        navController?.popBackStack()
    }
}

fun getDetallesJugador(nombre: String): JugadorDetails{
    return BaseDeDatos.jugadoresDetails.filter { detail -> detail.jugador.nombre == nombre }.first()
}



@Preview(showBackground = true)
@Composable
fun DetallesJugadorPreview(){
    DetallesJugador(navController = null, jugador = BaseDeDatos.jugadores[9])
}