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

fun List<Int>.sum(): Int =
    when (this) {
        is Empty -> 0
        is Cons -> list.first + list.rest.sum()
    }


// 1elementig Liste: 17
val list1 = Cons(17, Empty)

// 3elementige Liste: 1, 2, 3
val list3 = Cons(1, Cons(2, Cons(3, Empty)))