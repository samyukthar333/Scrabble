import java.util.ArrayList;
import java.util.HashMap;


/**
 * 
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
public class Trie
{
    private TrieNode root;


    /**
     * Constructs trie with given root
     * 
     * @param myRoot
     *            root of the trie
     */
    public Trie( TrieNode myRoot )
    {
        root = myRoot;
    }


    /**
     * constructs trie with null root
     */
    public Trie()
    {
        root = new TrieNode();
    }


    /**
     * returns root of Trie
     * 
     * @return root the root of the Trie
     */
    public TrieNode getRoot()
    {
        return root;
    }


    /**
     * sets root of Trie
     * 
     * @param myRoot
     *            the root of the Trie
     */
    public void setRoot( TrieNode myRoot )
    {
        root = myRoot;
    }


    /**
     * 
     * checks if there is a word
     * 
     * @param word
     *            the word that is being checked
     */
    public void insert( String word )
    {
        if ( word == null )
            return;

        HashMap<Character, TrieNode> children = root.getChildren();

        for ( int i = 0; i < word.length(); i++ )
        {
            TrieNode node = null;
            char c = word.charAt( i );

            if ( children != null && children.containsKey( c ) )
            {
                node = children.get( c );
            }
            else
            {
                node = new TrieNode( c );
                children.put( c, node );
            }
            children = node.getChildren();

            if ( i == word.length() - 1 )
                node.makeEnd();
        }

    }


    /**
     * 
     * checks if the trie contains the word being checked
     * 
     * @param word
     *            the word that is being checked
     * @return (node != null && node.isEnd());
     */
    public boolean contains( String word )
    {
        TrieNode node = getEndNode( word );

        return ( node != null && node.isEnd() );

    }


    /**
     * 
     * returns the end node
     * 
     * @param s
     *            the string where the end node is coming from
     * @return node
     */
    public TrieNode getEndNode( String s )
    {
        if ( s == null )
            return null;
        HashMap<Character, TrieNode> children = root.getChildren();
        TrieNode node = null;

        for ( int i = 0; i < s.length(); i++ )
        {
            char c = s.charAt( i );
            if ( children.containsKey( c ) )
            {
                node = children.get( c );
                children = node.getChildren();
            }
            else
            {
                return null;
            }
        }
        return node;
    }


    /**
     * 
     * checks if the end node is a prefix
     * 
     * @param pre
     *            the prefix that is being checked if its in the word
     * @return getEndNode(pre) != null
     */
    public boolean isPrefix( String pre )
    {
        return getEndNode( pre ) != null;
    }

}
