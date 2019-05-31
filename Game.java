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
    private ComputerPlayer player1; //computer player

    private Player player2; //human player

    private Player currentPlayer;
    
    private boolean twoPlayer;

    private LetterBag myBag;

    private Board board;

    /**
     * constructor with param twoPlayer
     *  makes game with board, bag, currentplayer - player2, and player1
     * @param twoPlayer whether game is two player or not
     */
    public Game(boolean twoPlayer)
    {
        this.twoPlayer = twoPlayer;
        board = new Board();
        player1 = new ComputerPlayer();
        player2 = new Player();
        myBag = new LetterBag();
        currentPlayer = player2;
        replenishLetters(player1);
        replenishLetters(player2);
//        chooseFirstPlayer();
    }
    
    /**
     * constructor 
     * makes game with board, bag, currentplayer - player2, and player1
     */
    public Game()
    {
        this.twoPlayer = false;
        board = new Board();
        player1 = new ComputerPlayer();
        player2 = new Player();
        myBag = new LetterBag();
        currentPlayer = player2;
        replenishLetters(player1);
        replenishLetters(player2);
//        chooseFirstPlayer();
    }


    /**
     * 
     * gets computer player if one person game
     * 
     * @return null if two person, else human player
     */
    public ComputerPlayer getComputer()
    {
        if(!twoPlayer)
            return player1;
        return null;
    }
    
    /**
     * 
     * gets Human player if one person game
     * @return null if two person, else human player
     */
    public Player getHuman()
    {
        if(!twoPlayer)
            return player2;
        return null;
    }
    
    /**
     * 
     * returns player1
     * @return player1 the first player
     */
    public Player getPlayer1()
    {
        return player1;
    }
    
    /**
     * 
     * returns player2
     * @return player2 second player
     */
    public Player getPlayer2()
    {
        return player2;
    }
    
    /**
     * 
     * returns a boolean whether player is or isnt computer
     * @param player keeps track of the player
     * @return  whether player is or isnt computer
     */
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
     * @param myLetters the players letters
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
     * checks if there are two players
     * @return twoPlayer
     */
    public boolean isTwoPlayer()
    {
        return twoPlayer;
    }


//    /**
//     * 
//     * chooses who goes first based on what letter they randomly pick from the
//     * bag
//     */
//    private void chooseFirstPlayer()
//    {
//        char pl1, pl2;
//        do
//        {
//            pl1 = myBag.getRandomLetterWithoutRemoving().getLetter();
//            pl2 = myBag.getRandomLetterWithoutRemoving().getLetter();
//        } while ( pl1 == pl2 );
//        if ( Character.getNumericValue( pl1 ) < Character.getNumericValue( pl2 ) )
//            currentPlayer = player1;
//        else
//            currentPlayer = player2;
//        replenishLetters();
//    }


    /**
     * 
     * switches the players
     */
    public void switchPlayers()
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
    public int play( ArrayList<Square> squares )
    {
        
        for ( Square s : squares )
        {
            System.out.println("ROW: " + s.getRow() + " COL: " + s.getCol() + " LETTER: " + s.getLetter());
            if ( !currentPlayer.getLetters().getLetters().contains( s.getLetter() ) )
                return -1;
            currentPlayer.getLetters().remove( s.getLetter() );
        }
        int points = board.placeWord( squares );
        if ( points == -1 )
            return -1;
        currentPlayer.addPoints( points );
        
        replenishLetters();
        
        switchPlayers();
        return points;
    }
    
    /**
     * 
     * refills letters until size of playerLetters is 7
     * 
     */
    public void replenishLetters()
    {
        while(!myBag.isEmpty() && currentPlayer.getLetters().size()<7)
        {
            currentPlayer.getLetters().add( myBag.getRandomLetter());
        }
    }
    
    
    /**
     * 
     * refills letters until size of playerLetters is 7
     * 
     */
    public void replenishLetters(Player player)
    {
        while(!myBag.isEmpty() && player.getLetters().size()<7)
        {
            player.getLetters().add( myBag.getRandomLetter());
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
