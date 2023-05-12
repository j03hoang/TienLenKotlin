abstract class SuperCard {
    lateinit var cardRank: Rank
    lateinit var cardSuit: Suit

    fun compare(other: SuperCard): Boolean {
        return if (this.cardRank.value > other.cardRank.value) {
            true
        } else if (this.cardRank == other.cardRank
            && this.cardSuit.value > other.cardSuit.value) {
            true
        } else {
            println("$this is not greater than $other")
            false
        }
    }

    fun equals(other: SuperCard): Boolean {
        return cardRank == other.cardRank && cardSuit == other.cardSuit
    }

    override fun toString(): String {
        return String.format("%-4s", cardRank.toString() + "" + cardSuit)
    }
}