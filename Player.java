
// import java.util.ArrayList;

/**
 * includes methods around gameplay for the player
 *
 * @author samyuktha
 * @version Apr 30, 2019
 * @author Period: 4
 * @author Assignment: Scrabble
 *
 * @author Sources: none
 */
public class Player
{

    int points;

    PlayerLetters myLetters;


    /**
     * sets letter to zero and my letters to the playerletters
     */
    public Player()
    {
        points = 0;
        myLetters = new PlayerLetters();
    }


    /**
     * 
     * adds your new points to your current number of points
     * 
     * @param num
     *            the number of points
     */
    public void addPoints( int num )
    {
        points += num;
    }


    /**
     * 
     * returns the point number
     * 
     * @return points
     */
    public int getPoints()
    {
        return points;
    }


    /**
     * 
     * returns your letters
     * 
     * @return myLetters
     */
    public PlayerLetters getLetters()
    {
        return myLetters;
    }

}
