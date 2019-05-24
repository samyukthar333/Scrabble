import java.util.ArrayList;


/**
 * 
 * assigns letters to a point value
 *
 * @author samyuktha, saanvi, richa
 * @version May 22, 2019
 * @author Period: 4
 * @author Assignment: Scrabble
 *
 * @author Sources: none
 */
public class LetterBag
{
    private ArrayList<Letter> letters;


    /**
     * initalizes arraylist letters
     */
    public LetterBag()
    {
        letters = new ArrayList<Letter>();
        initLetters();
    }


    /**
     * 
     * initializes all letters according to how often they appear in the
     * scrabble bag
     */
    public void initLetters()
    {
        for ( int i = 0; i < 9; i++ )
        {
            letters.add( new Letter( 'A' ) );
        }
        for ( int i = 0; i < 2; i++ )
        {
            letters.add( new Letter( 'B' ) );
        }
        for ( int i = 0; i < 2; i++ )
        {
            letters.add( new Letter( 'C' ) );
        }
        for ( int i = 0; i < 4; i++ )
        {
            letters.add( new Letter( 'D' ) );
        }
        for ( int i = 0; i < 12; i++ )
        {
            letters.add( new Letter( 'E' ) );
        }
        for ( int i = 0; i < 2; i++ )
        {
            letters.add( new Letter( 'F' ) );
        }
        for ( int i = 0; i < 3; i++ )
        {
            letters.add( new Letter( 'G' ) );
        }
        for ( int i = 0; i < 2; i++ )
        {
            letters.add( new Letter( 'H' ) );
        }
        for ( int i = 0; i < 9; i++ )
        {
            letters.add( new Letter( 'I' ) );
        }
        for ( int i = 0; i < 1; i++ )
        {
            letters.add( new Letter( 'J' ) );
        }
        for ( int i = 0; i < 1; i++ )
        {
            letters.add( new Letter( 'K' ) );
        }
        for ( int i = 0; i < 4; i++ )
        {
            letters.add( new Letter( 'L' ) );
        }
        for ( int i = 0; i < 2; i++ )
        {
            letters.add( new Letter( 'M' ) );
        }
        for ( int i = 0; i < 6; i++ )
        {
            letters.add( new Letter( 'N' ) );
        }
        for ( int i = 0; i < 8; i++ )
        {
            letters.add( new Letter( 'O' ) );
        }
        for ( int i = 0; i < 2; i++ )
        {
            letters.add( new Letter( 'P' ) );
        }
        for ( int i = 0; i < 1; i++ )
        {
            letters.add( new Letter( 'Q' ) );
        }
        for ( int i = 0; i < 6; i++ )
        {
            letters.add( new Letter( 'R' ) );
        }
        for ( int i = 0; i < 4; i++ )
        {
            letters.add( new Letter( 'S' ) );
        }
        for ( int i = 0; i < 6; i++ )
        {
            letters.add( new Letter( 'T' ) );
        }
        for ( int i = 0; i < 4; i++ )
        {
            letters.add( new Letter( 'U' ) );
        }
        for ( int i = 0; i < 2; i++ )
        {
            letters.add( new Letter( 'V' ) );
        }
        for ( int i = 0; i < 2; i++ )
        {
            letters.add( new Letter( 'W' ) );
        }
        for ( int i = 0; i < 1; i++ )
        {
            letters.add( new Letter( 'X' ) );
        }
        for ( int i = 0; i < 2; i++ )
        {
            letters.add( new Letter( 'Y' ) );
        }
        for ( int i = 0; i < 1; i++ )
        {
            letters.add( new Letter( 'Z' ) );
        }
    }


    /**
     * 
     * return a list of letters from the bag PRECONDITION: num>0
     * 
     * @param num
     *            number of letters to return
     * @return null if letters is empty, arraylist of letters
     */
    public ArrayList<Letter> getRandomLetters( int num )
    {
        if ( letters.size() == 0 )
        {
            return null;
        }
        ArrayList<Letter> letterlist = new ArrayList<Letter>();

        for ( int i = 0; i < num; i++ )
        {
            int rand = (int)( Math.random() * letters.size() );
            letterlist.add( letters.remove( rand ) );

        }
        return letterlist;
    }


    /**
     * 
     * Return a letter without removing it from the list
     * 
     * @return random letter
     */
    public Letter getRandomLetterWithoutRemoving()
    {
        if ( letters.size() == 0 )
        {
            return null;
        }
        int rand = (int)( Math.random() * letters.size() );
        return letters.get( rand );

    }


    /**
     * 
     * returns the length of the letters
     * 
     * @return letters.size()
     */
    public int size()
    {
        return letters.size();
    }


    /**
     * 
     * remove and return a random letter
     * 
     * @return a random letter
     */
    public Letter getRandomLetter()
    {
        if ( letters.size() == 0 )
        {
            return null;
        }
        int rand = (int)( Math.random() * letters.size() );
        return letters.remove( rand );

    }


    /**
     * 
     * adds this letter to the letters
     * 
     * @param letter
     *            the letters in the words
     */
    public void add( Letter letter )
    {
        letters.add( letter );
    }


    /**
     * 
     * adds each letter to the letterlist
     * 
     * @param letterlist
     *            the list of letters
     */
    public void add( ArrayList<Letter> letterlist )
    {
        for ( Letter letter : letterlist )
        {
            letters.add( letter );
        }
    }


    /**
     * 
     * returns whether or not letters is empty
     * 
     * @return (letters.isEmpty())
     */
    public boolean isEmpty()
    {
        return ( letters.isEmpty() );
    }
}
