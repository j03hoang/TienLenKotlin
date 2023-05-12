class CardDiamond(rankValue: Rank) : SuperCard() {
    init {
        cardRank = rankValue
        cardSuit = Suit.DIAMONDS
    }
}