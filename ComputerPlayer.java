import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;


/**
 * Represents a Computer Player, which is a Player that finds words using a
 * computer algorithm
 *
 * @author Samyuktha, saanvi, richa
 * @version Apr 30, 2019
 * @author Period: 4
 * @author Assignment: Scrabble
 *
 * @author Sources:
 *         https://www.cs.cmu.edu/afs/cs/academic/class/15451-s06/www/lectures/scrabble.pdf
 */
public class ComputerPlayer extends Player
{
    /**
     * best play out of all of them 0 is point value (int) 1 is transposed
     * (boolean) 2 is starting square (Square) 3 is word (String)
     */
    private ArrayList<Object> bestPlay = new ArrayList<Object>();


    /**
     * calls super
     */
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
        for ( int i = 0; i < 3; i++ )
        {
            letters
                .add( myLetters.removeIndex( (int)( Math.random() * ( myLetters.size() - 1 ) ) ) );

        }
        return letters;
    }


    /**
     * 
     * finds all the possible words
     * 
     * @return executePlay(board)
     */
    public ArrayList<Square> findWord( Board board )
    {
//        double initTime = System.currentTimeMillis();
        board.fixAnchors();

        ArrayList<Square> anchors = board.getAnchors();

        Board temp = new Board();
        temp.setBoard( board.copy() );
        
        Iterator<Square> iter = anchors.iterator();
        while ( iter.hasNext() )
        {
            Square square = iter.next();
            findLeftPart( temp, board, square );

//            if ( ( System.currentTimeMillis() - initTime ) / 1000 >= 10 )
//            {
//                break;
//            }
        }
//        board.transpose();
//        iter = anchors.iterator();
//        while ( iter.hasNext() )
//        {
//            findLeftPart( board, iter.next() );
//
//            if ( ( System.currentTimeMillis() - initTime ) / 1000 >= 10 )
//            {
//                break;
//            }
//        }
        return executePlay( board );
    }


    /**
     * finds the left part
     * 
     * @param board
     * @param square
     */
    private void findLeftPart( Board temp, Board board, Square square )
    {

        String s = "";
        if ( !board.isValid( square.getRow(), square.getCol() - 1 ) )
        {
            return;
        }
        // if leftpart is already on the board
        if ( !board.getBoard()[square.getRow()][square.getCol() - 1].isEmpty() )
        {
            for ( int i = square.getCol() - 1; i >= 0
                && !board.getBoard()[square.getRow()][i].isEmpty(); i-- )
            {
                s = board.getBoard()[square.getRow()][i] + s;
            }
            extendRight( temp, board, s, Words.wordTrie.getRoot(), square );

        }
        // find all possible beginning words if left part is not already on the
        // board
        // find number of available spots before square
        int count = 0;
        for ( int i = square.getCol() - 1; i >= 0
            && board.getRightandLeft( board.getBoard()[square.getRow()][i] ).isEmpty(); i-- )
        {
            count++;
        }
        System.out.println( "count" + count );
        findLeftPartHelper( temp, board, "", Words.wordTrie.getRoot(), count, square );
    }


    /**
     * 
     * finds the left part with helper
     * 
     * @param board
     * @param partialWord
     * @param node
     * @param limit
     * @param anchor
     */
    private void findLeftPartHelper( Board temp, Board board, String partialWord, TrieNode node, int limit, Square anchor )
    {

        extendRight( temp, board, partialWord, node, anchor );
        if ( limit > 0 )
        {
            // System.out.println("partial word " + partialWord );

            HashMap<Character, TrieNode> children = node.getChildren();
            for ( TrieNode n : children.values() )
            {
                char c = n.getChar();
                // System.out.println("find left part helper " + c);
                // System.out.println( myLetters.getLetters() );
                if ( myLetters.contains( c ) )
                {
                    // System.out.println("find left part helper--Match found!!"
                    // + c);

                    myLetters.remove( c );

                    findLeftPartHelper( temp, board, partialWord + c, n, limit - 1, anchor );
                    myLetters.add( new Letter( c ) );
                }
            }
        }
    }


    /**
     * 
     * extends to the right
     * 
     * @param board
     * @param wordPart
     * @param node
     * @param square
     */
    // fix square!!
    private void extendRight( Board temp, Board board, String wordPart, TrieNode node, Square square )
    {
        // System.out.println("extend right- word part" + wordPart);
        if ( board.isValid( square.getRow(), square.getCol() ) )
        {
            if ( square.isEmpty() )
            {
                if ( node.isEnd() )
                {
                    if ( bestPlay.isEmpty() )
                    {
                        bestPlay.add( Words.getPoints( wordPart ) );
                        bestPlay.add( board.isTransposed() );
                        bestPlay.add( square );
                        bestPlay.add( wordPart );
                    }
                    else if ( (Integer)bestPlay.get( 0 ) < Words.getPoints( wordPart ) )
                    {
                        bestPlay.set( 0, Words.getPoints( wordPart ) );
                        bestPlay.set( 1, board.isTransposed() );
                        bestPlay.set( 2, square );
                        bestPlay.set( 3, wordPart );

                        temp.printBoard();
                        // System.out.println( "square's row: " +
                        // square.getRow() + " / square's col :" +
                        // square.getCol() );

                        // System.out.println(bestPlay.get( 2 ));

                    }

                    System.out.println( "best word so far: " + bestPlay.get( 3 ) );

                }
                // System.out.println("alsefkj===extend right1");

                HashMap<Character, TrieNode> children = node.getChildren();
                for ( TrieNode n : children.values() )
                {
                    // System.out.println("alsefkj===extend right1-inside
                    // loop");
                    char c = n.getChar();
                    if ( !myLetters.getLetters().isEmpty() && myLetters.contains( c ) )
                    {
                        // System.out.println("alsefkj===extend right11");
                        BitSet bitSet = board.getBitVector( square.getRow(), square.getCol() );
                        if ( bitSet.get(Character.getNumericValue( c ) - Character.getNumericValue( 'A' ) ) )
                        {
                            // System.out.println("bitset works");
                            myLetters.remove( c );
                            
                            //for testing
                            temp.getBoard()[square.getRow()][square.getCol()].setLetter(new Letter(c));
                            
                            Square next = board.getSquare( square.getRow(), square.getCol() + 1 );
                            extendRight( temp, board, wordPart + c, n, next );
                            myLetters.add( new Letter( c ) );
                        }
                    }
                }
            }
            else
            {

                // System.out.println("square is not empty");
                char c = square.getLetter().getLetter();
                // System.out.println("letter on board is: " + c);
                HashMap<Character, TrieNode> children = node.getChildren();
                if ( !children.isEmpty() && children.containsKey( c ) )
                {
                    Square next = board.getSquare( square.getRow(), square.getCol() + 1 );
                    extendRight( temp, board, wordPart + c, children.get( c ), next );
                }
            }
        }
    }


    /**
     * 
     * executes the best play
     * 
     * @param board
     * @return squares
     */
    public ArrayList<Square> executePlay( Board board )
    {
        ArrayList<Square> squares = new ArrayList<Square>();

        Square sBoard = (Square)bestPlay.get( 2 );
        System.out
            .println( "square's row: " + sBoard.getRow() + " / square's col: " + sBoard.getCol() );
        String str = (String)bestPlay.get( 3 );
        System.out.println( "executePlay - placing word" + str );
        int strloc = 0;
        int i = sBoard.getCol()-str.length();
        if(i<0)
        {
            i = 0;
        }
        
        for ( ; i < sBoard.getCol(); i++ )
        {
            System.out.println( i );
            Square s = board.getSquare( sBoard.getRow(), i );
            if ( board.getBoard()[s.getRow()][i].isEmpty() )
            {
                Square input = new Square( s.getRow(), s.getCol() );
                input.setLetter( new Letter( str.charAt( strloc ) ) );
                squares.add( input );

            }
            strloc++;

        }
        if(board.isTransposed())
        {
            board.transposeBack();
        }
        if ( (Boolean)bestPlay.get( 1 ) )
        {
            squares = board.transposeSquaresBack( squares ); // if down words,
                                                             // then transpose
                                                             // Squares
        }
        System.out.println( "Squares : " + squares );
        for ( Square s : squares )
        {
            System.out.println( "square's row: " + s.getRow() + " / square's col: " + s.getCol() );
        }
        bestPlay = new ArrayList<Object>();
        return squares;
    }


    /**
     * 
     * for testing purposes
     * 
     * @param args
     */
    public static void main( String[] args )
    {
        Board board = new Board();
        ComputerPlayer player = new ComputerPlayer();

        board.getBoard()[2][6].setLetter( new Letter( 'D' ) );
        board.getBoard()[3][6].setLetter( new Letter( 'A' ) );
        board.getBoard()[4][6].setLetter( new Letter( 'D' ) );
        board.printBoardSp();

        ArrayList<Square> input = new ArrayList<Square>();
        input.add( new Square( new Letter( 'B' ), 2, 2 ) );
        input.add( new Square( new Letter( 'O' ), 2, 3 ) );
        input.add( new Square( new Letter( 'A' ), 2, 4 ) );
        input.add( new Square( new Letter( 'R' ), 2, 5 ) );
        int points = board.placeWord( input );
        board.printBoard();
        System.out.println( points );

        input = new ArrayList<Square>();
        input.add( new Square( new Letter( 'O' ), 3, 2 ) );
        input.add( new Square( new Letter( 'A' ), 4, 2 ) );
        input.add( new Square( new Letter( 'R' ), 5, 2 ) );
        points = board.placeWord( input );
        board.printBoard();
        System.out.println( points );

        input = new ArrayList<Square>();
        input.add( new Square( new Letter( 'O' ), 4, 7 ) );
        input.add( new Square( new Letter( 'L' ), 4, 8 ) );
        input.add( new Square( new Letter( 'L' ), 4, 9 ) );
        points = board.placeWord( input );
        board.printBoard();
        System.out.println( points );

        input = new ArrayList<Square>();
        input.add( new Square( new Letter( 'U' ), 5, 9 ) );
        input.add( new Square( new Letter( 'M' ), 6, 9 ) );
        input.add( new Square( new Letter( 'B' ), 7, 9 ) );
        input.add( new Square( new Letter( 'E' ), 8, 9 ) );
        input.add( new Square( new Letter( 'R' ), 9, 9 ) );
        points = board.placeWord( input );
        board.printBoard();
        System.out.println( points );

        input = new ArrayList<Square>();
        input.add( new Square( new Letter( 'D' ), 6, 2 ) );
        // input.add( new Square( new Letter( 'S' ), 7, 2 ) );
        points = board.placeWord( input );
        board.printBoard();
        System.out.println( points );

        input = new ArrayList<Square>();
        input.add( new Square( new Letter( 'S' ), 4, 10 ) );
        points = board.placeWord( input );
        board.printBoard();
        System.out.println( points );

        input = new ArrayList<Square>();
        input.add( new Square( new Letter( 'R' ), 3, 3 ) );
        points = board.placeWord( input );
        board.printBoard();
        System.out.println( points );

        input = new ArrayList<Square>();
        input.add( new Square( new Letter( 'N' ), 5, 7 ) );
        points = board.placeWord( input );
        board.printBoard();
        System.out.println( points );

        board.printBoardSp();

        player.getLetters().add( new Letter( 'B' ) );
        player.getLetters().add( new Letter( 'A' ) );
        player.getLetters().add( new Letter( 'R' ) );
        player.getLetters().add( new Letter( 'C' ) );
        player.getLetters().add( new Letter( 'T' ) );
        player.getLetters().add( new Letter( 'Y' ) );
        player.getLetters().add( new Letter( 'B' ) );

        ArrayList<Square> letters = player.findWord( board );
        points = board.placeWord( letters );
        board.printBoard();
        System.out.println( points );

        for ( Square s : letters )
        {
            player.getLetters().remove( s.getLetter().getLetter() );
        }
        while ( player.myLetters.size() < 7 )
        {
            String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            player.myLetters.add( new Letter( alphabet.charAt( (int)( Math.random() * 25 ) ) ) );
        }
        System.out.println( player.getLetters().getLetters() );
        letters = player.findWord( board );
        points = board.placeWord( letters );
        board.printBoard();
        System.out.println( points );

        for ( Square s : letters )
        {
            player.getLetters().remove( s.getLetter().getLetter() );
        }
        while ( player.myLetters.size() < 7 )
        {
            String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            player.myLetters.add( new Letter( alphabet.charAt( (int)( Math.random() * 25 ) ) ) );
        }
        System.out.println( player.getLetters().getLetters() );
        letters = player.findWord( board );
        points = board.placeWord( letters );
        board.printBoard();
        System.out.println( points );

    }

}
