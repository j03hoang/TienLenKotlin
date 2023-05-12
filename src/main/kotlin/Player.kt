class Player(
    private val name: String,
    var cardHand: MutableList<SuperCard> = mutableListOf(),
    var inRound: Boolean = true,
    var inGame: Boolean = true,
) {
    fun createHand(dealtHand: MutableList<SuperCard>) {
        cardHand = dealtHand
    }

    fun removeCards(playedCards: List<SuperCard>) {
        for (card in playedCards) {
            cardHand.remove(card)
        }
    }

    fun printHandWithIndices() {
        for (i in cardHand.indices) {
            System.out.printf("%-5s", Integer.toHexString(i))
        }
        println()
        for (card in cardHand) {
            print(card)
        }
        println()
    }

    override fun toString(): String {
        return name
    }
}