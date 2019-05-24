
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
     * returns the row value / x coordinate of letter
     * 
     * @return myX
     */
    public int getRow()
    {
        return myX;
    }


    /**
     * 
     * returns the column value / y coordinate of letter
     * 
     * @return myY
     */
    public int getCol()
    {
        return myY;
    }


    /**
     * 
     * sets the score multiplier to the local score multiplier
     * sets the value of the local letter to letter
     * 
     * @param letter
     *            the letters in the word
     * @return sp
     */
    public int setLetter( Letter letter )
    {
        this.letter = letter;
        int sp = special;
        removeSpecial();
        return sp;
    }


    /**
     * 
     * sets the score multiplier to a specified number
     * 
     * @param num
     */
    public void setSpecial( int num )
    {
        special = num;
    }


    /**
     * 
     * returns the letter
     * 
     * @return letter
     */
    public Letter getLetter()
    {
        return letter;
    }


    /**
     * 
     * if the row number is out of bounds, return false
     * if it is in bounds, set the x coordinate to row number, return true
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
     * if the column number is out of bounds, return false
     * if it is in bounds, set the x coordinate to column number, return true
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
     * returns the score multiplier
     * 
     * @return special
     */
    public int getSpecial()
    {
        return special;
    }


    /**
     * 
     * checks if letter is null, true if it is, false if it isnt
     * 
     * @return (letter == null)
     */
    public boolean isEmpty()
    {
        return ( letter == null );
    }


    /**
     * 
     * checks if there is a score multiplier, true if it is, false if it isnt
     * 
     * @return special != 0
     */
    public boolean isSpecial()
    {
        return special != 0;
    }


    /**
     * 
     * removing the score multiplier (sets it to 0)
     */
    public void removeSpecial()
    {
        special = 0;
    }


    /**
     * 
     * calculates point value + score multiplier based on letter
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
