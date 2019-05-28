/**
 * 
 * sets a letter to a letter value
 *
 * @author samyuktha, saanvi, richa
 * @version May 22, 2019
 * @author Period: 4
 * @author Assignment: Scrabble
 *
 * @author Sources: none
 */
public class Letter
{
    private char letter;

    private int pointValue;


    /**
     * assigns point values to each letter
     * 
     * @param c
     */
    public Letter( char c )
    {

        letter = Character.toUpperCase( c );
        if ( letter == 'A' )
            pointValue = 1;
        else if ( letter == 'B' )
            pointValue = 3;
        else if ( letter == 'C' )
            pointValue = 3;
        else if ( letter == 'D' )
            pointValue = 2;
        else if ( letter == 'E' )
            pointValue = 1;
        else if ( letter == 'F' )
            pointValue = 4;
        else if ( letter == 'G' )
            pointValue = 2;
        else if ( letter == 'H' )
            pointValue = 4;
        else if ( letter == 'I' )
            pointValue = 1;
        else if ( letter == 'J' )
            pointValue = 8;
        else if ( letter == 'K' )
            pointValue = 5;
        else if ( letter == 'L' )
            pointValue = 1;
        else if ( letter == 'M' )
            pointValue = 3;
        else if ( letter == 'N' )
            pointValue = 1;
        else if ( letter == 'O' )
            pointValue = 1;
        else if ( letter == 'P' )
            pointValue = 3;
        else if ( letter == 'Q' )
            pointValue = 10;
        else if ( letter == 'R' )
            pointValue = 1;
        else if ( letter == 'S' )
            pointValue = 1;
        else if ( letter == 'T' )
            pointValue = 1;
        else if ( letter == 'U' )
            pointValue = 1;
        else if ( letter == 'V' )
            pointValue = 4;
        else if ( letter == 'W' )
            pointValue = 4;
        else if ( letter == 'X' )
            pointValue = 8;
        else if ( letter == 'Y' )
            pointValue = 4;
        else if ( letter == 'Z' )
            pointValue = 10;
        else if ( letter == ' ' )
            pointValue = 0;
    }


    /**
     * 
     * gets the letter
     * 
     * @return letter
     */
    public char getLetter()
    {
        return letter;
    }


    /**
     * 
     * gets the point value
     * 
     * @return pointValue
     */
    public int getPointValue()
    {
        return pointValue;
    }
    
    public String toString()
    {
        return "" + letter;
    }
}
