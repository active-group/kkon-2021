// Eine geometrische Figur ist eins der folgenden:
//      ^^^^^^^^^^^^^^^^^^
// (2D)
// - Kreis
// - Quadrat
// - eine Überlagerung zweier geometrischer Figuren
//                            ^^^^^^^^^^^^^^^^^^^^^
//                            Selbstbezug -> rekursiver Aufruf


// 1. Schreibe eine Repräsentation für geometrische Figuren
// 2. Schreibe eine Funktion/Methode, die für einen Punkt
//    aussagt, ob dieser innerhalb oder außerhalb der
//    geometrischen Figur ist

data class Point(val x: Double, val y: Double)

sealed interface Shape {
    fun contains(point: Point): Boolean
}
data class Overlay(val shape1: Shape, val shape2: Shape): Shape {
    override fun contains(point: Point): Boolean =
        this.shape1.contains(point) || // enthält shape1 den point?
        this.shape2.contains(point) // enthält shape2 den point?
}