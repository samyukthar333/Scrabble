import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({})
public class ScrabbleTest extends junit.framework.TestCase
{
    @Test // Letter Saanvi
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

    @Test //PlayerLetters Saanvi
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

    // Words Saanvi
    @Test
    public void testInitWords() {
        
    }
    
    @Test
    public void testInitTrie() {
        
    }
    
    @Test
    public void testIsWord() {
        
    }
    
    @Test
    public void testGetPoints() {
        
    }

    // Test ComputerPlayer

    // Square Saanvi
    @Test 
    public void testSquare() {
        
    }
    
    @Test
    public void testGetRow() {
        
    }
    
    @Test
    public void testGetCol() {
        
    }
    
    @Test
    public void testSetLetter() {
        
    }
    
    @Test
    public void testSetSpecial() {
        
    }
    
    @Test
    public void testSetRow() {
        
    }
    
    @Test
    public void testSetCol() {
        
    }
    
    @Test
    public void testGetSpecial() {
        
    }
    
    @Test
    public void testIsSpecial() {
        
    }
    
    @Test
    public void testRemoveSpecial() {
        
    }
    
    @Test
    public void testGetPoints2() {
        
    }
    
    @Test
    public void testToString() {
        
    }

    // Test Trie

    // Test TrieNode Saanvi
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

    // TestLetterBag Saanvi
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
