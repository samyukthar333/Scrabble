import java.util.HashMap;


/**
 * the nodes of the tries
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
        isEnd = false;
    }


    /**
     * constructs TrieNode with given character
     * @param c the character
     */
    public TrieNode( char c )
    {
        children = new HashMap<Character, TrieNode>();
        myChar = c;
        isEnd = false;

    }


    /**
     * 
     * returns children of current node
     * 
     * @return children of current node (HashMap)
     */
    public HashMap<Character, TrieNode> getChildren()
    {
        return children;
    }


    /**
     * 
     * returns character of the node
     * 
     * @return myChar character of the node
     */
    public char getChar()
    {
        return myChar;
    }


    /**
     * 
     * sets the character of the node to given value
     * 
     * @param c the character to set the node
     */
    public void setChar( char c )
    {
        myChar = c;
    }


    /**
     * 
     * makes the node an ending node
     */
    public void makeEnd()
    {
        isEnd = true;
    }


    /**
     * 
     * returns whether the node is an end node or not
     * 
     * @return true if the node is an end node, false otherwise
     */
    public boolean isEnd()
    {
        return isEnd;
    }


    /**
     * 
     * checks if the node has children
     * 
     * @return !children.isEmpty() || (children != null)
     */
    public boolean hasChildren()
    {
        return !children.isEmpty() || ( children != null );
    }
}
