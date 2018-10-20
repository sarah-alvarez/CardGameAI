import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Driver{
    public static void main(String[] args) throws IOException{
	ArrayList<String> gameData = new ArrayList<String>();
	ArrayList<String> gameDisplay = new ArrayList<String>();
	Random rand = new Random();
	int numGames = rand.nextInt(40) + 0;
	
	for (int i=0; i < numGames; i++){
	    Game game = new Game();
	    game.playGame();
	    
	    String displayGame = game.getGameStr();
	    gameDisplay.add(displayGame);
	    // Collect game data.
	    String gameNumber = String.valueOf(i + 1);
	    String roundsPlayed = String.valueOf(game.getGameRound());
	    String player1Points = String.valueOf(game.getP1Points());
	    String player2Points = String.valueOf(game.getP2Points());
	    String winningPlayer = String.valueOf(game.getWinningPlayer());
	    String player1Streak = String.valueOf(game.getP1Streak());
	    String player2Streak = String.valueOf(game.getP2Streak());

	    String currentGameData = gameNumber + "," + roundsPlayed + "," + player1Points + "," +
		                     player2Points + "," + winningPlayer + "," + player1Streak +
		                     "," + player2Streak;

	    gameData.add(currentGameData);
	}
	System.out.println("");
	System.out.println("");
	System.out.println("All " + numGames + " games have been played.");
	
	FileWriter gameDataFile = new FileWriter("gameOutput.txt");
	String newLine = System.getProperty("line.separator");

	gameDataFile.write("Data from all games" + "\n");
	gameDataFile.write("Read per game: game number, # of rounds played, player 1's points, player 2's points, winning player (0 = tie), player 1's longest streak, player 2's longest streak." + "\n");

	for(int i=0; i < gameData.size(); i++){
            gameDataFile.write("-" + gameData.get(i) + newLine);
        }

	gameDataFile.write("End data from all games" + "\n");

	gameDataFile.write("\n" + "Documentation of all games below." + "\n");

	for (int i=0; i < gameDisplay.size(); i++){
	    gameDataFile.write(gameDisplay.get(i));
	}
	
	gameDataFile.write("\n" + "\n" + "All " + numGames + " games have been played." + "\n");

	gameDataFile.close();
	
    }
}