import java.util.ArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({})
public class ScrabbleTest extends junit.framework.TestCase
{
    @Test // Letter
    public void testLetterConstructorParam()
    {
        Letter a = new Letter( 'A' );
        assertEquals( a.getPointValue(), 1 );
        assertEquals( a.getLetter(), 'A' );

    }


    @Test
    public void testGetLetter()
    {
        Letter a = new Letter( 'A' );
        assertEquals( a.getLetter(), 'A' );
    }


    @Test
    public void testGetPointValue()
    {
        Letter a = new Letter( 'A' );
        assertEquals( a.getPointValue(), 1 );
    }


    @Test // PlayerLetters
    public void testPlayerLettersConstructorParam()
    {
        PlayerLetters myLetters = new PlayerLetters();
        assertNotNull( myLetters.getLetters() );
        assertEquals( myLetters.getLetters().size(), 0 );

    }


    @Test
    public void testAdd()
    {
        PlayerLetters myLetters = new PlayerLetters();
        myLetters.add( new Letter( 'A' ) );
        myLetters.add( new Letter( 'B' ) );
        assertTrue( myLetters.contains( 'A' ) );
        assertEquals( myLetters.size(), 2 );
    }


    @Test
    public void testRemoveWithLetter()
    {
        PlayerLetters myLetters = new PlayerLetters();
        Letter letter = new Letter( 'A' );
        myLetters.add( letter );
        assertEquals( myLetters.remove( letter ), letter );
        assertEquals( myLetters.size(), 0 );
        assertNull( myLetters.remove( letter ) );
    }


    @Test
    public void testRemoveWithChar()
    {
        PlayerLetters myLetters = new PlayerLetters();
        Letter letter = new Letter( 'A' );
        myLetters.add( letter );
        assertEquals( myLetters.remove( 'A' ), letter );
        assertEquals( myLetters.size(), 0 );
        assertNull( myLetters.remove( 'A' ) );
    }


    @Test
    public void testRemoveIndex()
    {
        PlayerLetters myLetters = new PlayerLetters();
        Letter letter = new Letter( 'A' );
        myLetters.add( letter );
        assertEquals( myLetters.removeIndex( 0 ), letter );
        assertEquals( myLetters.size(), 0 );
        assertNull( myLetters.removeIndex( 1 ) );
    }


    @Test
    public void testSize()
    {
        PlayerLetters myLetters = new PlayerLetters();
        Letter letter = new Letter( 'A' );
        myLetters.add( letter );
        assertEquals( myLetters.size(), 1 );
    }


    @Test
    public void testGetLetters()
    {
        PlayerLetters myLetters = new PlayerLetters();
        Letter letter = new Letter( 'A' );
        myLetters.add( letter );
        ArrayList<Letter> temp = new ArrayList<Letter>();
        temp.add( letter );
        assertEquals( myLetters.getLetters(), temp );
    }


    @Test
    public void testContains()
    {
        PlayerLetters myLetters = new PlayerLetters();
        Letter letter = new Letter( 'A' );
        myLetters.add( letter );
        assertTrue( myLetters.contains( 'A' ) );
        assertFalse( myLetters.contains( 'B' ) );
    }


    // Player
    @Test
    public void testPlayerConstructor()
    {
        Player p = new Player();
        assertEquals(p.getPoints(), 0);
        assertNotNull(p.getLetters());
        assertEquals(p.getLetters().size(),0);
    }


    @Test
    public void testAddPoints()
    {
        Player p = new Player();
        assertEquals(p.getPoints(), 0);
        p.addPoints( 100 );
        assertEquals(p.getPoints(),100);
    }


    @Test
    public void testGetPoints()
    {       
        Player p = new Player();
        assertEquals(p.getPoints(), 0);
        p.addPoints( 100 );
        assertEquals(p.getPoints(),100);
    }


    @Test
    public void testGetLettersPlayer()
    {
        Player p = new Player();
        assertEquals(p.getLetters().size(), 0);
        Letter l = new Letter('S');
        p.getLetters().add( l );
        assertEquals(p.getLetters().getLetters().get( 0 ), l);
        assertEquals(p.getLetters().getLetters().size(), 1);
    }


