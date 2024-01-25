package pgl.practica3.nbafantasy.model

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.gson.Gson
import pgl.practica3.nbafantasy.R
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

object BaseDeDatos{

   val jugadores: ArrayList<Jugador> = arrayListOf(
       Jugador("Lebron James",Equipos.LosAngelesLakers, listOf("SF"),32,Equipos.BrooklynNets,"12/03/2023"),
       Jugador("Stephen Curry",Equipos.GoldenStateWarriors, listOf("PG"),38,Equipos.LosAngelesLakers,"18/04/2023"),
       Jugador("Kevin Durant",Equipos.BrooklynNets, listOf("SF"),34,Equipos.UtahJazz,"25/05/2023"),
       Jugador("Giannis Antetokounmpo",Equipos.MilwaukeeBucks, listOf("PF"),27,Equipos.LosAngelesClippers,"24/01/2024"),
       Jugador("Jimmy Butler",Equipos.MiamiHeat, listOf("SF"),26,Equipos.Philadelphia,"03/06/2023"),
       Jugador("Joel Embiid",Equipos.Philadelphia, listOf("C"),30,Equipos.MiamiHeat,"15/07/2023"),
       Jugador("Kawhi Leonard",Equipos.LosAngelesClippers, listOf("SF"),25,Equipos.PhoenixSuns,"22/08/2023"),
       Jugador("Devin Booker",Equipos.PhoenixSuns, listOf("SG"),28,Equipos.DallasMavericks,"04/09/2023"),
       Jugador("Donovan Mitchell",Equipos.UtahJazz, listOf("SG"),24,Equipos.MilwaukeeBucks,"12/10/2023"),
       Jugador("Luka Dončić",Equipos.DallasMavericks, listOf("PG","SG"),29,Equipos.GoldenStateWarriors,"02/11/2023"),
       Jugador("Lebron James",Equipos.LosAngelesLakers, listOf("SF"),28,Equipos.BrooklynNets,"28/01/2024"),
       Jugador("Kevin Durant",Equipos.BrooklynNets, listOf("SF"),31,Equipos.LosAngelesLakers,"28/01/2024"),
       Jugador("Stephen Curry",Equipos.GoldenStateWarriors, listOf("PG"),35,Equipos.Philadelphia,"05/02/2024"),
       Jugador("Joel Embiid",Equipos.Philadelphia, listOf("C"),30,Equipos.GoldenStateWarriors,"05/02/2024")
   )
    object Equipos{
        val LosAngelesLakers = "Los Angeles Lakers"
        val GoldenStateWarriors = "Golden State Warriors"
        val BrooklynNets = "Brooklyn Nets"
        val MilwaukeeBucks = "Milwaukee Bucks"
        val MiamiHeat = "Miami Heat"
        val Philadelphia = "Philadelphia 76ers"
        val LosAngelesClippers = "Los Angeles Clippers"
        val PhoenixSuns = "Phoenix Suns"
        val UtahJazz = "Utah Jazz"
        val DallasMavericks = "Dallas Mavericks"
    }
}

