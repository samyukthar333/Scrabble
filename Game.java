import java.util.ArrayList;

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
    private LetterBag myBag;
    private Board board;
    
    public Game()
    {
        board = new Board();
        player1 = new ComputerPlayer();
        player2 = new Player();
        myBag = new LetterBag();
        chooseFirstPlayer();
    }
    
    public Player getHuman()
    {
        return player2;
    }
    
    public Player getCurrentPlayer()
    {
        return currentPlayer;
    }
    
    public Board getBoard()
    {
        return board;
    }

    public void putLetters(ArrayList<Letter> myLetters)
    {
        myBag.add( myLetters );
        
    }
    
    public ArrayList<Letter> getRandomThree()
    {
        return myBag.getRandomLetters(3);
        
    }
    
    private void chooseFirstPlayer()
    {
        char pl1, pl2;
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
    
    public boolean play(ArrayList<Square> squares)
    {
        for(Square s: squares)
        {
            if(!currentPlayer.getLetters().getLetters().contains( s.getLetter() ))
                return false;
            currentPlayer.getLetters().remove( s.getLetter() );
        }
        int points = board.placeWord( squares );
        if(points==-1)
            return false;
        currentPlayer.addPoints( points );
        switchPlayers();
        return true;
    }
    
    public void exchange(ArrayList<Letter> letters)
    {
        for(Letter i:letters)
        {
            currentPlayer.getLetters().remove( i );
        }
        myBag.add( letters );
        switchPlayers();
    }
    
    public void pass()
    {
         switchPlayers();
    }
    
}
