//SHOULD THIS CLASS HAVE THE GUI??
/**
 *  TODO Write a one-sentence summary of your class here.
 *  TODO Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author  samyu
 *  @version Apr 30, 2019
 *  @author  Period: 4
 *  @author  Assignment: Scrabble
 *
 *  @author  Sources: none
 */
public class Game
{
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Words myWords;
    private LetterBag myBag;
    
    public Game()
    {
        myWords = new Words("Collins Scrabble Words (2015)");
        player1 = new Player();
        player2 = new Player();
        myBag = new LetterBag();
        chooseFirstPlayer();
    }
    
    private void chooseFirstPlayer()
    {
        char pl1;
        char pl2;
        do {
            pl1 = myBag.getRandomLetterWithoutRemoving().getLetter();
            pl2 = myBag.getRandomLetterWithoutRemoving().getLetter();
        } while(pl1==pl2);
        if(Character.getNumericValue( pl1 )<Character.getNumericValue( pl2 ))
            currentPlayer = player1;
        else
            currentPlayer = player2;
    }
    
    private void switchPlayers()
    {
        if(currentPlayer.equals( player1 ))
            currentPlayer= player2;
        else
            currentPlayer= player1;
    }
    
    public void play()
    {
        switchPlayers();
    }
    
//should we have a main class here?
}