    // Board
    @Test
    public void testBoardConstructor()
    {
        Board board = new Board();
        assertNotNull(board.getBoard());
        assertEquals(board.getBoard()[0][0].getSpecial(), 4);
        assertFalse( board.isTransposed() );
        assertEquals(board.getAnchors().size(), 0);
        assertTrue(board.getBitVector( 0, 0 ).get( 0 ));
    }

    @Test
    public void testSetBoard()
    {
        Board board = new Board();
        Square[][] squares = new Square[15][15];
        for ( int i = 0; i < squares.length; i++ )
            for ( int j = 0; j < squares[0].length; j++ )
                squares[i][j].setLetter( new Letter('A') );
        board.setBoard( squares );
        assertTrue(board.getBoard().equals( squares ));
        
    }

    @Test
    public void testIsEmpty()
    {
        Board board = new Board();
        assertTrue(board.isEmpty());
        board.getBoard()[0][0].setLetter(new Letter('A'));
        assertFalse(board.isEmpty());
    }

    @Test
    public void testTransposeBack()
    {
        Board board = new Board();
        Board temp = new Board();
        temp.setBoard( board.copy() );
        for ( int i = 0; i < board.getBoard().length; i++ )
            for ( int j = 0; j < board.getBoard()[0].length/2; j++ )
                board.getBoard()[i][j].setLetter( new Letter('A') );
        for ( int i = 0; i < board.getBoard().length; i++ )
            for ( int j = board.getBoard()[0].length; j < board.getBoard()[0].length; j++ )
                board.getBoard()[i][j].setLetter( new Letter('B') );
        board.transpose();
        board.transposeBack();
        assertEquals(board,temp);
        assertFalse(board.isTransposed());
        
    }


    @Test
    public void testTranspose()
    {
        Board board = new Board();
        for ( int i = 0; i < board.getBoard().length; i++ )
            for ( int j = 0; j < board.getBoard()[0].length/2; j++ )
                board.getBoard()[i][j].setLetter( new Letter('A') );
        for ( int i = 0; i < board.getBoard().length; i++ )
            for ( int j = board.getBoard()[0].length; j < board.getBoard()[0].length; j++ )
                board.getBoard()[i][j].setLetter( new Letter('B') );
        assertFalse(board.isTransposed());
        board.transpose();
        assertTrue(board.isTransposed());
        assertTrue(board.getSquare( 0, 0 ).getLetter().getLetter()=='B');
        
        
    }


    @Test
    public void testCopy()
    {
        Board board = new Board();
        Board temp = new Board();
        temp.setBoard( board.copy() );
        assertFalse(board==temp);
        assertTrue(board.equals( temp ));
    }


    @Test
    public void testGetSquare()
    {
        Board board = new Board();
        Letter l = new Letter('S');
        board.getBoard()[0][0].setLetter( l );
        assertEquals(board.getSquare( 0,0 ).getLetter(), l);
        assertNull(board.getSquare( 17, 17 ));
    }


    @Test
    public void testGetAnchors()
    {
        Board board = new Board();
        assertTrue(board.getAnchors().isEmpty());
        Letter l = new Letter('S');
        board.getBoard()[1][0].setLetter( l );
        assertFalse(board.getAnchors().isEmpty());
    }


    @Test
    public void testIsValid()
    {
        Board board = new Board();
        assertTrue( board.isValid( 1, 1 ) );
        assertFalse(board.isValid(-1,-1) );
        assertFalse(board.isValid(17, 16 ));
    }


    @Test
    public void testGetBitVector()
    {
        Board board = new Board();
        assertNotNull( board.getBitVector( 1, 1 ) );
        assertNull(board.getBitVector( -1, 18 ));
        
    }


    @Test
    public void testGetOccupiedNeighbors()
    {
        Board board = new Board();
        assertEquals(board.getOccupiedNeighbors( board.getSquare( 6, 6 )).size(), 0);
        board.getBoard()[6][5].setLetter( new Letter('S') );
        board.getBoard()[6][7].setLetter( new Letter('S') );
        board.getBoard()[5][6].setLetter( new Letter('S') );
        board.getBoard()[7][6].setLetter( new Letter('S') );
        assertEquals(board.getOccupiedNeighbors( board.getSquare( 6, 6 )).size(), 4);
        
    }


