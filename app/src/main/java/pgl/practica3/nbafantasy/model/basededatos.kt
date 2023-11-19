package pgl.practica3.nbafantasy.model

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.gson.Gson
import pgl.practica3.nbafantasy.R
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

object BaseDeDatos{

    val archivo = "jugadores.dat"

    fun writeData(jugadores: Array<Jugador>){
        ObjectOutputStream(FileOutputStream(archivo)).use {
            stream -> stream.writeObject(jugadores)
        }
    }

    fun readData(): Array<Jugador>{
        var listaJugadores: Array<Jugador>
        ObjectInputStream(FileInputStream(archivo)).use {
            stream -> listaJugadores = stream.readObject() as Array<Jugador>
        }
        return listaJugadores
    }
}