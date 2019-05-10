import java.util.ArrayList;


public class PlayerLetters
{
    ArrayList<Letter> playerletters = new ArrayList<Letter>();


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
    
    public ArrayList<Letter> getLetters( )
    {
        return playerletters;
    }
}
