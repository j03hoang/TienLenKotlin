fun main(args: Array<String>) {

//    val list = listOf(
//        Player("mah", inRound = false),
//        Player("bah", inRound = true)
//    )
//
//    val player = list.find { it.inRound }
//    print(list.indexOf(player))
//
//    print(list.indexOfFirst { it.inRound })

//    val list3: MutableList<SuperCard> = mutableListOf (
//        CardDiamond(Rank.THREE),
//        CardDiamond(Rank.FOUR)
//    )
//    println(list3.map { it.cardRank }.distinct().count())


//    println(list3.distinct().size != list3.size)
//    println(list3)

//    val input: String = readLine() ?: ""
//    println("$input${input == ""}")
//    val inputArray = input
//        .split("")
//        .filter { it.isNotBlank() }
//
//    val newArray = inputArray.map { Integer.parseInt(it, 16) }
//    println(newArray)
//    println(inputArray)
//    println(inputArray.size)


//
//    inputArray.forEach {
//        val index = Integer.parseInt(it, 16)
//        print(index)
//    }

//    val deck = CardDeck()
//    val player: Player = Player("Bob", deck.dealCards())
//    player.printHandWithIndices()
//    val inputArray = listOf(2,1,0)
//
//    var preBoardCards = listOf<SuperCard>()
//
//    val inputArray2 = listOf("0","a","A")
//    preBoardCards = inputArray.map { player.cardHand[it] }
//    println(preBoardCards)
//    preBoardCards = inputArray.sorted().map { player.cardHand[it] }
//    println(preBoardCards)

    val game = Game()
    game.play()

}


