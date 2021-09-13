// Tiere auf dem texanischen Highway
// Da gibt es Gürteltiere.
// Wollen erfassen: tot oder lebendig? Gewicht?
// Information -> Daten
// DeinProgramm: Konstruktionsanleitungen
// Datendefinition:
// Ein Gürteltier hat folgende Eigenschaften:
//                ^^^^^^^^^^^^^^^^^^^^^^^^^^^
//                (alternative Formulierung: besteht aus)
//                zusammengesetzte Daten (hier nicht: Komposition)
// - tot oder lebendig?     - UND -
// - Gewicht

// Lebendigkeit: tot ODER lebendig
// "eins der folgenden"
// Fallunterscheidung
// Spezialfall: Aufzählung
enum class Liveness { DEAD, ALIVE }

// Zustand des Gürteltiers zu einem bestimmten Zeitpunkt
data class Dillo(val liveness: Liveness, val weight: Int) {
    // OO: void
    fun runOver(): Dillo =
        // Dillo(Liveness.DEAD, this.weight)
        this.copy(liveness = Liveness.DEAD)
}

fun runOverDillo(dillo: Dillo): Dillo =
    // Dillo(Liveness.DEAD, dillo.weight)
    dillo.copy(liveness = Liveness.DEAD)



// Gürteltier, lebendig, 10kg
val dillo1 = Dillo(Liveness.ALIVE, 10)
// totes Gürteltier, 11kg
val dillo2 = Dillo(Liveness.DEAD, 11)

// Ein Papagei hat folgende Eigenschaften:
// - Satz
// - Gewicht
