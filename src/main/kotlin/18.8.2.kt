class ReglagesAppareil {
    var volume: Int by SeuilMaxDelegate(100)
    var luminosite: Int by SeuilMaxDelegate(50)
}

fun main() {
    val reglages = ReglagesAppareil()
    reglages.volume = 120    // Plafonné à 100


    reglages.luminosite = 60   // Plafonné à 50
    println("Volume : ${reglages.volume}, Luminosité : ${reglages.luminosite}")
}