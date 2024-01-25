package pgl.practica3.nbafantasy.routes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pgl.practica3.nbafantasy.model.BaseDeDatos

@Composable
fun Navigator(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Rutas.MenuPrincipal.ruta){

        val listaJugadores = BaseDeDatos.jugadores

        composable(Rutas.MenuPrincipal.ruta){

            MenuPrincipal(navController = navController, listaJugadores)
        }
        composable(Rutas.CrearJugadores.ruta){

            CrearJugadores(navController = navController, jugadores = listaJugadores)

        }
        composable(Rutas.DetallesJugador.ruta + "/{jugador}"){
            val jugadorName = it.arguments?.getString("jugador")
            val jugador = listaJugadores.filter { player -> player.nombre == jugadorName }.first()
            DetallesJugador(navController = navController, jugador = jugador)
        }
    }

}