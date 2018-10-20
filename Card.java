public class Card{
    private String cardColor;
    private String cardVal;
    private String cardSuite;
    private int cardState;
    
    public Card(String color, String faceVal, String suite, int state){
	cardColor = color;
	cardVal = faceVal;
	cardSuite = suite;
	cardState = state;
    }
    
    public String getCardColor(){
	return cardColor;
    }

    public void setCardColor(String newColor){
	cardColor = newColor;
    }

    public String getCardVal(){
        return cardVal;
    }

    public void setCardVal(String newVal){
        cardVal = newVal;
    }

    public String getCardSuite(){
	return cardSuite;
    }
    
    public void setCardSuite(String newSuite){
	cardSuite = newSuite;
    }

    public int getCardState(){
	return cardState;
    }

    public void setCardState(int newState){
	cardState = newState;
    }

    public String printCard(){
	String cardStr = "";
	if (cardState == -1){
	    cardStr = "OO";
	    System.out.print("OO");
	}
	else if (cardState == 1){
	    String card = cardVal + cardSuite;
	    cardStr = card;
	    System.out.print(card);
	}
	else if (cardState == 0){
	    cardStr = "XX";
	    System.out.print("XX");
	}
	
	return cardStr;
    }
}