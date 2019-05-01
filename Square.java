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
    Letter letter;
    //special square or not -- 0 for nothing, 1 for double letter score,
    //2 for triple letter score, 3 for double word score, and 4 for triple letter score
    int special;
    
    public Square(Letter letter, int special)
    {
        this.letter = letter;
        this.special = special;
    }
    
    public Square()
    {
        letter = null;
         
        special = 0;
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
    
    
}
