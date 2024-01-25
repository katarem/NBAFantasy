package pgl.practica3.nbafantasy.routes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
import java.util.Collections

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuPrincipal(navController: NavController?, listaImportada: ArrayList<Jugador>) {

    val query = remember { mutableStateOf("") }
    val listaJugadores = remember { mutableStateOf(listaImportada) }
    val equipos: Set<String> = listaJugadores.value.map { it.equipo }.toSet()
    val searchActive = remember { mutableStateOf(false) }
    val editMode = remember { mutableStateOf(false) }
        Column (
            modifier = Modifier.fillMaxWidth()
        ){
            Text(text = "Bienvenido a tu fantasy league",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp))
            SearchBar(
                query = query.value, onQueryChange = {
                    query.value = it
                    if (query.value.isEmpty()) listaJugadores.value = listaImportada
                    else listaJugadores.value = listaImportada.filter { jugador -> jugador.equipo.startsWith(query.value,true) } as ArrayList<Jugador>
                }, onSearch = {}, active = searchActive.value, onActiveChange = {searchActive.value = !searchActive.value},
                shape = RectangleShape,
                placeholder = { Text(text = "Busca a jugadores por equipo") },
                colors = SearchBarDefaults.colors(containerColor = Color(R.color.white)),
                leadingIcon = {
                    Icon(painter = painterResource(id = R.drawable.baseline_sports_basketball_24), contentDescription = "")
                },
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Text(text = "Busca a jugadores por equipo...")
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ){
                    equipos.forEachIndexed{_, equipo ->
                        item{
                            EquipoComponent(equipo = equipo) {
                                searchActive.value = !searchActive.value
                                query.value = equipo
                                listaJugadores.value = listaImportada.filter { jugador -> jugador.equipo.equals(query.value) } as ArrayList<Jugador>
                            }
                        }
                    }
                }
            }
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ){
                listaJugadores.value.forEachIndexed{ _, jugador ->
                    item{
                        JugadorComponent(jugador = jugador, editMode.value){
                            listaImportada.remove(jugador)
                            listaJugadores.value = listaImportada
                        }
                    }
                }
            }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)

        ) {
            if(!editMode.value){
                ExtendedFloatingActionButton(onClick = { navController?.navigate(Rutas.CrearJugadores.ruta) }) {
                    Text(text = "Add")
                    Icon(painter = painterResource(id = R.drawable.baseline_add_circle_24), contentDescription = "")
                }
            }
            ExtendedFloatingActionButton(onClick = {
                editMode.value = !editMode.value
            }) {
                Text(text = "Delete")
                Icon(painter = painterResource(id = R.drawable.baseline_delete_24), contentDescription = "")
            }

        }
    }
}
@Composable
fun EquipoComponent(equipo: String, onClick : () -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Icon(painter = painterResource(id = R.drawable.baseline_star_24), contentDescription = "estrella")
        Text(text = equipo)
        Icon(painter = painterResource(id = R.drawable.baseline_account_box_24), contentDescription = "icono")
    }
}


@Composable
fun JugadorComponent(jugador: Jugador, editMode: Boolean, onChecked: () -> Unit){
    val checked = remember { mutableStateOf(false) }
    val teamColor = setColorEquipo(jugador.equipo)
    val playerIcon = setImagenJugador(jugador.nombre)

        if(checked.value && !editMode) onChecked()
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(5.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(teamColor),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = playerIcon), contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.weight(2f)
                ) {
                    Text(text = jugador.nombre)
                    Text(text = "VS ${jugador.equipoRival}, ${jugador.fechaPartido}")
                    Text(text = "" + jugador.puntos + "PP")
                }
                if (editMode) {
                    Checkbox(checked = checked.value, onCheckedChange = {
                        checked.value = !checked.value

                    }, enabled = true, modifier = Modifier.weight(1f))
                }
            }
    }
}

fun setColorEquipo(equipo: String): Color{
    return when(equipo) {
        "Los Angeles Lakers" -> Color(200,200,60)
        "Philadelphia" -> Color(40,200,60)
        "Golden State Warriors" -> Color(255,0,0)
        "Miami Heat" -> Color(0,0,255)
        "Milwaukee Bucks" -> Color(50,100,50)
        "Brooklyn Nets" -> Color(100,20,100)
        "Utah Jazz" -> Color(69,69,69)
        "Phoenix Suns" -> Color(20,80,100)
        "Dallas Mavericks" -> Color.Gray
        "Los Angeles Clippers" -> Color.Magenta
        else -> Color.White
    }
}

fun setImagenJugador(nombre: String): Int{
    return when(nombre){
        "Lebron James" -> R.drawable.lebron
        "Stephen Curry" -> R.drawable.stephen_curry
        "Kevin Durant" -> R.drawable.kevin
        "Giannis Antetokounmpo" -> R.drawable.giannis
        "Jimmy Butler" -> R.drawable.jimmy
        "Joel Embiid" -> R.drawable.joel
        "Kawhi Leonard" -> R.drawable.kawhi
        "Devin Booker" -> R.drawable.devin
        "Donovan Mitchell" -> R.drawable.donovan
        "Luka Dončić" -> R.drawable.luka
        else -> R.drawable.lebron
    }
}





@Preview(showBackground = true)
@Composable
fun MenuPreview(){
    MenuPrincipal(
        navController = null, listaImportada = BaseDeDatos.jugadores)
}

//@Preview(showBackground = true)
//@Composable
//fun EquipoComponentPreview(){
//    EquipoComponent(BaseDeDatos.Equipos.Philadelphia)
//}