    @Test
    public void testGetRightAndLeft()
    {
        Board board = new Board();
        assertEquals(board.getRightandLeft( board.getSquare( 6, 6 )).size(), 0);
        board.getBoard()[5][6].setLetter( new Letter('S') );
        assertEquals(board.getRightandLeft( board.getSquare( 6, 6 )).size(), 1);
        board.getBoard()[7][6].setLetter( new Letter('S') );
        assertEquals(board.getRightandLeft( board.getSquare( 6, 6 )).size(), 2);
    }


    @Test
    public void testPlaceWord()
    {
        Board board = new Board();
    }

    @Test
    public void testIsTransposed()
    {
        Board board = new Board();
        assertFalse(board.isTransposed());
        board.transpose();
        assertTrue(board.isTransposed());
        board.transposeBack();
        assertFalse(board.isTransposed());
    }


    @Test
    public void testContainsTopOrBottom()
    {
        Board board = new Board();
        assertFalse(board.containsToporBottom(6,6));
        board.getBoard()[6][5].setLetter( new Letter('S') );
        assertTrue(board.containsToporBottom(6,6));
    }

    @Test
    public void testTransposeSquares()
    {
        Board board = new Board();
        ArrayList<Square> squares = new ArrayList<Square>();
        Square s1 = new Square(6,6);
        squares.add( s1 );
        board.transposeSquares(squares);
        Square s2 = squares.get( 0 );
        assertEquals(s2.getRow(), 14 - s1.getCol());
        assertEquals(s2.getCol(), s1.getRow());
    }


    @Test
    public void testTransposeSquaresBack()
    {
        Board board = new Board();
        ArrayList<Square> squares = new ArrayList<Square>();
        Square s1 = new Square(6,6);
        squares.add( s1 );
        board.transposeSquares(squares);
        Square s2 = squares.get( 0 );
        board.transposeSquaresBack( squares );
        assertEquals(s2,s1);
    }

    @Test
    public void testAddLetter()
    {
        Board board = new Board();
        Letter l = new Letter('P');
        assertTrue(board.addLetter( l, 6, 6 ));
        assertEquals(board.getSquare( 6, 6 ).getLetter(), l);
        assertFalse(board.addLetter( l, 100, 136 ));
        
    }


    @Test
    public void testPrintBoard()
    {
        Board board = new Board();
        board.printBoard();
    }


    @Test
    public void testPrintBoardSp()
    {
        Board board = new Board();
        board.printBoardSp();
    }


    @Test
    public void testGetBoard()
    {
        Board board = new Board();
        assertNotNull(board.getBoard());
    }


    // Game
    @Test
    public void testGameConstuctor()
    {

    }


    @Test
    public void testGetComputer()
    {

    }


    @Test
    public void testGetHuman()
    {

    }


    @Test
    public void testIsComputer()
    {

    }


    @Test
    public void testGetCurrentPlayer()
    {

    }


    @Test
    public void testGetBoard2()
    {

    }


    @Test
    public void testGetBag()
    {

    }


    @Test
    public void testGetRandomThree()
    {

    }


    @Test
    public void testChooseFirstPlayer()
    {

    }


    @Test
    public void testSwitchPlayers()
    {

    }


    @Test
    public void testPlay()
    {

    }


    @Test
    public void testReplenishLetters()
    {

    }


    @Test
    public void testExchange()
    {

    }


    @Test
    public void testPass()
    {

    }


    // Words
    @Test
    public void testWords()
    {
        assertTrue( Words.words.containsKey( "HELLO" ) );
        assertTrue( ( Words.words.get( "AA" ) == 2 ) );
        assertFalse( Words.words.containsKey( "aljdkf" ) );
    }


    @Test
    public void testTrie()
    {
        assertTrue( Words.wordTrie.contains( "HELLO" ) );
        assertTrue( Words.wordTrie.getEndNode( "HELLO" ).isEnd() );
        assertFalse( Words.wordTrie.contains( "as;dflkalsd;flk" ) );
        assertNull( Words.wordTrie.getEndNode( "ALDKJ" ) );
    }


    @Test
    public void testIsWord()
    {
        assertFalse( Words.isWord( "AKLJSLDFKSJF" ) );
        assertTrue( Words.isWord( "HELLO" ) );
    }


    @Test
    public void testGetPointsWords()
    {
        assertEquals( Words.getPoints( "AA" ), 2 );
        assertEquals( Words.getPoints( "ALSJDK" ), -1 );
    }


    // ComputerPlayer
    @Test
    public void testComputerPlayerConstuctor()
    {

    }


