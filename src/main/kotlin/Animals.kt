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

data class Dillo(val liveness: Liveness, val weight: Int)