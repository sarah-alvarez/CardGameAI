import java.util.Random;
import java.util.ArrayList;

public class Player{
    private String playerType;
    private ArrayList<String> rememberedCards;
    private int playerPoints;
    private Random rand;
    private int playerTurns;
    private int playerStreak;

    public Player(){
	playerStreak = 0;
	playerTurns = 0;
	playerType = "BASIC";
	playerPoints = 0;
	rand = new Random();
    }

    public Player(String pType){
	playerStreak = 0;
	playerTurns = 0;
	playerType = pType;
	playerPoints = 0;
	rand = new Random();

	if (playerType.equals("INTERMEDIATE")){
	    rememberedCards = new ArrayList<String>();
	}
    } 

    public int getPlayerStreak(){
	return playerStreak;
    }

    public void setPlayerStreak(int newStreak){
	
	playerStreak = newStreak;
    }

    public String chooseCard(String [] available, int numAvailable){
	if (playerType.equals("BASIC")){
	    int cardIndex = rand.nextInt(numAvailable) + 0;
	    return available[cardIndex];
	}
	else{
	    // Check if player's memory is empty.
	    String finalChosen = "";
	    boolean isEmpty = rememberedCards.isEmpty();	    

	    // If player's memory is empty, pick a random card.
	    if (isEmpty == true){
		int cardIndex = rand.nextInt(numAvailable) + 0;
		finalChosen = available[cardIndex];
		rememberedCards.add(available[cardIndex]);
	    }
	    // If player's memory is not empty, Check if card is available.
	    else{
		// Choose a card from memory.
		// Check if the card is available.
		String chosen = rememberedCards.remove(0);
		
		boolean isAvailable = false;
		for (int i=0; i < numAvailable; i++){
		    if (chosen.equals(available[i])){
			isAvailable = true;
		    }
		}

		// If the card is not available, get another card from memory.
		if (isAvailable == false){
		    while (isAvailable ==  false && rememberedCards.size() > 0){
			chosen = rememberedCards.remove(0);
			for (int i=0; i < numAvailable; i++){
			    if (chosen.equals(available[i])){
				isAvailable = true;
			    }
			}
		    }
		    // If isAvailable is false and rememberedCards is empty, choose random card
		    if (isAvailable == false && rememberedCards.size() <= 0){
			int cardIndex = rand.nextInt(numAvailable) + 0;
			finalChosen = available[cardIndex];
			rememberedCards.add(available[cardIndex]);
		    }
		    // If the card is available, pick it.
		    else{
			finalChosen = chosen;
		    }
		}
		// If the card is available, pick it.
		else{
		    finalChosen = chosen;
		}
	    }

	    // Pick another card randomly.
	    int cardIndex = rand.nextInt(numAvailable) + 0;
	    String secondChosen = available[cardIndex];
	    while (secondChosen.equals(finalChosen)){
		cardIndex = rand.nextInt(numAvailable) + 0;
		secondChosen = available[cardIndex];
		rememberedCards.add(available[cardIndex]);
	    }

	    finalChosen = finalChosen + " " + secondChosen;
	    return finalChosen;
	}
    }

    public void addPoint(){
	playerPoints++;
    }

    public int getPoints(){
	return playerPoints;
    }
    
}