// Eine Liste ist eins der folgenden:
// - die leere Liste
// - eine Cons-Liste bestehend aus erstem Element und Rest-Liste
//                                                         ^^^^^
sealed interface List {
    fun sum(): Int
    fun product(): Int
}

object Empty : List {
    // "nichts"
    // "neutrales Element": x + 0 = 0 + x = x
    // bezüglich der Addition
    override fun sum(): Int = 0
    //                      x * 1 = 1 * x = x
    // neutrales Element bezüglich der Multiplikation
    override fun product(): Int = 1
}

data class Cons(val first: Int, val rest: List) : List {
    override fun sum(): Int =
        this.first + this.rest.sum()

    override fun product(): Int =
        this.first * this.rest.product()
}

// 1elementig Liste: 17
val list1 = Cons(17, Empty)

// 3elementige Liste: 1, 2, 3
val list3 = Cons(1, Cons(2, Cons(3, Empty)))