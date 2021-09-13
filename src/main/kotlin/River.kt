// Ein Fluss kommt entweder aus:
// - einer Quelle
// - einem Hauptfluss und einem Nebenfluss
// -->
// Ein Fluss ist eins der folgenden:
//     ^^^^^
// - ein Bach aus einer Quelle
// - ein Zusammentreffen von einem Hauptfluss und einem Nebenfluss
//                                      ^^^^^                ^^^^^

sealed interface River {
    fun flowsFrom(location: String): Boolean
}

data class Creek(val origin: String): River {
    override fun flowsFrom(location: String): Boolean =
        this.origin == location
}
data class Confluence(val location: String,
                      val mainStem: River,
                      val tributary: River) : River {
    override fun flowsFrom(location: String): Boolean =
        this.location == location ||
        this.mainStem.flowsFrom(location) ||
        this.tributary.flowsFrom(location)
                      }

