public class Board
{
    private Square[][] board;
    public static final int MAX_X = 14;
    public static final int MAX_Y = 14;
    
    public Board()
    {
        board = new Square[15][15]; 
        
        for(int i = 0; i <= MAX_X; i++)
        {
            for(int j = 0; j <= MAX_Y; j++)
            {
                if((i==0 || i==MAX_X)&&(j==0||j==MAX_Y)||((i==MAX_X/2&&(j==0||j==MAX_Y))||(j==MAX_Y/2&&(i==0||i==MAX_X))))
                {
                    board[i][j] = new Square(null, 4);
                }
                else if(((i>0||i<MAX_X*5/14)&&(j>0||j<MAX_Y*5/14)&&(i==j||i==MAX_Y-j))||(i==MAX_X/2&&MAX_Y==MAX_Y/2))
                {
                    board[i][j] = new Square(null, 3);
                }
               /* else if()
                {
                    board[i][j] = new Square(null, 2);
                }
                else if()
                {
                    board[i][j] = new Square(null, 1);
                }*/
                else
                {
                    board[i][j] = new Square(null, 0);
                }
            }
        }
    }
    
    public Square getSquare(int r, int c)
    {
        return board[r][c];
    }
    
    /**
     * places word if possible. If not possible returns -1. Else returns number of points the word is worth
     */
    public int placeWord(String word, Square start, boolean direction)
    {
        return -1; //TODO FIX THIS!!!
    }
    
    
}
