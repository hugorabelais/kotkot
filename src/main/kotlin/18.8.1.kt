import kotlin.properties.Delegates

class CapteurMoteur {

    var temperature: Double by Delegates.observable(0.0) { _, _, nouvelleValeur ->

        if (nouvelleValeur < -20 || nouvelleValeur > 150) {
            println("[ALERTE] Température invalide détectée : $nouvelleValeur°C. (Plage autorisée: -20 à 150)")
        } else {
            println("[CAPTEUR] Nouvelle température enregistrée : $nouvelleValeur°C")
        }
    }
}

fun main() {
    val capteur = CapteurMoteur()

    capteur.temperature = 50.0
    capteur.temperature = 200.0
}