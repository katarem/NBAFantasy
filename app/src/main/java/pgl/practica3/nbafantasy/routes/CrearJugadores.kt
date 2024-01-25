package pgl.practica3.nbafantasy.routes

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pgl.practica3.nbafantasy.model.BaseDeDatos
import pgl.practica3.nbafantasy.model.Jugador
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrearJugadores(navController: NavController?, jugadores: ArrayList<Jugador>){
    var nombreJugador by remember{ mutableStateOf("") }
    var nombreEquipo by remember{ mutableStateOf("") }
    var nombreEquipoRival by remember { mutableStateOf("") }
    val estadoScroll = rememberScrollState()
    val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Input, initialSelectedDateMillis = System.currentTimeMillis())
    var expandedTeams by remember { mutableStateOf(false) }
    var expandedRivalTeams by remember { mutableStateOf(false) }
    var expandedNames by remember { mutableStateOf(false) }
    var puntosObtenidos by remember { mutableStateOf(0f) }
    var fechaPartido by remember { mutableStateOf(Calendar.getInstance()) }
    val posicionesJugadas = remember{ mutableStateOf(arrayListOf<String>()) }
    val equipos: Set<String> = jugadores.map { it.equipo }.toSet()
    val jugadoresNombres: Set<String> = jugadores.map { it.nombre }.toSet()
    Column(
        Modifier
            .padding(10.dp)
            .verticalScroll(estadoScroll)
    ) {
        Text(text = "Introduce el nombre del jugador:")
        ExposedDropdownMenuBox(expanded = expandedNames,
            onExpandedChange = {
                expandedNames = !expandedNames
            }) {
            TextField(
                value = nombreJugador,
                readOnly = false,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                label = { Text("Seleccionar opción") },
            )
            DropdownMenu(expanded = expandedNames, onDismissRequest = { expandedNames = false }) {
                for (jugador in jugadoresNombres){
                    DropdownMenuItem(text = { Text(text = jugador)},
                        onClick = { nombreJugador = jugador
                                    expandedNames = false})
                }
            }
        }
        Text(text = "El equipo en el que juega:")
        ExposedDropdownMenuBox(expanded = expandedTeams,
            onExpandedChange = {
                expandedTeams = !expandedTeams
            }) {
            TextField(
                value = nombreEquipo,
                readOnly = true,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                label = { Text("Seleccionar opción") },
            )
            DropdownMenu(expanded = expandedTeams, onDismissRequest = { expandedTeams = false }) {
                for (equipo in equipos){
                    DropdownMenuItem(text = { Text(text = equipo)},
                        onClick = { nombreEquipo = equipo
                                    expandedTeams = false})
                }
            }
        }
        Text(text = "Selecciona una o más posiciones:")
        Column(
        ) {
            PosicionComponent(posicion = "PF",posicionesJugadas.value)
            PosicionComponent(posicion = "C",posicionesJugadas.value)
            PosicionComponent(posicion = "PG",posicionesJugadas.value)
            PosicionComponent(posicion = "SG",posicionesJugadas.value)
            PosicionComponent(posicion = "SF", posicionesJugadas.value)
        }
        Text(text = "El número de puntos que metió en el partido:")
        Slider(value = puntosObtenidos, onValueChange = { it -> puntosObtenidos=it}, valueRange = 0f..100f, steps = 100)
        Text(text = "${puntosObtenidos.toInt()}")
        Text(text = "El equipo contra el que se jugó:")
        ExposedDropdownMenuBox(expanded = expandedRivalTeams,
            onExpandedChange = {
                expandedRivalTeams = !expandedRivalTeams
            }) {
            TextField(
                value = nombreEquipoRival,
                readOnly = true,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                label = { Text("Seleccionar opción") },
            )
            DropdownMenu(expanded = expandedRivalTeams, onDismissRequest = { expandedRivalTeams = false }) {
                for (equipo in equipos){
                    DropdownMenuItem(text = { Text(text = equipo)},
                        onClick = { nombreEquipoRival = equipo
                            expandedRivalTeams = false})
                }
            }
        }
        DatePicker(state = datePickerState, modifier = Modifier.padding(16.dp), title = { "Selecciona la fecha del partido:" },)    }

        BackHandler {
            val jugador = Jugador(nombreJugador,nombreEquipo,
                posicionesJugadas.value,puntosObtenidos.toInt(),nombreEquipoRival, fechaPartido.toString())
            jugadores.add(jugador)
            BaseDeDatos.jugadores.add(jugador)
            navController?.popBackStack()
        }

}


@Composable
fun PosicionComponent(posicion: String, posicionesElegidas: ArrayList<String>){
    var selected by remember { mutableStateOf(false) }
    if(selected && !posicionesElegidas.contains(posicion)) posicionesElegidas.add(posicion)
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(selected = selected, onClick = {
            selected = !selected
        })
        Text(text = posicion)
    }
}

@Preview(showBackground = true)
@Composable
fun CrearPreview(){
    CrearJugadores(
        navController = null, BaseDeDatos.jugadores)
}