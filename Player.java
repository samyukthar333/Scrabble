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
    
}
