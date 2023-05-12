enum class Suit(val value: Int) {
    SPADES(1),
    CLUBS(2),
    DIAMONDS(3),
    HEARTS(4);

    override fun toString(): String {
        return when (value) {
            1 -> "♠"
            2 -> "♣"
            3 -> "♦"
            4 -> "♥"
            else -> "0"
        }
    }
}