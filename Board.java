import java.util.ArrayList;


public class Board
{
    private Square[][] board;

    private ArrayList<Square> placeableLocs;


    public Board()
    {

        board = new Square[8][8]; // temporary, will change to 15*15 in
                                  // initboard
        for ( int i = 0; i < board.length; i++ )
            for ( int j = 0; j < board[0].length; j++ )
                board[i][j] = new Square( i, j );
        initBoard();

    }


    private void initBoard()
    {
        board[0][0].setSpecial( 4 );
        board[0][3].setSpecial( 1 );
        board[0][7].setSpecial( 4 );
        board[1][1].setSpecial( 3 );
        board[1][5].setSpecial( 2 );
        board[2][2].setSpecial( 3 );
        board[2][6].setSpecial( 1 );
        board[3][0].setSpecial( 1 );
        board[3][3].setSpecial( 3 );
        board[3][7].setSpecial( 1 );
        board[4][4].setSpecial( 3 );
        board[5][1].setSpecial( 2 );
        board[5][5].setSpecial( 2 );
        board[6][2].setSpecial( 1 );
        board[6][6].setSpecial( 1 );
        board[7][0].setSpecial( 4 );
        board[7][3].setSpecial( 1 );
        board[7][7].setSpecial( 3 );
        flipHorizontally();
        flipVertically();

    }


    private void flipHorizontally( )
    {
        for ( int i = 0; i < board.length; i++ )
            for ( int j = 0; j < board[0].length; j++ )
                board[i][board[0].length - 1 - j] = board[i][j];
    }


    private void flipVertically()
    {
        for ( int i = 0; i < board.length; i++ )
            for ( int j = 0; j < board[0].length; j++ )
                board[board.length - 1 - i][j] = board[i][j];
    }


    public Square getSquare( int r, int c )
    {
        return board[r][c];
    }


    public ArrayList<Square> getPlaceableLocs()
    {
        return placeableLocs;
    }


    public boolean isValid( int x, int y )
    {
        return ( x >= 0 && x < 15 && y >= 0 && y < 15 );
    }


    public ArrayList<Square> getOccupiedNeighbors( Square square )
    {
        ArrayList<Square> myOccNeighbors = new ArrayList<Square>();
        int i = square.getRow() - 1;
        int j = square.getCol();
        if ( isValid( i, j ) && board[i][j].getLetter() != null )
            myOccNeighbors.add( board[i][j] );
        i = square.getRow();
        j = square.getCol() - 1;
        if ( isValid( i, j ) && board[i][j].getLetter() != null )
            myOccNeighbors.add( board[i][j] );
        i = square.getRow() + 1;
        j = square.getCol();
        if ( isValid( i, j ) && board[i][j].getLetter() != null )
            myOccNeighbors.add( board[i][j] );
        i = square.getRow();
        j = square.getCol() + 1;
        if ( isValid( i, j ) && board[i][j].getLetter() != null )
            myOccNeighbors.add( board[i][j] );
        return myOccNeighbors;
    }


    public void fixPlaceableLocs()
    {

        // check if word can start in this location, and add to member variable
        // (should we do anything about what direction the word should go?)
        for ( int i = 0; i < 15; i++ )
        {
            for ( int j = 0; j < 15; j++ )
            {
                Square square = board[i][j];
                if ( getOccupiedNeighbors( square ).size() <= 1 )
                {

                }
            }
        }

    }


    /**
     * places word if possible. If not possible returns false, else true
     */
    public boolean placeWord( String word, Square start, boolean direction )
    {

        return false; // FIX
    }


    public boolean addLetter( Letter letter, Square square )
    {
        if ( board[square.getRow()][square.getCol()].getLetter() != null )
            return false;
        board[square.getRow()][square.getCol()].setLetter( letter );
        return true;
    }

}
