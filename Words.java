import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Words
{
    private Map<String, Integer> words;
    
    public Words(String fileName)
    {
        words = new HashMap<String, Integer>();
        initWords(fileName);


    }
    
    /**
     * initializes words with filename given
     * 
     * @param fileName name of file
     */
    public void initWords(String fileName)
    {
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
            
            while ( inFile.hasNext() )
            {
                String tempword = inFile.next();
                int points = 0;
                for(int i = 0; i<tempword.length(); i++)
                {
                    points+=new Letter(tempword.charAt( i )).getPointValue();
                }
                words.put( tempword, points );
            }
            inFile.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println( "your filename is incorrect" );
        }
        
    }
    
    /**
     * check if given string is a word
     *
     * @param word to check
     * @return whether the given string is a word
     */
    public boolean isWord(String word)
    {
        return (words.containsKey( word ));
    }
    
    /**
     * return number of points of given word
     *
     * @param word word to get points for
     * @return number of points of given word or -1 if word does not exist
     */
    public int getPoints(String word)
    {
        if(!isWord(word))
            return -1;
        return words.get( word );
    }

}
