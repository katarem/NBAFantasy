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

    val jugadoresDetails = listOf(
        JugadorDetails(jugadores[0],R.drawable.lebron_details, "LeBron Raymone James Sr. (Akron, Ohio; 30 de diciembre de 1984) es un jugador de baloncesto estadounidense que pertenece a la plantilla de Los Angeles Lakers de la NBA. Con 2,06 metros de estatura, su posición es la de alero, pero su talento, versatilidad y poderío físico le permiten jugar tanto de base como de ala-pívot. James es el máximo anotador de la historia de la NBA y es considerado como uno de los mejores jugadores de baloncesto del mundo y de la historia."),
        JugadorDetails(jugadores[1],R.drawable.stephen_details,"Wardell Stephen Curry II (Akron, Ohio; 14 de marzo de 1988), es un jugador de baloncesto estadounidense que pertenece a la plantilla de los Golden State Warriors de la NBA. Con 1,88 metros de altura, juega en la posición de base. Hijo del exjugador de la NBA Dell Curry y hermano mayor del también jugador Seth Curry, jugó a nivel universitario tres años en Davidson y fue elegido por los Warriors en la séptima posición del Draft de la NBA de 2009. A lo largo de su carrera ha sido cuatro veces campeón de la NBA, dos veces MVP de la temporada regular y una vez MVP de las Finales, además de dos Copas del Mundo con la selección estadounidense. Está considerado como el mejor tirador de la historia del baloncesto y es el líder histórico de la NBA en triples anotados."),
        JugadorDetails(jugadores[2],R.drawable.kevin_details,"Kevin Wayne Durant (Washington D. C.; 29 de septiembre de 1988) es un jugador de baloncesto estadounidense que pertenece a la plantilla de los Phoenix Suns de la NBA. Con 2,11 metros de altura, juega en la posición de alero. Fue campeón de la NBA y MVP de las finales en 2017 y 2018; campeón del mundo en 2010 y medalla de oro olímpica con la selección de Estados Unidos en 2012, 2016 y 2021. También fue MVP de la temporada en 2014, y está considerado como uno de los mejores jugadores del mundo."),
        JugadorDetails(jugadores[3],R.drawable.giannis_details,"Giannis Ougko Antetokounmpo (en griego: Γιάννης Αντετοκούνμπο, pronunciación en griego: /ˈʝanis adetoˈkumbo/; Atenas, 6 de diciembre de 1994) es un baloncestista greco-nigeriano que pertenece a la plantilla de los Milwaukee Bucks de la NBA. Con 2,11 metros de altura, juega en la posición de ala-pívot, pero debido a su gran versatilidad es capaz de jugar tanto de alero como de pívot. Sus hermanos Thanasis (n. 1992), Kostas (n. 1997) y Alex (n. 2001) también son jugadores profesionales de baloncesto. Desde que llegó a la NBA ha sido siete veces All-Star, dos veces MVP de la temporada y una vez MVP de las Finales."),
        JugadorDetails(jugadores[4],R.drawable.jimmy_details,"Jimmy Butler III (Houston, Texas; 14 de septiembre de 1989) es un jugador de baloncesto estadounidense que pertenece a la plantilla de los Miami Heat de la NBA. Con 2,01 metros de estatura, puede jugar tanto en la posición de escolta como en la de alero."),
        JugadorDetails(jugadores[5],R.drawable.joel_details,"Joel Hans Embiid (Yaundé; 16 de marzo de 1994) es un jugador de baloncesto camerunés, nacionalizado francés y estadounidense, que pertenece a la plantilla de los Philadelphia 76ers. Con 2,13 metros de estatura, juega en la posición de pívot."),
        JugadorDetails(jugadores[6],R.drawable.kawhi_details,"Kawhi Anthony Leonard (Riverside, California; 29 de junio de 1991) es un jugador de baloncesto estadounidense que pertenece a la plantilla de Los Angeles Clippers de la NBA. Con 2,01 metros de estatura, juega en la posición de alero. El 15 de junio de 2014, se proclamó campeón de la NBA con los San Antonio Spurs, siendo nombrado MVP de las Finales tras su gran defensa sobre LeBron James. En 2016 participó por primera vez en su carrera como titular en el All-Star Game de la NBA. Logro que repetiría en 2017, 2019, 2020 y 2021. En 2015 y 2016 fue nombrado Mejor defensor de la NBA y fue elegido para el Mejor Quinteto Defensivo de la NBA hasta en 3 ocasiones consecutivas durante 2015, 2016 y 2017. El 13 de junio de 2019, con Toronto Raptors, se proclamó campeón de la NBA por segunda vez en su carrera consiguiendo, también, su segundo MVP de las Finales."),
        JugadorDetails(jugadores[7],R.drawable.devin_details,"Devin Armani Booker (Grand Rapids, Míchigan, 30 de octubre de 1996) es un jugador de baloncesto estadounidense que pertenece a la plantilla de los Phoenix Suns de la NBA. Con 1,98 metros de estatura, juega en la posición de escolta. Booker jugó una temporada de baloncesto universitario con los Wildcats de la Universidad de Kentucky. Fue seleccionado en la decimotercera posición del Draft de la NBA de 2015 por los Phoenix Suns"),
        JugadorDetails(jugadores[8],R.drawable.donovan_details,"Donovan Vernell Mitchell Jr. (Elmsford, Nueva York, 7 de septiembre de 1996) es un baloncestista estadounidense que pertenece a la plantilla de los Cleveland Cavaliers de la NBA. Con 1,91 metros de estatura, juega en la posición de escolta."),
        JugadorDetails(jugadores[9],R.drawable.luka_details,"Luka Dončić (Liubliana, 28 de febrero de 1999) es un jugador de baloncesto esloveno, que pertenece a la plantilla de los Dallas Mavericks de la NBA. Con 2,01 metros de altura juega en la posición de base pero también puede jugar como escolta. En abril de 2015 se convirtió en el jugador más joven del Real Madrid Baloncesto en debutar en la Liga ACB (a la edad de 16 años y 2 meses), y el tercero más joven en la historia de la competición. Sus actuaciones a tan temprana edad le situaron rápidamente como uno de los jugadores emergentes más importantes de su época y de Europa, postulándose como uno de los primeros elegidos del draft NBA 2018 según los portales especializados."),
    )

}