    @Test
    public void testGetExchange()
    {

    }


    @Test
    public void testFindWord()
    {

    }


    @Test
    public void testFindLeftPart()
    {

    }


    @Test
    public void testFindLeftPartHelper()
    {

    }


    @Test
    public void testExtendRight()
    {

    }


    @Test
    public void testExecutePlay()
    {

    }


    // Square
    @Test
    public void testSquareConstructor1()
    {
        Square s1 = new Square( 2, 2, 6 );
        assertEquals( s1.getRow(), 2 );
        assertEquals( s1.getCol(), 6 );
        assertEquals( s1.getSpecial(), 2 );
        assertNull( s1.getLetter() );

        Square s2 = new Square( 10, 1000, 1000 );
        assertEquals( s2.getRow(), -1 );
        assertEquals( s2.getCol(), -1 );
        assertEquals( s2.getSpecial(), 0 );
        assertNull( s2.getLetter() );
    }


    @Test
    public void testSquareConstructor2()
    {
        Square s1 = new Square( 2, 6 );
        assertEquals( s1.getRow(), 2 );
        assertEquals( s1.getCol(), 6 );
        assertEquals( s1.getSpecial(), 0 );
        assertNull( s1.getLetter() );

        Square s2 = new Square( 1000, 1000 );
        assertEquals( s2.getRow(), -1 );
        assertEquals( s2.getCol(), -1 );
        assertEquals( s2.getSpecial(), 0 );
        assertNull( s2.getLetter() );
    }


    @Test
    public void testGetRow()
    {
        Square s1 = new Square( 2, 6 );
        assertEquals( s1.getRow(), 2 );
    }


    @Test
    public void testGetCol()
    {
        Square s1 = new Square( 2, 6 );
        assertEquals( s1.getCol(), 6 );
    }


    @Test
    public void testSetLetter()
    {
        Square s1 = new Square( 2, 6 );
        Letter l = new Letter( 'A' );
        assertEquals( s1.setLetter( l ), 1 );
        assertEquals( s1.getLetter(), l );
    }


    @Test
    public void testSetSpecial()
    {
        Square s1 = new Square( 2, 6 );
        assertEquals( s1.getSpecial(), 0 );
        s1.setSpecial( 1 );
        assertEquals( s1.getSpecial(), 1 );
        s1.setSpecial( 1000 );
        assertEquals( s1.getSpecial(), 1 );
    }


    @Test
    public void testSetRow()
    {
        Square s1 = new Square( 2, 6 );
        assertTrue( s1.setRow( 10 ) );
        assertEquals( s1.getRow(), 10 );
        assertFalse( s1.setRow( 1000 ) );
        assertEquals( s1.getRow(), 10 );
    }


    @Test
    public void testSetCol()
    {
        Square s1 = new Square( 2, 6 );
        assertTrue( s1.setCol( 10 ) );
        assertEquals( s1.getCol(), 10 );
        assertFalse( s1.setCol( 1000 ) );
        assertEquals( s1.getCol(), 10 );
    }


    @Test
    public void testGetSpecial()
    {
        Square s1 = new Square( 2, 6 );
        assertEquals( s1.getSpecial(), 0 );
        s1.setSpecial( 1 );
        assertEquals( s1.getSpecial(), 1 );
        s1.setSpecial( 1000 );
        assertEquals( s1.getSpecial(), 1 );
    }


    @Test
    public void testIsSpecial()
    {
        Square s1 = new Square( 2, 6 );
        assertFalse( s1.isSpecial() );
        s1.setSpecial( 1 );
        assertTrue( s1.isSpecial() );
    }


    @Test
    public void testRemoveSpecial()
    {
        Square s1 = new Square( 2, 6 );
        assertFalse( s1.isSpecial() );
        s1.setSpecial( 1 );
        assertTrue( s1.isSpecial() );
        s1.removeSpecial();
        assertFalse( s1.isSpecial() );
    }


    @Test
    public void testGetPointsSquare()
    {
        Square s1 = new Square( 2, 6 );
        s1.setLetter( new Letter( 'A' ) );
        assertEquals( s1.getPoints(), 1 );
    }


    @Test
    public void testToString()
    {
        Square s1 = new Square( 2, 6 );
        s1.setLetter( new Letter( 'A' ) );
        assertEquals( s1.toString(), 'A' );
    }


