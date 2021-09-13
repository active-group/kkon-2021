// Eine Liste ist eins der folgenden:
// - die leere Liste
// - eine Cons-Liste bestehend aus erstem Element und Rest-Liste
//                                                         ^^^^^
sealed interface List {
    fun sum(): Int
}

object Empty : List {
    // "nichts"
    // "neutrales Element"
    override fun sum(): Int = 0
}

data class Cons(val first: Int, val rest: List) : List {
    override fun sum(): Int =
        this.first + this.rest.sum()
}

// 1elementig Liste: 17
val list1 = Cons(17, Empty)

// 3elementige Liste: 1, 2, 3
val list3 = Cons(1, Cons(2, Cons(3, Empty)))