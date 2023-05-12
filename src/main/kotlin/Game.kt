class Game {
    var deck = CardDeck()
    var boardState = BoardState.SINGLE

    var onBoardCards: List<SuperCard> = listOf()
    var preBoardCards: List<SuperCard> = listOf()
    lateinit var inputArray: List<Int>

    var currentPlayerIdx = -1
    var numGames = 0
    var numRounds = 0

    var winnerIdx = -1

    var playerList: List<Player> = generatePlayers()

    private fun generatePlayers(): List<Player> {
        return listOf(
            Player("Doge"),
            Player("Cat"),
            Player("Norman"),
            Player("4")
        )
    }

    private fun dealToPlayers() {
        playerList.forEach {
            it.createHand(deck.dealCards())
            sortCards(it.cardHand)
        }
    }

    fun play() {
        dealToPlayers()
        if (numGames == 0) {
            playLowestThree()
        }
        while (!ifGameLoser()) {
            playRounds()
        }
        numGames++
        playAgain()
    }

    private fun playAgain() {
        println("Enter 1 to play another game.")
        val input = readLine() ?: ""
        if (input.contentEquals("1")) {
            boardState = BoardState.SINGLE
            currentPlayerIdx = winnerIdx
            winnerIdx = -1
            numRounds = 1
            play()
        } else {
            println("Thanks for play.")
        }
    }

    private fun playRounds() {
        if (numRounds > 0) {
            freeTurn(playerList[currentPlayerIdx])
            currentPlayerIdx++
        }
        while (!ifRoundWinner()) {
            playTurn(playerList[currentPlayerIdx % playerList.size])
            currentPlayerIdx++
        }
        putBackInPlay()
//        ifGameWinner()
        numRounds++
    }

    private fun putBackInPlay() {
        playerList.forEach {
            if (it.inGame) {
                it.inRound = true
            }
        }
    }

    // TODO: FIX recursion, duplicate if inRound conditions
    private fun playTurn(player: Player) {
        if (!player.inRound) {
            return
        }
        println("$player 's Turn.")
        player.printHandWithIndices()
        getInput(player)

        if (!player.inRound) {
            return
        }

        if (checkValidPlay()) {
            placeCards(player)
        } else {
            playTurn(player)
        }
    }

    private fun checkValidPlay(): Boolean {
        return checkBomb() || (checkBoardState() && checkGreaterThan())
    }

    private fun checkGreaterThan(): Boolean {
        if (preBoardCards.size != onBoardCards.size) {
            return false
        }
        return preBoardCards.last().compare(onBoardCards.last())
    }

    private fun checkBomb(): Boolean {
        if (GameUtil.ifBomb(boardState, preBoardCards, onBoardCards)) {
            setBoardState()
            return true
        }
        return false
    }

    private fun checkBoardState(): Boolean {
        if (GameUtil.evaluateBoardState(preBoardCards) != boardState) {
            println("Accepted play is: $boardState")
            return false
        }
        return true
    }

    // TODO: FIX recursion
    private fun getInput(player: Player) {
        println("Enter index at of which card to play. 'SKIP' to exit round.")
        val input = readLine() ?: ""

        if (input.contentEquals("SKIP")) {
            exitRound(player)
        } else if (checkInput(player, input)) {
            setPreBoardCards(player)
        } else {
            getInput(player)
        }
    }

    private fun checkInput(player: Player, input: String): Boolean {
        return if (GameUtil.checkIndicesValid(input)) {
            setInputArray(input)
            (GameUtil.checkRepeatInputs(inputArray)
                    && GameUtil.checkInputBounds(inputArray, player.cardHand.size))
        } else {
            false
        }
    }

    private fun setInputArray(input: String) {
        inputArray = input
            .split("")
            .filter { it.isNotBlank() }
            .map { Integer.parseInt(it, 16) }
    }

    private fun setPreBoardCards(player: Player) {
        preBoardCards = inputArray.sorted().map { player.cardHand[it] }
    }

    private fun sortCards(cards: MutableList<SuperCard> ) {
        cards.sortBy { it.cardSuit }
        cards.sortBy { it.cardRank }
    }

    private fun exitRound(player: Player) {
        player.inRound = false
        println("$player is no longer in play for this round.")
    }

    private fun ifRoundWinner(): Boolean {
        (playerList.count { it.inRound } == 1).let { onePlayerLeft ->
            if (onePlayerLeft) {
                currentPlayerIdx = playerList.indexOfFirst { it.inRound }
                return true
            }
        }
        return false
    }

    private fun ifGameLoser(): Boolean {
        (playerList.count { it.inGame } == 1).let { onePlayerLeft ->
            if (onePlayerLeft) {
                println("Player ${playerList.first { it.inGame }} has lost ")
                return true
            }
        }
        return false
    }

    // TODO: fix
    private fun ifGameWinner() {
        if (winnerIdx != -1) {
            return
        }
        playerList.forEach {
            if (it.cardHand.size == 0) {
                winnerIdx = playerList.indexOf(it)
            }
        }
        playerList.count { it.cardHand.size == 0}
    }

    // TODO: fix recursion, what if player skips
    private fun freeTurn(player: Player) {
        println("$player's Free Turn.")
        player.printHandWithIndices()
        getInput(player)
        setBoardState()
        if (boardState != BoardState.INVALID) {
            placeCards(player)
        } else {
            freeTurn(player)
            return
        }
    }

    private fun freeTurnv2(player: Player) {
        do {
            println("$player's Free Turn.")
            player.printHandWithIndices()
            getInput(player)
            setBoardState()
        } while (boardState == BoardState.INVALID)
        placeCards(player)
    }

    private fun setBoardState() {
        boardState = GameUtil.evaluateBoardState(preBoardCards)
    }

    private fun playLowestThree() {
        playerList.first { it.cardHand.first().equals(CardSpade(Rank.THREE)) }.let {
            currentPlayerIdx = playerList.indexOf(it)
            preBoardCards = listOf(it.cardHand.first())
            println("$it has the lowest carD: $preBoardCards")
            placeCards(it)
        }
    }

    private fun placeCards(player: Player) {
        player.removeCards(preBoardCards)
        onBoardCards = preBoardCards
        println("Cards on board: $onBoardCards")
    }
}