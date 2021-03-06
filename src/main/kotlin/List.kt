// Eine Liste ist eins der folgenden:
// - die leere Liste
// - eine Cons-Liste bestehend aus erstem Element und Rest-Liste
//                                                         ^^^^^
sealed interface List<out A> {
    // fun sum(): Int
    // fun product(): Int
    // Funktor:
    fun <B>     map(f: (A) ->      B) = listMap(f, this)
    // fun <B >flatMap(f: (A) -> List<B>): List<B>
    // fun <A> ofList(a: A): List<A>
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

fun <A> contains(list: List<A>, a: A): Boolean =
    when (list) {
        is Empty -> false
        is Cons ->
            if (list.first == a)
                true
            else
                contains(list.rest, a)
    }

sealed interface Option<out A> {
    // Funktor:
    fun <B> map(f: (A) -> B) = optionMap(f, this)
    // fun <B> flatMap(f: (A) -> Option<B>): Option<B>
}

object None : Option<Nothing>

data class Some<out A>(val value: A): Option<A>

fun <A, B> optionMap(f: (A) -> B, option: Option<A>): Option<B> =
    when (option) {
        is None -> None
        is Some -> Some(f(option.value))
    }

fun <A> listIndex(list: List<A>, a: A): Option<Int> =
    when (list) {
        is Empty -> None
        is Cons ->
            if (list.first == a)
                Some(0)
            else
                listIndex(list.rest, a).map {
                    i -> i + 1
                }
    }

fun listSum(list: List<Int>): Int =
    when (list) {
        is Empty -> 0
        is Cons -> list.first + listSum(list.rest)
    }

tailrec fun listSum1(list: List<Int>, acc: Int): Int =
    when (list) {
        is Empty -> acc
        // tail call: Aufruf ohne Kontext
        // endrekursiver Aufruf
        // wird kein Speicher auf dem Stack benötigt
        // die JVM verbraucht aber trotzdem welchen, AUSSER BEI tailrec
        is Cons -> listSum1(list.rest, acc + list.first)
    }

fun listSumWhile(list0: List<Int>): Int {
    var acc: Int = 0
    var list = list0
    while (list is Cons) {
        acc = acc + list.first
        list = list.rest
    }
    return acc
}

// listSum von Cons:
// - erst listSum(list.rest)
// - dann list.first + <Ergebnis> :: Kontext
// Kontext braucht zur Laufzeit eine Datenstruktur und Speicher
// oft: Stack
// JVM: Stack ist klein und fest in der Größe

fun listProduct(list: List<Int>): Int =
    when (list) {
        is Empty -> 1
        is Cons -> list.first * listProduct(list.rest)
    }

// + : (Int, Int) -> Int
// auch: foldRight, entsprechend: foldLeft
fun <A, B> fold(n: B, f: (A, B) -> B, list: List<A>): B =
    when (list) {
        is Empty -> n
        is Cons -> f(list.first, fold(n, f, list.rest))
    }

fun <A> append(list1: List<A>, list2: List<A>): List<A> =
    when (list1) {
        is Empty -> list2  // Sharing zwischen list2 und dem Ergebnis
        is Cons -> Cons(list1.first, // 1
                        append(list1.rest, list2)) // 2 3 4 5 6
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
// vgl:
// fun <A, B> optionMap(f: (A) -> B, option: Option<A>): Option<B>
fun <A, B> listMap(f: (A) -> B, list: List<A>): List<B> =
    when (list) {
        is Empty -> Empty
        is Cons -> Cons(f(list.first), listMap(f, list.rest))
    }

fun <A> listMap2(list: List<A>, f: (A) -> A): List<A> =
    when (list) {
        is Empty -> Empty
        is Cons -> Cons(f(list.first), listMap2(list.rest, f))
    }

fun <A> listFilter(list: List<A>, f: (A) -> Boolean): List<A> =
    when (list) {
        is Empty -> Empty
        is Cons ->
            if (f(list.first))
                Cons(list.first, listFilter(list.rest, f))
            else
                listFilter(list.rest, f) // die gefilterten Elemente aus dem Rest
    }

// 1elementig Liste: 17
val list1 = Cons(17, Empty)

// 3elementige Liste: 1, 2, 3
val list3 = Cons(1, Cons(2, Cons(3, Empty)))

val list6 = append(list3, Cons(4, Cons(5, Cons(6, Empty))))

