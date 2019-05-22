import java.util.HashMap;


/**
 * TODO Write a one-sentence summary of your class here. TODO Follow it with
 * additional details about its purpose, what abstraction it represents, and how
 * to use it.
 *
 * @author samyuktha, saanvi, richa
 * @version May 19, 2019
 * @author Period: 4
 * @author Assignment: Scrabble
 *
 * @author Sources: none
 */
public class TrieNode
{
    private char myChar;

    private HashMap<Character, TrieNode> children;

    private boolean isEnd;


    /**
     * constructs trie node with empty children
     */
    public TrieNode()
    {
        children = new HashMap<Character, TrieNode>();
    }


    /**
     * 
     * @param c
     */
    public TrieNode( char c )
    {
        children = new HashMap<Character, TrieNode>();
        myChar = c;

    }


    /**
     * 
     * TODO Write your method description here.
     * 
     * @return children
     */
    public HashMap<Character, TrieNode> getChildren()
    {
        return children;
    }


    /**
     * 
     * TODO Write your method description here.
     * 
     * @return myChar
     */
    public char getChar()
    {
        return myChar;
    }


    /**
     * 
     * TODO Write your method description here.
     * 
     * @param c
     */
    public void setChar( char c )
    {
        myChar = c;
    }


    /**
     * 
     * TODO Write your method description here.
     */
    public void makeEnd()
    {
        isEnd = true;
    }


    /**
     * 
     * TODO Write your method description here.
     * 
     * @return isEnd
     */
    public boolean isEnd()
    {
        return isEnd;
    }


    /**
     * 
     * TODO Write your method description here.
     * 
     * @return !children.isEmpty() || (children != null)
     */
    public boolean hasChildren()
    {
        return !children.isEmpty() || ( children != null );
    }
}
