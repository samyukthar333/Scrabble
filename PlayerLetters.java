import java.util.ArrayList;


public class PlayerLetters
{
    ArrayList<Letter> playerletters = new ArrayList<Letter>();


    public boolean add( Letter letter )
    {
        if ( playerletters.size() >= 6 )
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


    public ArrayList<Letter> returnplayerletters( ArrayList<Letter> playerletters )
    {
        return playerletters;
    }
}
