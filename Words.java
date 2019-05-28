import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * 
 * checks the dictionary if the word is valid
 *
 * @author samyuktha, saanvi, richa
 * @version May 22, 2019
 * @author Period: 4
 * @author Assignment: Scrabble
 *
 * @author Sources: none
 */
public class Words
{
    /**
     * HashMap of Words that represent a dictionary
     */
    public static final Map<String, Integer> words = initWords( "Collins Scrabble Words (2015)" );

    /**
     * Trie of words that represent a prefix dictionary
     */
    public static final Trie wordTrie = initTrie( "Collins Scrabble Words (2015)" );


    /**
     * initializes words with filename given
     * 
     * @param fileName
     *            name of file
     */
    private static Map<String, Integer> initWords( String fileName )
    {

        Map<String, Integer> temp = new HashMap<String, Integer>();
        Scanner inFile;
        try
        {
            if ( new File( fileName ).isFile() )
            {
                inFile = new Scanner( new File( fileName ) );

            }
            else
            {
                inFile = new Scanner( fileName );

            }
            inFile.nextLine();
            while ( inFile.hasNext() )
            {
                String tempword = inFile.next();
                int points = 0;

                for ( int i = 0; i < tempword.length(); i++ )
                {
                    points += new Letter( tempword.charAt( i ) ).getPointValue();
                }
                temp.put( tempword, points );
            }
            inFile.close();

        }
        catch ( FileNotFoundException ex )
        {
            System.out.println( "your filename is incorrect" );
        }
        return temp;

    }


    /**
     * 
     * initializes words with filename given
     * 
     * @param fileName
     *            name of file
     * @return temp
     */
    private static Trie initTrie( String fileName )
    {
        Trie temp = new Trie();
        Scanner inFile = null;
        try
        {
            if ( new File( fileName ).isFile() )
            {
                inFile = new Scanner( new File( fileName ) );

            }
            else
            {
                inFile = new Scanner( fileName );

            }
            inFile.nextLine();
            while ( inFile.hasNext() )
            {
                String tempword = inFile.next();
                temp.insert( tempword );
            }
        }
        catch ( FileNotFoundException ex )
        {
            System.out.println( "your filename is incorrect" );
        }
        inFile.close();
        return temp;
    }


    /**
     * check if given string is a word
     *
     * @param word
     *            to check
     * @return whether the given string is a word
     */
    public static boolean isWord( String word )
    {
        //return ( words.containsKey( word ) );
        return wordTrie.contains( word ); //faster to use trie rather than hashMap
    }


    /**
     * return number of points of given word
     *
     * @param word
     *            word to get points for
     * @return number of points of given word or -1 if word does not exist
     */
    public static int getPoints( String word )
    {
        if ( !isWord( word ) )
            return -1;
        return words.get( word );
    }

}
