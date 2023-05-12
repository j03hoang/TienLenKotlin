object GameUtil {
    fun evaluateBoardState(playedCards: List<SuperCard>): BoardState {
        return if (single(playedCards)) {
            BoardState.SINGLE
        } else if (pair(playedCards)) {
            BoardState.PAIR
        } else if (triplet(playedCards)) {
            BoardState.TRIPLE
        } else if (quartet(playedCards)) {
            BoardState.QUAD
        } else if (straight(playedCards)) {
            BoardState.STRAIGHT
        }  else if (doubleSequence(playedCards)) {
            BoardState.DOUBLE_SEQUENCE
        } else
            BoardState.INVALID
    }

    fun checkIndicesValid(input: String): Boolean {
        val regex = Regex("[0-9A-Ca-c]+")
        if (!regex.matches(input)) {
            println("Error: Index regex mismatch.")
            return false
        }
        return true
    }

    fun checkRepeatInputs(input: List<Int>): Boolean {
        if (input.distinct().count() != input.size) {
            println("Error: copy of a card.")
            return false
        }
        return true
    }

    fun checkInputBounds(input: List<Int>, handSize: Int): Boolean {
        input.forEach {
            if (it > handSize) {
                println("Error: out of bounds of player hand")
                return false;
            }
        }
        return true
    }

    fun ifBomb(boardState: BoardState, preBoardCards: List<SuperCard>,
                       onBoardCards: List<SuperCard>): Boolean {
        if (onBoardCards[0].cardRank != Rank.DEUCE) {
            return false
        }

        if (boardState == BoardState.SINGLE) {
            return doubleSequence(preBoardCards) || quartet(preBoardCards)
        }
        if (boardState == BoardState.PAIR) {
            return doubleSequence(preBoardCards) && preBoardCards.size >= 4*2
        }
        if (boardState == BoardState.TRIPLE) {
            return doubleSequence(preBoardCards) && preBoardCards.size >= 5*2
        }
        return false
    }

    private fun single(playedCards: List<SuperCard>): Boolean  {
        return playedCards.size == 1
    }

    private fun pair(playedCards: List<SuperCard>): Boolean {
        return playedCards.size == 2
                && playedCards.map { it.cardRank }.distinct().count() == 1
    }

    private fun triplet(playedCards: List<SuperCard>): Boolean {
        return playedCards.size == 3
                && playedCards.map { it.cardRank }.distinct().count() == 1
    }

    private fun quartet(playedCards: List<SuperCard>): Boolean {
        return playedCards.size == 4
                && playedCards.map { it.cardRank }.distinct().count() == 1
    }

    private fun straight(playedCards: List<SuperCard>): Boolean {
        if (playedCards.size < 3 || playedCards[playedCards.size - 1].cardRank === Rank.DEUCE) {
            return false
        }
        for (i in 0 until playedCards.size - 1) {
            if (playedCards[i].cardRank.value != playedCards[i + 1].cardRank.value - 1) {
                return false
            }
        }
        return true
    }
    private fun doubleSequence(playedCards: List<SuperCard>): Boolean {
        if (playedCards.size % 2 == 1 || playedCards.size < 3*2) {
            return false
        }

        val trimmedCards: List<Rank> = playedCards.map { it.cardRank }.distinct()
        for (i in 0 until trimmedCards.size - 1) {
            if (trimmedCards[i].value != trimmedCards[i+1].value - 1) {
                return false;
            }
        }
        return true;
    }
}