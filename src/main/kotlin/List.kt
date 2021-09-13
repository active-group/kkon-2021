// Eine Liste ist eins der folgenden:
// - die leere Liste
// - eine Cons-Liste bestehend aus erstem Element und Rest-Liste
//                                                         ^^^^^
sealed interface List<out A> {
    // fun sum(): Int
    // fun product(): Int
}

object Empty : List<Nothing> {
    // "nichts"
    // "neutrales Element": x + 0 = 0 + x = x
    // bezüglich der Addition
    // override fun sum(): Int = 0
    //                      x * 1 = 1 * x = x
    // neutrales Element bezüglich der Multiplikation
    // override fun product(): Int = 1
}

data class Cons<out A>(val first: A, val rest: List<A>) : List<A> {
    // override fun sum(): Int =
    //    this.first + this.rest.sum()

    // override fun product(): Int =
    //    this.first * this.rest.product()
}

fun listSum(list: List<Int>): Int =
    when (list) {
        is Empty -> 0
        is Cons -> list.first + listSum(list.rest)
    }

// extension method
fun List<Int>.sum(): Int =
    when (this) {
        is Empty -> 0
        is Cons -> this.first + this.rest.sum()
    }

fun double(i: Int): Int = i * 2

fun listDouble(list: List<Int>): List<Int> =
    when (list) {
        is Empty -> Empty
        is Cons -> Cons(double(list.first), listDouble(list.rest))
    }

fun runOverAnimals(list: List<Animal>): List<Animal> =
    when (list) {
        is Empty -> Empty
        is Cons -> Cons(runOverAnimal(list.first), runOverAnimals(list.rest))
    }

// Abstraktion
// Kopieren, Umbenennen, Variablen für Unterschiede, ggf. Parameter
// double : (Int) -> Int
// runOverAnimal : (Animal) -> Animal
fun <A> listMap(f: (A) -> A, list: List<A>): List<A> =
    when (list) {
        is Empty -> Empty
        is Cons -> Cons(f(list.first), listMap(f, list.rest))
    }

fun <A> listMap2(list: List<A>, f: (A) -> A): List<A> =
    when (list) {
        is Empty -> Empty
        is Cons -> Cons(f(list.first), listMap2(list.rest, f))
    }

// 1elementig Liste: 17
val list1 = Cons(17, Empty)

// 3elementige Liste: 1, 2, 3
val list3 = Cons(1, Cons(2, Cons(3, Empty)))