    // Trie
    @Test
    public void testTrie2()
    {

    }


    @Test
    public void testGetRoot()
    {

    }


    @Test
    public void testSetRoot()
    {

    }


    @Test
    public void testInsert()
    {

    }


    @Test
    public void testContains2()
    {

    }


    @Test
    public void testGetEndNode()
    {

    }


    @Test
    public void testIsPrefix()
    {

    }


    // Test TrieNode
    @Test
    public void testConstructor1TrieNode()
    {
        TrieNode node = new TrieNode();
        assertNotNull( node.getChildren() );
        assertFalse(node.getChar()=='A');
        assertFalse(node.isEnd());
    }
    
    @Test
    public void testConstructor2TrieNode()
    {
        TrieNode node = new TrieNode('A');
        assertNotNull( node.getChildren() );
        assertFalse(node.getChar()=='A');
        assertFalse(node.isEnd());
    }


    @Test
    public void testGetChildren()
    {
        TrieNode node = new TrieNode();
        node.getChildren().put( 'C', new TrieNode() );
        assertNotNull( node.getChildren() );
        assertEquals(node.getChildren().values().size(),1);
    }


    @Test
    public void testGetCharTrieNode()
    {
        TrieNode node = new TrieNode('A');
        assertEquals(node.getChar(), 'A');
    }


    @Test
    public void testSetChar()
    {
        TrieNode node = new TrieNode();
        node.setChar( 'A' );
        assertEquals(node.getChar(), 'A');
    }


    @Test
    public void testMakeEnd()
    {
        TrieNode node = new TrieNode();
        assertFalse(node.isEnd());
        node.makeEnd();
        assertTrue( node.isEnd() );
    }


    @Test
    public void testIsEnd()
    {
        TrieNode node = new TrieNode();
        assertFalse(node.isEnd());
        node.makeEnd();
        assertTrue( node.isEnd() );
    }


    @Test
    public void testHasChildren()
    {
        TrieNode node = new TrieNode();
        
    }


    // TestLetterBag
    @Test
    public void testLetterBagConstructor()
    {
        LetterBag bag = new LetterBag();
        assertNotNull(bag.getBag());
        assertEquals(bag.getBag().size(), 100);
    }


    @Test
    public void testGetRandomLetters()
    {
        LetterBag bag = new LetterBag();
        assertEquals(bag.getRandomLetters( 5 ).size(), 5);
        assertEquals(bag.getBag().size(), 95);
        assertNull(bag.getRandomLetters( 100 ));
    }


    @Test
    public void testGetRandomLettersWithoutRemoving()
    {
        LetterBag bag = new LetterBag();
        assertNotNull(bag.getRandomLetterWithoutRemoving());
        assertEquals(bag.getBag().size(), 100);
    }


    @Test
    public void testSizeLetterBag()
    {
        LetterBag bag = new LetterBag();
        assertEquals(bag.getBag().size(), 100);
        bag.getRandomLetter();
        assertEquals(bag.getBag().size(), 99);
    }


    @Test
    public void testGetRandomLetter()
    {
        LetterBag bag = new LetterBag();
        assertNotNull(bag.getRandomLetter());
        for(int i = 100; i>0; i++)
        {
            bag.getRandomLetter();
        }
        assertNull(bag.getRandomLetter());
    }


    @Test
    public void testAddLetterBag()
    {
        LetterBag bag = new LetterBag();
        bag.add( new Letter('C') );
        assertEquals(bag.size(), 101);
    }

    @Test
    public void testAddMultipleLetters()
    {
        LetterBag bag = new LetterBag();
        ArrayList<Letter> letters = new ArrayList<Letter>();
        letters.add( new Letter('C') );
        letters.add( new Letter('B') );
        letters.add( new Letter('E') );
        letters.add( new Letter('A') );
        letters.add( new Letter('H') );
        bag.add( letters );
        assertEquals(bag.size(), 105);
    }

    @Test
    public void testIsEmptyLetterBag()
    {
        LetterBag bag = new LetterBag();
        assertFalse(bag.isEmpty());
        for(int i = 0; i<100; i++)
        {
            bag.getRandomLetter();
        }
        assertTrue(bag.isEmpty());

    }
    
    @Test
    public void testGetLetterBag()
    {
        LetterBag bag = new LetterBag();
        assertNotNull(bag.getBag());
    }
}
