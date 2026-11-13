package org.exemple
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.MutableStateFlow

data class EtatDrone(val altitude : Int, val statut : String)

fun main() = runBlocking {
    val etat = MutableStateFlow<EtatDrone>(EtatDrone(0, "au sol"))

    val drone = launch {
        delay(1000)
        etat.value = EtatDrone(50, "en vol")
        delay(1000)
        etat.value = EtatDrone(0, "livré")
    }

    val moniteur = launch {
        etat.collect { etat ->
            println("Moniteur : Altitude=${etat.altitude} | Statut=${etat.statut} ")
        }
    }

    delay(5000) // Laisse le temps au système de s'exécuter
    moniteur.cancel()
    drone.cancel()
    println("mission terminé.")
}