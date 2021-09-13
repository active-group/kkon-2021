// Damit wir überhaupt von neutralem Element reden können, brauchen wir
// sowas wie +, *
// Typ/Menge: A
// binärer Operator: (A, A) -> A
// Assoziativgesetz:
// a + (b + c) = (a + b) + c
// Halbgruppe / Semigroup
interface Semigroup<A> {
    // muß assoziativ sein
    fun op(x: A, y: A): A
}

open class IntAddSemigroup : Semigroup<Int> {
    override fun op(x: Int, y: Int): Int = x + y
}

val intAddSemigroup = IntAddSemigroup()

fun <A> listSemigroup() = object : Semigroup<List<A>> {
    override fun op(x: List<A>, y: List<A>): List<A> = append(x, y)
}


// neutrales Element:
// 0 + x = x + 0 = x
// 1 * x = x * 1 = x

// Halbgruppe + neutrales Element:
// Monoid
interface Monoid<A> : Semigroup<A> {
    val neutral: A
}

class IntAddMonoid : IntAddSemigroup {
    override
}

// Kommutativgesetz:
// a + b = b + a