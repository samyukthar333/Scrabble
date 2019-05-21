import java.util.HashMap;

/**
 *  TODO Write a one-sentence summary of your class here.
 *  TODO Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author  samyu
 *  @version May 19, 2019
 *  @author  Period: TODO
 *  @author  Assignment: Scrabble
 *
 *  @author  Sources: TODO
 */
public class TrieNode
{
    private char myChar;
    private HashMap<Character, TrieNode> children;
    private boolean isEnd;
    
    public TrieNode()
    {
        children = new HashMap<Character, TrieNode>();
    }
    
    public TrieNode(char c)
    {
        children = new HashMap<Character, TrieNode>();
        myChar = c;
        
    }
    
    public HashMap<Character, TrieNode> getChildren()
    {
        return children;
    }
    
    public char getChar()
    {
        return myChar;
    }
    
    public void setChar(char c)
    {
        myChar = c;
    }
    
    public void makeEnd()
    {
        isEnd = true;
    }
    
    public boolean isEnd()
    {
        return isEnd;
    }
}
