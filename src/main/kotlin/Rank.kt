enum class Rank(val value: Int) {
    THREE(1),
    FOUR(2),
    FIVE(3),
    SIX(4),
    SEVEN(5),
    EIGHT(6),
    NINE(7),
    TEN(8),
    JACK(9),
    QUEEN(10),
    KING(11),
    ACE(12),
    DEUCE(13);

    //TODO use actual card values then change to 13?
    override fun toString(): String {
        return when (value) {
            1 -> "3"
            2 -> "4"
            3 -> "5"
            4 -> "6"
            5 -> "7"
            6 -> "8"
            7 -> "9"
            8 -> "10"
            9 -> "J"
            10 -> "Q"
            11 -> "K"
            12 -> "A"
            13 -> "2"
            else -> "0"
        }
    }
}