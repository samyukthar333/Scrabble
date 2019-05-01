import java.util.ArrayList;


public class LetterBag
{
    ArrayList<Letter> letters = new ArrayList<Letter>();


    public LetterBag()
    {
        for ( int i = 0; i < 9; i++ )
        {
            letters.add( new Letter( 'A', 1 ) );
        }
        for ( int i = 0; i < 2; i++ )
        {
            letters.add( new Letter( 'B', 4 ) );
        }
        for ( int i = 0; i < 2; i++ )
        {
            letters.add( new Letter( 'C', 4 ) );
        }
        for ( int i = 0; i < 4; i++ )
        {
            letters.add( new Letter( 'D', 2 ) );
        }
        for ( int i = 0; i < 12; i++ )
        {
            letters.add( new Letter( 'E', 1 ) );
        }
        for ( int i = 0; i < 2; i++ )
        {
            letters.add( new Letter( 'F', 4 ) );
        }
        for ( int i = 0; i < 3; i++ )
        {
            letters.add( new Letter( 'G', 3 ) );
        }
        for ( int i = 0; i < 2; i++ )
        {
            letters.add( new Letter( 'H', 3 ) );
        }
        for ( int i = 0; i < 9; i++ )
        {
            letters.add( new Letter( 'I', 1 ) );
        }
        for ( int i = 0; i < 1; i++ )
        {
            letters.add( new Letter( 'J', 10 ) );
        }
        for ( int i = 0; i < 1; i++ )
        {
            letters.add( new Letter( 'K', 5 ) );
        }
        for ( int i = 0; i < 4; i++ )
        {
            letters.add( new Letter( 'L', 2 ) );
        }
        for ( int i = 0; i < 2; i++ )
        {
            letters.add( new Letter( 'M', 4 ) );
        }
        for ( int i = 0; i < 6; i++ )
        {
            letters.add( new Letter( 'N', 2 ) );
        }
        for ( int i = 0; i < 8; i++ )
        {
            letters.add( new Letter( 'O', 1 ) );
        }
        for ( int i = 0; i < 2; i++ )
        {
            letters.add( new Letter( 'P', 4 ) );
        }
        for ( int i = 0; i < 1; i++ )
        {
            letters.add( new Letter( 'Q', 10 ) );
        }
        for ( int i = 0; i < 6; i++ )
        {
            letters.add( new Letter( 'R', 1 ) );
        }
        for ( int i = 0; i < 4; i++ )
        {
            letters.add( new Letter( 'S', 1 ) );
        }
        for ( int i = 0; i < 6; i++ )
        {
            letters.add( new Letter( 'T', 1 ) );
        }
        for ( int i = 0; i < 4; i++ )
        {
            letters.add( new Letter( 'U', 2 ) );
        }
        for ( int i = 0; i < 2; i++ )
        {
            letters.add( new Letter( 'V', 5 ) );
        }
        for ( int i = 0; i < 2; i++ )
        {
            letters.add( new Letter( 'W', 4 ) );
        }
        for ( int i = 0; i < 1; i++ )
        {
            letters.add( new Letter( 'X', 8 ) );
        }
        for ( int i = 0; i < 2; i++ )
        {
            letters.add( new Letter( 'Y', 3 ) );
        }
        for ( int i = 0; i < 1; i++ )
        {
            letters.add( new Letter( 'Z', 10 ) );
        }
    }
}
