// Eine Liste ist eins der folgenden:
// - die leere Liste
// - eine Cons-Liste bestehend aus erstem Element und Rest-Liste
//                                                         ^^^^^
sealed interface List {
    fun sum(): Int
}

object Empty : List {
    override fun sum(): Int = 0
}

data class Cons(val first: Int, val rest: List) : List {
    override fun sum(): Int =
        this.first + this.rest.sum()
}

// 3elementige Liste: 1, 2, 3
val list1 = Cons(1, Cons(2, Cons(3, Empty)))