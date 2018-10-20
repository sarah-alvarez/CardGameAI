import java.util.Random;
import java.util.ArrayList;

public class Board{
    private Card [][] board;
    private Random rand;

    public Board(){
	rand = new Random();
	board = new Card[4][13];
	Deck deck = new Deck();
	ArrayList<Card> temp = new ArrayList<Card>();

	for (int i=0; i < 52; i++){
	    temp.add(deck.getCardAt(i));
	}

	for (int i=0; i < 4; i++){
	    for (int j=0; j < 13; j++){
		int cardIndex = rand.nextInt(temp.size()) + 0;
		board[i][j] = temp.get(cardIndex);
		temp.remove(cardIndex);
	    }
	}
    }
    
    public String printBoard(){
	String boardStr = "";

	for (int i=0; i < 4; i++){
            for (int j=0; j < 13; j++){
		String temp = board[i][j].printCard();
		System.out.print(" ");
		boardStr = boardStr + temp + " ";
	    }
	    System.out.println(" ");
	    boardStr = boardStr + "\n";
	}
	System.out.println(" ");
	boardStr = boardStr + "\n";

	return boardStr;
    }

    public void flipCard(int x, int y){
	int currState = board[y][x].getCardState();
	if (currState == 1 || currState == -1){
	    board[y][x].setCardState(currState * -1);
	}
	else{
	    System.out.println("Cannot flip this card over!");
	}
    }

    public void removeCard(int x, int y){
	int currState = board[y][x].getCardState();
	if (currState != 0){
	    board[y][x].setCardState(0);
	}
	else{
	    System.out.println("Cannot remove this card!");
	}
    }

    public Card[][] getBoard(){
	return board;
    }
    
    public Card getCard(int y, int x){
	return board[x][y];
    }
}