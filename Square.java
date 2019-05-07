/**
 * 
 * 
 *  TODO Write a one-sentence summary of your class here.
 *  TODO Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author  sramanan007
 *  @version Apr 30, 2019
 *  @author  Period: 4
 *  @author  Assignment: Scrabble
 *
 *  @author  Sources: none
 *  
 */
public class Square
{   
    private Letter letter;
    //special square or not -- 0 for nothing, 1 for double letter score,
    //2 for triple letter score, 3 for double word score, and 4 for triple letter score
    private int special;
    private int myX;
    private int myY;
    
    public Square(int special, int x, int y)
    {
        if(x<15||x>=0)
            myX = x;
        else
            myX = -1;
        if(y<15||y>=0)
            myY = y;
        else
            myY = -1;
        letter = null;
        this.special = special;
    }
    
    public Square(int x, int y)
    {
        if(x<15||x>=0)
            myX = x;
        else
            myX = -1;
        if(y<15||y>=0)
            myY = y;
        else
            myY = -1;
        letter = null;
        special = 0;
    }
    
    public int getRow()
    {
        return myX;
    }
    
    public int getCol()
    {
        return myY;
    }
    
    public void setLetter(Letter letter)
    {
        this.letter = letter;
    }
    
    public void setSpecial(int num)
    {
        special = num;
    }
    
    public Letter getLetter()
    {
        return letter;
    }
    
    public int getSpecial()
    {
        return special;
    }
    
    public boolean isEmpty()
    {
        return (letter==null);
    }
    
    public boolean isSpecial()
    {
        return special != 0;
    }
    
    public void removeSpecial()
    {
        special = 0;
    }
    
}
