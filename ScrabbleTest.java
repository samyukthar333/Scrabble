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
       Letter a = new Letter('A');
       assertEquals(a.getPointValue(), 1 );
       assertEquals(a.getLetter(), 'A');
       
    }

    @Test
    public void testGetLetter()
    {
        Letter a = new Letter('A');
        assertEquals(a.getLetter(), 'A');
    }

    @Test
    public void testGetPointValue()
    {
        Letter a = new Letter('A');
        assertEquals(a.getPointValue(), 1 );
    }

    @Test //PlayerLetters  
    public void testPlayerLettersConstructorParam()
    {
       PlayerLetters myLetters = new PlayerLetters();
       assertNotNull(myLetters.getLetters());
       assertEquals(myLetters.getLetters().size(), 0);
       
    }
    
    @Test
    public void testAdd() {
        PlayerLetters myLetters = new PlayerLetters();
        myLetters.add(new Letter('A'));
        myLetters.add( new Letter('B') );
        assertTrue(myLetters.contains( 'A' ));
        assertEquals(myLetters.size(), 2);
    }
    
    @Test
    public void testRemoveWithLetter() {
        PlayerLetters myLetters = new PlayerLetters();
        Letter letter = new Letter('A');
        myLetters.add(letter);
        assertEquals(myLetters.remove(letter), letter);
        assertEquals(myLetters.size(), 0);
        assertNull(myLetters.remove( letter ));
    }
    
    @Test
    public void testRemoveWithChar() {
        PlayerLetters myLetters = new PlayerLetters();
        Letter letter = new Letter('A');
        myLetters.add(letter);
        assertEquals(myLetters.remove('A'), letter);
        assertEquals(myLetters.size(), 0);
        assertNull(myLetters.remove( 'A' ));
    }
    
    @Test
    public void testRemoveIndex() {
        PlayerLetters myLetters = new PlayerLetters();
        Letter letter = new Letter('A');
        myLetters.add(letter);
        assertEquals(myLetters.removeIndex(0), letter);
        assertEquals(myLetters.size(), 0);
        assertNull(myLetters.removeIndex( 1 ));
    }
    
    @Test
    public void testSize() {
        PlayerLetters myLetters = new PlayerLetters();
        Letter letter = new Letter('A');
        myLetters.add(letter);
        assertEquals(myLetters.size(), 1);
    }
    
    @Test
    public void testGetLetters() {
        PlayerLetters myLetters = new PlayerLetters();
        Letter letter = new Letter('A');
        myLetters.add(letter);
        ArrayList<Letter> temp = new ArrayList<Letter>();
        temp.add(letter);
        assertEquals(myLetters.getLetters(), temp);
    }
    
    @Test
    public void testContains()
    {
        PlayerLetters myLetters = new PlayerLetters();
        Letter letter = new Letter('A');
        myLetters.add(letter);
        assertTrue(myLetters.contains('A'));
        assertFalse(myLetters.contains('B'));
    }

    // Test Player

    // Test Board

    // Test Game

    // Words  
    @Test
    public void testWords() {
        assertTrue(Words.words.containsKey( "HELLO" ));
        assertTrue((Words.words.get( "AA" ) == 2));
        assertFalse(Words.words.containsKey( "aljdkf" ));
    }
    
    @Test
    public void testTrie() {
        assertTrue(Words.wordTrie.contains( "HELLO" ));
        assertTrue(Words.wordTrie.getEndNode( "HELLO" ).isEnd());
        assertFalse(Words.wordTrie.contains( "as;dflkalsd;flk" ));
        assertNull(Words.wordTrie.getEndNode( "ALDKJ" ));
    }
    
    @Test
    public void testIsWord() {
        assertFalse(Words.isWord( "AKLJSLDFKSJF" ));
        assertTrue(Words.isWord( "HELLO" ));
    }
    
    @Test
    public void testGetPointsWords() {
        assertEquals(Words.getPoints( "AA" ), 2);
        assertEquals(Words.getPoints( "ALSJDK" ), -1);
    }

    // Test ComputerPlayer

    // Square
    @Test 
    public void testSquareConstructor1() {
        Square s1 = new Square(2,2,6);
        assertEquals(s1.getRow(),2);
        assertEquals(s1.getCol(),6);
        assertEquals(s1.getSpecial(),2);
        assertNull(s1.getLetter());
        
        Square s2 = new Square(10,1000, 1000);
        assertEquals(s2.getRow(),-1);
        assertEquals(s2.getCol(),-1);
        assertEquals(s2.getSpecial(),0);
        assertNull(s2.getLetter());
    }
    
    @Test
    public void testSquareConstructor2() {
        Square s1 = new Square(2,6);
        assertEquals(s1.getRow(),2);
        assertEquals(s1.getCol(),6);
        assertEquals(s1.getSpecial(),0);
        assertNull(s1.getLetter());
        
        Square s2 = new Square(1000, 1000);
        assertEquals(s2.getRow(),-1);
        assertEquals(s2.getCol(),-1);
        assertEquals(s2.getSpecial(),0);
        assertNull(s2.getLetter());
    }
    
    @Test
    public void testGetRow() {
        Square s1 = new Square(2,6);
        assertEquals(s1.getRow(),2);
    }
    
    @Test
    public void testGetCol() {
        Square s1 = new Square(2,6);
        assertEquals(s1.getCol(),6);
    }
    
    @Test
    public void testSetLetter() {
        Square s1 = new Square(2,6);
        Letter l = new Letter('A');
        assertEquals(s1.setLetter( l ), 1);
        assertEquals(s1.getLetter(),l);
    }
    
    @Test
    public void testSetSpecial() {
        Square s1 = new Square(2,6);
        assertEquals(s1.getSpecial(),0);
        s1.setSpecial( 1 );
        assertEquals( s1.getSpecial(), 1 );
        s1.setSpecial( 1000 );
        assertEquals( s1.getSpecial(), 1 );
    }
    
    @Test
    public void testSetRow() {
        Square s1 = new Square(2,6);
        assertTrue(s1.setRow( 10 ));
        assertEquals(s1.getRow(),10);
        assertFalse(s1.setRow(1000));
        assertEquals(s1.getRow(),10);
    }
    
    @Test
    public void testSetCol() {
        Square s1 = new Square(2,6);
        assertTrue(s1.setCol( 10 ));
        assertEquals(s1.getCol(),10);
        assertFalse(s1.setCol(1000));
        assertEquals(s1.getCol(),10);
    }
    
    @Test
    public void testGetSpecial() {
        Square s1 = new Square(2,6);
        assertEquals(s1.getSpecial(),0);
        s1.setSpecial( 1 );
        assertEquals( s1.getSpecial(), 1 );
        s1.setSpecial( 1000 );
        assertEquals( s1.getSpecial(), 1 );
    }
    
    @Test
    public void testIsSpecial() {
        Square s1 = new Square(2,6);
        assertFalse(s1.isSpecial());
        s1.setSpecial( 1 );
        assertTrue( s1.isSpecial() );
    }
    
    @Test
    public void testRemoveSpecial() {
        Square s1 = new Square(2,6);
        assertFalse(s1.isSpecial());
        s1.setSpecial( 1 );
        assertTrue( s1.isSpecial() );
        s1.removeSpecial();
        assertFalse(s1.isSpecial());
    }
    
    @Test
    public void testGetPointsSquare() {
        Square s1 = new Square(2,6);
        s1.setLetter( new Letter('A') );
        assertEquals(s1.getPoints(), 1);
    }
    
    @Test
    public void testToString() {
        Square s1 = new Square(2,6);
        s1.setLetter( new Letter('A') );
        assertEquals(s1.toString(), 'A');
    }

    // Test Trie

    // Test TrieNode
    @Test
    public void testConstructor1Param2() {
        
    }
    
    @Test
    public void testGetChildren() {
        
    }
    
    @Test
    public void testGetChar2() {
        
    }
    
    @Test
    public void testSetChar() {
        
    }
    
    @Test
    public void testMakeEnd() {
        
    }
    
    @Test
    public void testIsEnd() {
        
    }
    
    @Test
    public void testHasChildren() {
        
    }

    // TestLetterBag  
    @Test
    public void testConstructor1Param3() {
        
    }
    
    @Test
    public void testInitLetters() {
        
    }
    
    @Test
    public void testGetRandomLetters() {
        
    }
    
    @Test
    public void testGetRandomLettersWithoutRemoving() {
        
    }
    
    @Test
    public void testSize2 () {
        
    }
    
    @Test
    public void testGetRandomLetter() {
        
    }
    
    @Test
    public void testAdd2() {
        
    }
    
    @Test
    public void testIsEmpty() {
        
    }
}
