import java.util.ArrayList;

public class LetterBag
{
    ArrayList<Letters> letters = new ArrayList<Character>();
    
    public LetterBag() {
        for (int i = 0; i < 9; i++) {
            letters.add( new Letter('A', 1));
        }
    }
}
