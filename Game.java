public class Game{
    private int playerTurn;
    private String [] availableCards;
    private int cardsLeft;
    private Player player1;
    private Player player2;
    private Board gameBoard;
    private int gameRound;
    private int p1TotalPoints;
    private int p2TotalPoints;
    private int winningPlayer;
    private int p1Streak;
    private int p2Streak;
    private String gameStr;

    public Game(){
	gameBoard = new Board();
	cardsLeft = 52;
	player1 = new Player();
	player2 = new Player("INTERMEDIATE");
	playerTurn = -1;
	availableCards = getAvailable(gameBoard.getBoard());
	gameStr = "";
    }

    public String [] getAvailable(Card[][] board){
	cardsLeft = 52;
	for (int i=0; i < 4; i++){
	    for (int j=0; j < 13; j++){
		if (board[i][j].getCardState() == 1 || board[i][j].getCardState() == 0){
		    cardsLeft--;
		}
	    }
	}

	String [] temp = new String[cardsLeft];
	if (cardsLeft == 0){
	    return temp;
	}
	int index = 0;
	for (int i=0; i < 4; i++){
            for (int j=0; j < 13; j++){
                if (board[i][j].getCardState() == -1){
                    String coords = String.valueOf(i) + String.valueOf(j);
		    temp[index] = coords;
		    index++;
                }
            }
        }

	return temp;
    }

    public String getGameStr(){
	return gameStr;
    }

    public int getP1Streak(){
	return p1Streak;
    }
    
    public int getP2Streak(){
	return p2Streak;
    }

    public int getGameRound(){
	return gameRound;
    }
    
    public int getP1Points(){
	return p1TotalPoints;
    }
    
    public int getP2Points(){
	return p2TotalPoints;
    }
    
    public int getWinningPlayer(){
	return winningPlayer;
    }

    public int getCardsLeft(){
	return cardsLeft;
    }

    public void printGameBoard(){
	gameBoard.printBoard();
    }

    public void playGame(){
	while (cardsLeft > 0){
	    if (playerTurn == -1){
		gameStr = gameStr + "///////////////////////" + "\n" + "   PLAYER ONE'S TURN   " + 
		          "\n" + "///////////////////////" + "\n";
		System.out.println("///////////////////////");
		System.out.println("   PLAYER ONE'S TURN   ");
		System.out.println("///////////////////////");
		boolean found = true;
		int p1CurrStreak = 0;
		while (found && cardsLeft > 0){
		    gameStr = gameStr + "PLAYER ONE CHOOSE A CARD" + "\n";
		    System.out.println("PLAYER ONE CHOOSE A CARD");
		    gameStr = gameStr + "There are " + cardsLeft + " cards left." + "\n";
		    System.out.println("There are " + cardsLeft + " cards left.");
		    String player1Choice = player1.chooseCard(availableCards, cardsLeft);
		    int y = Integer.parseInt(player1Choice.substring(0,1));
		    int x = Integer.parseInt(player1Choice.substring(1));
		    String choice1 = gameBoard.getCard(x,y).getCardVal() + gameBoard.getCard(x,y).getCardSuite();
		    gameBoard.flipCard(x, y);

		    gameStr = gameStr + gameBoard.printBoard();
		    
		    availableCards = getAvailable(gameBoard.getBoard());
		    
		    gameStr = gameStr + "PLAYER ONE CHOOSE ANOTHER CARD" + "\n";
		    System.out.println("PLAYER ONE CHOOSE ANOTHER CARD");
		    gameStr = gameStr + "There are " + cardsLeft + " cards left." + "\n";
		    System.out.println("There are " + cardsLeft + " cards left.");
		    player1Choice = player1.chooseCard(availableCards, cardsLeft);
		    int b = Integer.parseInt(player1Choice.substring(0,1));
		    int a = Integer.parseInt(player1Choice.substring(1));
		    String choice2 = gameBoard.getCard(a,b).getCardVal() + gameBoard.getCard(a,b).getCardSuite();
		    gameBoard.flipCard(a, b);
		    
		    gameStr = gameStr + gameBoard.printBoard();
		    
		    if ((gameBoard.getCard(x,y).getCardColor()).equals(gameBoard.getCard(a,b).getCardColor())){
			if ((gameBoard.getCard(x,y).getCardVal()).equals(gameBoard.getCard(a,b).getCardVal())){
			    System.out.println("PLAYER ONE MATCHED " + choice1 + " and " + choice2);
			    gameStr = gameStr + "PLAYER ONE MATCHED " + choice1 + " and " + choice2 + "\n";
			    gameBoard.removeCard(x, y);
			    gameBoard.removeCard(a, b);
			    player1.addPoint();
			    p1CurrStreak++;
			}
			else{
			    gameBoard.flipCard(x, y);
			    gameBoard.flipCard(a, b);
			    System.out.println("PLAYER ONE FAILED TO MATCH " + choice1 + " and " + choice2);
			    gameStr = gameStr + "PLAYER ONE FAILED TO MATCH " + choice1 + " and " + choice2 + "\n";
			    found = false;
			    
			    if (p1CurrStreak > player1.getPlayerStreak()){
				player1.setPlayerStreak(p1CurrStreak);
			    }
			}
		    }
		    else{
			gameBoard.flipCard(x, y);
			gameBoard.flipCard(a, b);
			System.out.println("PLAYER ONE FAILED TO MATCH " + choice1 + " and " + choice2);
			gameStr = gameStr + "PLAYER ONE FAILED TO MATCH " + choice1 + " and " + choice2 + "\n";
			found = false;
			
			if (p1CurrStreak > player1.getPlayerStreak()){
			    player1.setPlayerStreak(p1CurrStreak);
			}
		    }
		    
		    availableCards = getAvailable(gameBoard.getBoard());
		    if (found == false){
			playerTurn = playerTurn * -1;
		    }
		    gameRound++;
		}
	    }
	    else{
		gameStr = gameStr + "///////////////////////" + "\n" + "   PLAYER TWO'S TURN   " +
		    "\n" + "///////////////////////" + "\n";
		System.out.println("///////////////////////");
                System.out.println("   PLAYER TWO'S TURN   ");
                System.out.println("///////////////////////");
		boolean found = true;
		int p2CurrStreak = 0;
		while (found == true && cardsLeft > 0){
		    String player2Choice = player2.chooseCard(availableCards, cardsLeft);
		    String [] choices = player2Choice.split(" ");
		    
		    System.out.println("Player 2 PICK A CARD.");
		    gameStr = gameStr + "Player 2 PICK A CARD." + "\n";
		    System.out.println("There are " + cardsLeft + " cards left.");
		    gameStr = gameStr + "There are " + cardsLeft + " cards left." + "\n";
		    int y = Integer.parseInt(choices[0].substring(0,1));
		    int x = Integer.parseInt(choices[0].substring(1));
		    String choice1 = gameBoard.getCard(x,y).getCardVal() + gameBoard.getCard(x,y).getCardSuite();
		    gameBoard.flipCard(x, y);
		    gameStr = gameStr + gameBoard.printBoard();
		    
		    System.out.println("Player 2 PICK ANOTHER CARD.");
		    gameStr = gameStr + "Player 2 PICK ANOTHER CARD." + "\n";
		    System.out.println("There are " + cardsLeft + " cards left.");
		    gameStr = gameStr + "There are " + cardsLeft + " cards left." + "\n";
		    int b = Integer.parseInt(choices[1].substring(0,1));
		    int a = Integer.parseInt(choices[1].substring(1));
		    String choice2 = gameBoard.getCard(a,b).getCardVal() + gameBoard.getCard(a,b).getCardSuite();
		    gameBoard.flipCard(a, b);
		    gameBoard.printBoard();
		    gameStr = gameStr + gameBoard.printBoard();
		    
		    if ((gameBoard.getCard(x,y).getCardColor()).equals(gameBoard.getCard(a,b).getCardColor())){
			if ((gameBoard.getCard(x,y).getCardVal()).equals(gameBoard.getCard(a,b).getCardVal())){
			    System.out.println("PLAYER TWO MATCHED " + choice1 + " and " + choice2);
			    gameStr = gameStr + "PLAYER TWO MATCHED " + choice1 + " and " + choice2 + "\n";
			    gameBoard.removeCard(x, y);
			    gameBoard.removeCard(a, b);
			    player2.addPoint();
			    p2CurrStreak++;
			}
			else{
			    gameBoard.flipCard(x, y);
			    gameBoard.flipCard(a, b);
			    System.out.println("PLAYER TWO FAILED TO MATCH " + choice1 + " and " + choice2);
			    gameStr = gameStr + "PLAYER TWO FAILED TO MATCH " + choice1 + " and " + choice2 + "\n";
			    found = false;
			    
			    if (p2CurrStreak > player2.getPlayerStreak()){
                                player2.setPlayerStreak(p2CurrStreak);
                            }
			}
		    }
		    else{
			gameBoard.flipCard(x, y);
			gameBoard.flipCard(a, b);
			System.out.println("PLAYER TWO FAILED TO MATCH " + choice1 + " and " + choice2);
			gameStr = gameStr + "PLAYER TWO FAILED TO MATCH " + choice1 + " and " + choice2 + "\n";
			found = false;
			
			if (p2CurrStreak > player2.getPlayerStreak()){
			    player2.setPlayerStreak(p2CurrStreak);
			}
		    }
		    
		    availableCards = getAvailable(gameBoard.getBoard());
		    if (found == false){
			playerTurn = playerTurn * -1;
		    }
		    gameRound++;
	    	}
	    }
	}
	System.out.println(" ");
	gameStr = gameStr + "\n";
	System.out.println(".:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.");
	gameStr = gameStr + ".:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:." + "\n";
	System.out.println("THE GAME HAS ENDED.");
	gameStr = gameStr + "THE GAME HAS ENDED." + "\n";
	System.out.println("PLAYER 1 POINTS = " + player1.getPoints());
	gameStr = gameStr + "PLAYER 1 POINTS = " + player1.getPoints() + "\n";
	System.out.println("PLAYER 2 POINTS = " + player2.getPoints());
	gameStr = gameStr + "PLAYER 2 POINTS = " + player2.getPoints() + "\n";
	p1TotalPoints = player1.getPoints();
	p2TotalPoints = player2.getPoints();
	p1Streak = player1.getPlayerStreak();
	p2Streak = player2.getPlayerStreak();

	if (player1.getPoints() > player2.getPoints()){
	    System.out.println("PLAYER 1 WON THE GAME!!!");
	    gameStr = gameStr + "PLAYER 1 WON THE GAME!!!" + "\n";
	    System.out.println("PLAYER 1 POINTS = " + p1TotalPoints);
	    gameStr = gameStr + "PLAYER 1 POINTS = " + p1TotalPoints + "\n";
	    winningPlayer = 1;
	}
	else if (player2.getPoints() > player1.getPoints()){
	    System.out.println("PLAYER 2 WON THE GAME!!!");
	    gameStr = gameStr + "PLAYER 2 WON THE GAME!!!" + "\n";
	    System.out.println("PLAYER 2 POINTS = " + p2TotalPoints);
	    gameStr = gameStr + "PLAYER 2 POINTS = " + p2TotalPoints + "\n";
	    winningPlayer = 2;
	}
	else if (player2.getPoints() == player1.getPoints()){
	    System.out.println("THE GAME WAS A TIE!!!");
	    gameStr = gameStr + "THE GAME WAS A TIE!!!" + "\n";
	    winningPlayer = 0;
	}
	
    }

}