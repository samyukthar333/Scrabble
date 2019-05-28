import java.util.ArrayList;


/**
 * sets up the game
 *
 * @author samyuktha, saanvi, richa
 * @version Apr 30, 2019
 * @author Period: 4
 * @author Assignment: Scrabble
 *
 * @author Sources: none
 */
public class Game
{
    private Player player1; //computer player

    private Player player2; //human player

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


    /**
     * 
     * returns the player who is playing
     * 
     * @return player2
     */
    public Player getComputer()
    {
        return player1;
    }
    
    public Player getHuman()
    {
        return player2;
    }
    
    public boolean isComputer(Player player)
    {
        return (player1.equals( player ));
    }

    /**
     * 
     * returns the person who is currently playing
     * 
     * @return currentPlayer
     */
    public Player getCurrentPlayer()
    {
        return currentPlayer;
    }


    /**
     * 
     * returns the board that the player is playing on
     * 
     * @return board
     */
    public Board getBoard()
    {
        return board;
    }


    /**
     * 
     * adds the letters into the bag
     * 
     * @param myLetters
     */
    public void putLetters( ArrayList<Letter> myLetters )
    {
        myBag.add( myLetters );

    }

    /**
     *
     * gets the LetterBag
     * 
     * @return LetterBag
     */
    public LetterBag getBag(){
        return myBag;
    }


    /**
     * 
     * returns 3 random letters from the letter bag
     * 
     * @return myBag.getRandomLetters(3);
     */
    public ArrayList<Letter> getRandomThree()
    {
        return myBag.getRandomLetters( 3 );

    }


    /**
     * 
     * chooses who goes first based on what letter they randomly pick from the
     * bag
     */
    private void chooseFirstPlayer()
    {
        char pl1, pl2;
        do
        {
            pl1 = myBag.getRandomLetterWithoutRemoving().getLetter();
            pl2 = myBag.getRandomLetterWithoutRemoving().getLetter();
        } while ( pl1 == pl2 );
        if ( Character.getNumericValue( pl1 ) < Character.getNumericValue( pl2 ) )
            currentPlayer = player1;
        else
            currentPlayer = player2;
        replenishLetters();
    }


    /**
     * 
     * switches the players
     */
    private void switchPlayers()
    {
        if ( currentPlayer.equals( player1 ) )
            currentPlayer = player2;
        else
            currentPlayer = player1;
    }


    /**
     * 
     * adds or removes squares
     * 
     * @param squares
     *            represents the tiles that are on the board and racks
     * @return true
     */
    public boolean play( ArrayList<Square> squares )
    {
        for ( Square s : squares )
        {
            if ( !currentPlayer.getLetters().getLetters().contains( s.getLetter() ) )
                return false;
            currentPlayer.getLetters().remove( s.getLetter() );
        }
        int points = board.placeWord( squares );
        if ( points == -1 )
            return false;
        currentPlayer.addPoints( points );
        
        replenishLetters();
        
        switchPlayers();
        return true;
    }
    
    /**
     * 
     * refills letters until size of playerLetters is 7
     * 
     */
    public void replenishLetters()
    {
        while(currentPlayer.getLetters().size()<7)
        {
            currentPlayer.getLetters().add( myBag.getRandomLetter());
        }
    }


    /**
     * 
     * exchanges given letters for random letters
     * 
     * @param letters
     *            the letter tiles that are on the board and racks
     */
    public void exchange( ArrayList<Letter> letters )
    {
        for ( Letter i : letters )
        {
            currentPlayer.getLetters().remove( i );
        }
        myBag.add( letters );
        replenishLetters();
        switchPlayers();
    }

    /**
     * 
     * switches the players
     */
    public void pass()
    {
        switchPlayers();
    }

}
