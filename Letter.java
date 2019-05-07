public class Letter
{
    private char letter;
    private int pointValue;
    
    public Letter(char c, int i)
    {

        letter = c;
        pointValue = i;
    }
    /**
     * 
     * TODO Write your method description here.
     * @return
     */
    public char getLetter()
    {
        return letter;
    }
    
    public int getPointValue()
    {
        return pointValue;
    }
}
