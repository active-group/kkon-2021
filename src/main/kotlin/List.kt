// Eine Liste ist eins der folgenden:
// - die leere Liste
// - eine Cons-Liste bestehend aus erstem Element und Rest-Liste
//                                                         ^^^^^
sealed interface List

object Empty : List

data class Cons(val first: Int, val rest: List) : List