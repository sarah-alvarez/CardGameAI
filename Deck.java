public class Deck{
    private Card [] deck;

    public Deck(){
	deck = new Card[52];
	
	int index = 0;
	// Spades
	for (int i = 0; i < 13; i++){
	    if (i == 0){
		deck[index] = new Card("BLACK", "A", "S", -1);
	    }
	    else if (i == 10){
		deck[index] = new Card("BLACK", "J", "S", -1);
	    }
	    else if (i == 11){
		deck[index] = new Card("BLACK", "Q", "S", -1);
	    }
	    else if (i == 12){
		deck[index] = new Card("BLACK", "K", "S", -1);
	    }
	    else{
		deck[index] = new Card("BLACK", Integer.toString(i + 1), "S", -1);
	    }
	    index++;
	}
	// Clubs
	for (int i = 0; i < 13; i++){
            if (i == 0){
                deck[index] = new Card("BLACK", "A", "C", -1);
            }
            else if (i == 10){
                deck[index] = new Card("BLACK", "J", "C", -1);
            }
            else if (i == 11){
                deck[index] = new Card("BLACK", "Q", "C", -1);
            }
            else if (i == 12){
                deck[index] = new Card("BLACK", "K", "C", -1);
            }
            else{
                deck[index] = new Card("BLACK", Integer.toString(i + 1), "C", -1);
            }
            index++;
        }
	// Hearts
	for (int i = 0; i < 13; i++){
            if (i == 0){
                deck[index] = new Card("RED", "A", "H", -1);
            }
            else if (i == 10){
                deck[index] = new Card("RED", "J", "H", -1);
            }
            else if (i == 11){
                deck[index] = new Card("RED", "Q", "H", -1);
            }
            else if (i == 12){
                deck[index] = new Card("RED", "K", "H", -1);
            }
            else{
                deck[index] = new Card("RED", Integer.toString(i + 1), "H", -1);
            }
            index++;
        }
	// Diamonds
	for (int i = 0; i < 13; i++){
            if (i == 0){
                deck[index] = new Card("RED", "A", "D", -1);
            }
            else if (i == 10){
                deck[index] = new Card("RED", "J", "D", -1);
            }
            else if (i == 11){
                deck[index] = new Card("RED", "Q", "D", -1);
            }
            else if (i == 12){
                deck[index] = new Card("RED", "K", "D", -1);
            }
            else{
                deck[index] = new Card("RED", Integer.toString(i + 1), "D", -1);
            }
            index++;
        }
    }

    public void printDeck(){
	for (int i=0; i < 52; i++){
	    String card = deck[i].getCardVal() + deck[i].getCardSuite();
	    System.out.print(card);
	    System.out.print(" ");
	    if ((deck[i].getCardVal()).equals("K")){
		System.out.println(" ");
	    }
	}
	System.out.println(" ");
    }

    public Card getCardAt(int index){
	return deck[index];
    }
}