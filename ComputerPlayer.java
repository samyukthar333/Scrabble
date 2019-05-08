import java.util.ArrayList;

//DO THIS CLASS AFTER IMPLEMENTING A GAME WITH TWO HUMAN PLAYERS
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
    
    public void exchange()
    {
        ArrayList<Letter> myLetters = new ArrayList<Letter>();
        super.exchange(myLetters);
    }
}
