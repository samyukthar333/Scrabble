import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
/**
 *  TODO Write a one-sentence summary of your class here.
 *  TODO Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author  samyu
 *  @version Apr 30, 2019
 *  @author  Period: TODO
 *  @author  Assignment: Scrabble
 *
 *  @author  Sources: TODO
 */
public class ComputerPlayer extends Player
{
    
    private ArrayList<Object> bestPlay = new ArrayList<Object>();
    
    public ComputerPlayer()
    {
        super();
    }
    
    /**
     * exchanges 3 random letters with 3 letters from the bag
     * 
     */
    public ArrayList<Letter> getExchange()
    {
        ArrayList<Letter> letters = new ArrayList<Letter>();
        for(int i = 0; i<3; i++)
        {
            letters.add( myLetters.removeIndex( (int)(Math.random()*(myLetters.size() -1 )) ));
            
        }
        return letters;
    }
    
    
    
    public void findWord(Board board)
    {
        double initTime = System.currentTimeMillis();
        for(Square s: board.getAnchors())
        {
            findLeftPart(board, s);
            
            if(System.currentTimeMillis()-initTime/1000>=10)
            {
                break;
            }
        }
        board.transpose();
        for(Square s: board.getAnchors())
        {
            findLeftPart(board, s);
            
            if(System.currentTimeMillis()-initTime/1000>=25)
            {
                break;
            }
        }
        board.transposeBack();
        executePlay(board);
    }
    
    private void findLeftPart(Board board, Square square)
    {
        String s = "";
        if(!board.isValid( square.getRow(),  square.getCol()-1 ))
        {
            return;
        }
        //if leftpart is already on the board
        if(!board.getBoard()[square.getRow()][square.getCol()-1].isEmpty())
        {
            for(int i = square.getCol()-1; i>=0 && !board.getBoard()[square.getRow()][i].isEmpty(); i--)
            {
                s = board.getBoard()[square.getRow()][i] + s;
            }
            extendRight(board, s, Words.wordTrie.getRoot(), square);
                
        }
        //find all possible beginning words if left part is not already on the board
        //find number of available spots before square
        int count = 0;
        for(int i = square.getCol()-1; i>=0 && !board.getAnchors().contains(board.getBoard()[square.getRow()][i]); i--)
        {
            count++;
        }
        findLeftPartHelper(board, "", Words.wordTrie.getRoot(), count);
    }
    
    private void findLeftPartHelper(Board board, String partialWord, TrieNode node, int limit)
    {
        HashMap<Character, TrieNode> children = node.getChildren();
        for(TrieNode n : children.values())
        {
            char c = n.getChar();
            if(myLetters.getLetters().contains(new Letter(c)))
            {
                myLetters.remove( new Letter(c) );
                findLeftPartHelper(board, partialWord + c, n, limit-1);
                
                myLetters.add( new Letter(c) );
            }
        } 
    }
    
    public void extendRight(Board board, String wordPart, TrieNode node, Square square)
    {
        if(board.isValid( square.getRow(),  square.getCol() )) 
        {
            if(square.isEmpty())
            {
                if(node.isEnd())
                {
                    bestPlay.add( square );
                    bestPlay.add( wordPart );
                    return;
                }
                HashMap<Character, TrieNode> children = node.getChildren();
                for(TrieNode n : children.values())
                {
                    char c = n.getChar();
                    if(myLetters.getLetters().contains(new Letter(c)))
                    {
                        BitSet bitSet = board.getBitVector( square.getRow(), square.getCol() );
                        if(bitSet.get( Character.getNumericValue( c ) - Character.getNumericValue( 'A' ) ))
                        {
                            myLetters.remove(new Letter(c));
                            Square next = board.getSquare( square.getRow(), square.getCol() );
                            extendRight(board, wordPart+c, n, next);
                            myLetters.add(new Letter(c));  
                        }
                    }
                }
            }
            else
            {
                Letter letter = square.getLetter();
                HashMap<Character, TrieNode> children = node.getChildren();
                if(!children.isEmpty())
                {
                    for(TrieNode n : children.values())
                    {
                        Square next = board.getSquare( square.getRow(), square.getCol() );
                        extendRight(board, wordPart+letter.getLetter(), n, next   );
                    }
                }
            }
        }
    }
    
    public void executePlay(Board board)
    {
        ArrayList<Square> squares = new ArrayList<Square>();
        Square s = (Square)bestPlay.get( 0 );
        String str = (String)bestPlay.get( 1 );
        for(int i = s.getCol(); i<s.getCol()+str.length(); i++)
        {
            if(board.getBoard()[s.getRow()][i].isEmpty())
                squares.add(s);
            s = board.getSquare( s.getRow(), i);
        }
        board.placeWord( squares );
    }
    
}
