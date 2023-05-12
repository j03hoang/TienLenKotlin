class CardDeck {
    private var cardArray: MutableList<SuperCard> = mutableListOf()
    init {
        getNewDeck()
    }

    private fun getNewDeck() {
        for (value in Rank.values()) {
            cardArray.add(CardSpade(value))
            cardArray.add(CardClub(value))
            cardArray.add(CardDiamond(value))
            cardArray.add(CardHeart(value))
        }
    }

    private fun getCard(): SuperCard {
        val card: SuperCard = cardArray.random()
        cardArray.remove(card)
        return card
    }

    // error handling
    fun dealCards(numCards: Int = 13): MutableList<SuperCard>  {
        val cardHand: MutableList<SuperCard> = mutableListOf()
        for (i in 0 until numCards) {
            cardHand.add(getCard())
        }
        return cardHand
    }

    fun printDeck() {
        for (card in cardArray) {
            println(card)
        }
    }
}