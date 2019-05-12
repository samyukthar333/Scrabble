import java.util.ArrayList;

/**
 *  TODO Write a one-sentence summary of your class here.
 *  TODO Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author  samyu
 *  @version Apr 30, 2019
 *  @author  Period: TODO
 *  @author  Assignment: Scrabble
 *
 *  @author  Sources: TODO
 */
public class Player
{

    int points;
    PlayerLetters myLetters;
    
    public Player()
    {
        points = 0;
        myLetters = new PlayerLetters();
    }
    
    public void addPoints(int num)
    {
        points+=num;
    }
    
    public int getPoints()
    {
        return points;
    }
    
    public PlayerLetters getLetters()
    {
        return myLetters;
    }
    
    public void executeTurn()
    {
        int input = 0;
        //get input, and store as value from 0 to 2 
        if(input==0)
        {
           play();
        }
        else if(input == 1)
        {
            exchange(exchangeInput(), new ArrayList<Letter>());
            
        }
        
    }
    
    private void play()
    {
        //get input 
    }
    
    private ArrayList<Letter> exchangeInput()
    {
        //ask for input of 3 letters from player one at a time
       return new ArrayList<Letter>();
        
    }
    
    public void exchange(ArrayList<Letter> myUseless, ArrayList<Letter> threeNew)
    {
        for(Letter letter : myUseless)
        {
            myLetters.remove( letter );
        }
        for(Letter letter : threeNew)
        {
            myLetters.add( letter );
        }
    }
}
