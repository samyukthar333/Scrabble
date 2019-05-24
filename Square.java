
/**
 * 
 * 
 * Represents a square/tile on the Scrabble board.
 *
 * @author samyuktha, saanvi, richa
 * @version Apr 30, 2019
 * @author Period: 4
 * @author Assignment: Scrabble
 *
 * @author Sources: none
 * 
 */
public class Square
{
    private Letter letter;

    // special square or not -- 0 for nothing, 1 for double letter score,
    // 2 for triple letter score, 3 for double word score, and 4 for triple
    // letter score
    private int special;

    private int myX;

    private int myY;


    /**
     * 
     * @param special
     * @param x
     * @param y
     */
    public Square( int special, int x, int y )
    {
        if ( x < 15 || x >= 0 )
            myX = x;
        else
            myX = -1;
        if ( y < 15 || y >= 0 )
            myY = y;
        else
            myY = -1;
        letter = null;
        this.special = special;
    }


    /**
     * 
     * @param x
     * @param y
     */
    public Square( int x, int y )
    {
        if ( x < 15 || x >= 0 )
            myX = x;
        else
            myX = -1;
        if ( y < 15 || y >= 0 )
            myY = y;
        else
            myY = -1;
        letter = null;
        special = 0;
    }


    /**
     * 
     * @param letter
     * @param x
     * @param y
     */
    public Square( Letter letter, int x, int y )
    {
        if ( x < 15 || x >= 0 )
            myX = x;
        else
            myX = -1;
        if ( y < 15 || y >= 0 )
            myY = y;
        else
            myY = -1;
        this.letter = letter;
        special = 0;
    }


    /**
     * 
     * TODO Write your method description here.
     * 
     * @return myX
     */
    public int getRow()
    {
        return myX;
    }


    /**
     * 
     * TODO Write your method description here.
     * 
     * @return myY
     */
    public int getCol()
    {
        return myY;
    }


    /**
     * 
     * TODO Write your method description here.
     * 
     * @param letter
     *            the letters in the word
     * @return sp
     */
    public int setLetter( Letter letter )
    {
        this.letter = letter;
        int sp = special;
        return sp;
    }


    /**
     * 
     * TODO Write your method description here.
     * 
     * @param num
     */
    public void setSpecial( int num )
    {
        special = num;
    }


    /**
     * 
     * TODO Write your method description here.
     * 
     * @return letter
     */
    public Letter getLetter()
    {
        return letter;
    }


    /**
     * 
     * TODO Write your method description here.
     * 
     * @param row
     * @return false || false && true
     */
    public boolean setRow( int row )
    {
        if ( row < 0 || row > 14 )
            return false;
        myX = row;
        return true;

    }


    /**
     * 
     * TODO Write your method description here.
     * 
     * @param col
     * @return false || false && true
     */
    public boolean setCol( int col )
    {
        if ( col < 0 || col > 14 )
            return false;
        myY = col;
        return true;

    }


    /**
     * 
     * TODO Write your method description here.
     * 
     * @return special
     */
    public int getSpecial()
    {
        return special;
    }


    /**
     * 
     * TODO Write your method description here.
     * 
     * @return (letter == null)
     */
    public boolean isEmpty()
    {
        return ( letter == null );
    }


    /**
     * 
     * TODO Write your method description here.
     * 
     * @return special != 0
     */
    public boolean isSpecial()
    {
        return special != 0;
    }


    /**
     * 
     * TODO Write your method description here.
     */
    public void removeSpecial()
    {
        special = 0;
    }


    /**
     * 
     * TODO Write your method description here.
     * 
     * @return letter.getPointValue() || letter.getPointValue() * 2 ||
     *         letter.getPointValue() * 3 || -10 || -20
     */
    public int getPoints()
    {
        if ( special == 0 ) // none
            return letter.getPointValue();
        else if ( special == 1 ) // double letter
            return letter.getPointValue() * 2;
        else if ( special == 2 ) // triple letter
            return letter.getPointValue() * 3;
        else if ( special == 3 ) // double word
            return -10;
        else // triple word
            return -20;

    }


    /**
     * toString method
     */
    public String toString()
    {
        if ( letter == null )
        {
            return " ";
        }
        return "" + letter.getLetter();
    }

}
