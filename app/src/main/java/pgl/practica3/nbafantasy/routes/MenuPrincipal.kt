package pgl.practica3.nbafantasy.routes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pgl.practica3.nbafantasy.model.Jugador
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuPrincipal(navController: NavController, listaImportada: Array<Jugador>){

    var listaJugadores = remember { mutableListOf(listaImportada) }

    Column (){
        Text(text = "Bienvenido a tu fantasy league")
        SearchBar(query = "", onQueryChange = {}, onSearch = {}, active = false, onActiveChange = {}) {

        }
        LazyColumn(){
            items(listaJugadores){

            }
        }
    }
}

@Composable
fun JugadorComponent(jugador: Jugador){

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(10.dp)
    ) {
        Text(text = jugador.nombre)
        Text(text = jugador.equipo)
        Text(text = jugador.posiciones[0])
    }
}


@Preview(showBackground = true)
@Composable
fun JugadorCardPreview(){
    
    JugadorComponent(jugador = Jugador("Lebron James", "bulls", ,10,"rival",Date(2001,11,9)))
    
    
}