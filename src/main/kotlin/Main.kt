import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    // MutableStateFlow
    // Il contient toujours UNE valeur actuelle (ici 20.0).
    // Toute modification de cette valeur est automatiquement propagée à tous les abonnés.
    val fluxBaterie = MutableStateFlow<Int>(100)
    // OU avec inférence de type : val fluxTemperature = MutableStateFlow(20.0)


    // Coroutine "Capteur" : Le Producteur
    val jobCapteur = launch {
        val valeurs = listOf(100, 80, 50, 20, 5)
        for (baterie in valeurs) {

            delay(1000)

            // On met à jour l'état. Le StateFlow prévient automatiquement
            // tous ceux qui font un "collect" dessus.
            fluxBaterie.value = baterie
            println(" Console : État reçu -> -> $baterie")
        }
    }

    // Coroutine "Afficheur" : Le Consommateur
    val jobAfficheur = launch {
        // .collect est une fonction suspendue qui se met en pause
        // tant qu'il n'y a pas de nouvelle valeur dans le flux.
        // Dès que fluxTemperature.value change, le code à l'intérieur s'exécute.
        fluxBaterie.collect { baterie ->
            println("--- Sonde [Ares-1] : Nouveau niveau -> $baterie%")
        }
    }

    // On laisse tourner pendant 3 secondes
    delay(10000)
    if (fluxBaterie.value<20){
        println("!!! ALERTE CRITIQUE : Énergie faible sur Ares-1 !!!")
    }
    // On ferme les coroutines
    jobCapteur.cancel()
    jobAfficheur.cancel()
    println("--- Système arrêté ---")
}