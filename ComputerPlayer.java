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
public class ComputerPlayer extends Player
{
    
    public ComputerPlayer()
    {
        super();
    }
    
    /**
     * exchanges 3 random letters with 3 letters from the bag
     * 
     */
    public ArrayList<Letter> getExchange()
    {
        ArrayList<Letter> letters = new ArrayList<Letter>();
        for(int i = 0; i<3; i++)
        {
            letters.add( myLetters.removeIndex( (int)(Math.random()*(myLetters.size() -1 )) ));
            
        }
        return letters;
    }
    
    
    
    public void findWord(Board board)
    {
        //AHH THIS IS THE MOST COMPLICATED METHOD
    }
    
}
