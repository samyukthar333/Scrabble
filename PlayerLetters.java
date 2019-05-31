import java.util.ArrayList;


/**
 * 
 * keeps track of the player's letters
 *
 * @author samyuktha, saanvi, richa
 * @version May 22, 2019
 * @author Period: 4
 * @author Assignment: Scrabble
 *
 * @author Sources: none
 */
public class PlayerLetters
{
    ArrayList<Letter> playerletters;

    /**
     * constructor
     */
    public PlayerLetters()
    {
        playerletters = new ArrayList<Letter>();
    }


    /**
     * 
     * adds or doesnt add letter(s) depending on your current number of letters
     * 
     * @param letter
     *            the letter on the tile
     * @return false || true
     */
    public boolean add( Letter letter )
    {
        if ( playerletters.size() >= 7 )
        {
            return false;
        }
        else
        {
            playerletters.add( letter );
            return true;
        }
    }


    /**
     * 
     * removes a letter if your letters already has that letter
     * 
     * @param letter
     *            the letter on the tile
     * @return false || true
     */
    public boolean remove( Letter letter )
    {
        if ( playerletters.contains( letter ) )
        {
            playerletters.remove( letter );
            return true;
        }
        else
        {
            return false;
        }
    }


    /**
     * 
     * removes a letter if your letters already has that letter
     * 
     * @param c the letter on the tile
     * @return true is successfully removed, false otherwise
     */
    public boolean remove( char c )
    {
        for ( Letter letter : playerletters )
        {
            if ( letter.getLetter() == c )
            {
                playerletters.remove( letter );
                return true;
            }
        }
        return false;
    }


    /**
     * 
     * removes the letter at that index
     * 
     * @param index
     *            the index of your letter
     * @return letter
     */
    public Letter removeIndex( int index )
    {
        Letter letter = null;
        if ( playerletters.size() > index )
        {
            letter = playerletters.get( index );
            playerletters.remove( letter );

        }
        return letter;
    }


    /**
     * returns size of playerLetters
     *
     * @return size of playerLetters
     */
    public int size()
    {
        return playerletters.size();
    }


    /**
     * 
     * returns playerletters
     * 
     * @return playerletters
     */
    public ArrayList<Letter> getLetters()
    {
        return playerletters;
    }


    /**
     * 
     * checks if the letter is in the player's letters
     * 
     * @param c
     *            stands for character
     * @return true if playerletters contains char, false otherwise
     */
    public boolean contains( char c )
    {
        for ( Letter letter : playerletters )
        {
            if ( letter.getLetter() == c )
                return true;
        }
        return false;
    }
}
