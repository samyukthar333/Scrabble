import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({})
public class ScrabbleTest extends junit.framework.TestCase
{
    @Test // Letter Saanvi
    public void testConstructor1Param()
    {
       Letter a = 'a';
       assertEquals(a, 1);
    }


    public void testGetChar()
    {
        
    }


    public void testGetPointValue()
    {

    }

    @Test //PlayerLetters Saanvi
    public void testAdd() {
        Letter e = new Letter('C', 'D', 'E');
        Letter b = e.add('B'); 
        assertEquals(a, true);
    }
    
    public void testRemove() {
        Letter e = new Letter('C', 'D', 'E');
        Letter c = 'c';
        assertEquals(c, true);
    }
    
    public void testRemoveIndex() {
        
    }
    
    public void testSize() {
        
    }
    
    public void testGetLetters() {
        
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
