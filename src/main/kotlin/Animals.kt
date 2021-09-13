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

sealed interface Animal {
    fun runOver(): Animal

    fun runOver2(): Animal =
        when (this) {
            is Dillo -> runOverDillo(this)
            is Parrot -> this.copy(sentence = "")
        }
}

// Zustand des Gürteltiers zu einem bestimmten Zeitpunkt
data class Dillo(val liveness: Liveness, val weight: Int): Animal {
    // OO: void
    override fun runOver(): Dillo =
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
data class Parrot(val sentence: String, val weight: Int): Animal {
    override fun runOver(): Parrot = this.copy(sentence = "")
}

// Begrüßungspapagei, 1kg
val parrot1 = Parrot("Hallo", 1)

// Ein Tier ist eins der folgenden
// - Gürteltier - ODER -
// - Papagei
// Fallunterscheidung aus Klassen / zusammengesetzte Daten:
// gemischte Daten

fun runOverAnimal(animal: Animal): Animal =
    when (animal) {
        is Dillo -> runOverDillo(animal)
        is Parrot -> animal.copy(sentence = "")
    }