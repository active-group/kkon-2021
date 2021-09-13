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

object intAddSemigroup : Semigroup<Int> {
    override fun op(x: Int, y: Int): Int = x + y
}

// neutrales Element:
// 0 + x = x + 0 = x
// 1 * x = x * 1 = x

// Halbgruppe + neutrales Element:
// Monoid

// Kommutativgesetz:
// a + b = b + a