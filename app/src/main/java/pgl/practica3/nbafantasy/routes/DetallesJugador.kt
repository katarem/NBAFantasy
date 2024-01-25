package pgl.practica3.nbafantasy.routes

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pgl.practica3.nbafantasy.R
import pgl.practica3.nbafantasy.model.BaseDeDatos
import pgl.practica3.nbafantasy.model.Jugador

@Composable
fun DetallesJugador(navController: NavController?, jugador: Jugador){
    val imagenJugador = setImagenJugador(jugador.nombre)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ){
        Text(text = "${jugador.nombre} - ${jugador.equipo}", fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        Image(painter = painterResource(id = imagenJugador), contentDescription = "a",
            contentScale = ContentScale.Crop)
        Text(text = "Jugador epico de la NBA", modifier = Modifier.fillMaxWidth())
    }
    BackHandler {
        navController?.popBackStack()
    }
}


@Preview(showBackground = true)
@Composable
fun DetallesJugadorPreview(){
    DetallesJugador(navController = null, jugador = BaseDeDatos.jugadores[0])
}