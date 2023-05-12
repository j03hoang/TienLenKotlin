class CardSpade(rankValue: Rank) : SuperCard() {
    init {
        cardRank = rankValue
        cardSuit = Suit.SPADES
    }
}