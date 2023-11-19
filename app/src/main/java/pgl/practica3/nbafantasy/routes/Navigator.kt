package pgl.practica3.nbafantasy.routes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pgl.practica3.nbafantasy.model.BaseDeDatos
import pgl.practica3.nbafantasy.model.Jugador

@Composable
fun Navigator(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Rutas.MenuPrincipal.ruta){

        val listaJugadores: Array<Jugador> = BaseDeDatos.readData()

        composable(Rutas.MenuPrincipal.ruta){
            MenuPrincipal(navController = navController)
        }
        composable(Rutas.CrearJugadores.ruta){

        }
        composable(Rutas.Detalles.ruta){

        }
    }

}