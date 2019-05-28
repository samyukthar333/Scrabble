import java.util.ArrayList;

/**
 * 
 *  TODO Write a one-sentence summary of your class here.
 *  TODO Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author  samyuktha, saanvi, richa
 *  @version May 22, 2019
 *  @author  Period: 4
 *  @author  Assignment: Scrabble
 *
 *  @author  Sources: none
 */
public class PlayerLetters
{
    ArrayList<Letter> playerletters;
    
    public PlayerLetters()
    {
        playerletters = new ArrayList<Letter>();
    }

    /**
     * 
     * adds or doesnt add letter(s) depending on your current number of letters
     * @param letter
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
     * @param letter
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
     * @param letter
     * @return false || true
     */
    public boolean remove( char c )
    {
        for(Letter letter: playerletters)
        {
            if(letter.getLetter()==c)
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
     * @param index the index of your letter
     * @return letter
     */
    public Letter removeIndex( int index )
    {
        Letter letter = null;
        if ( playerletters.size()>index )
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
     * @return playerletters
     */
    public ArrayList<Letter> getLetters( )
    {
        return playerletters;
    }
    
    public boolean contains(char c)
    {
        for(Letter letter: playerletters)
        {
            if(letter.getLetter()==c)
                return true;
        }
        return false;
    }
}
