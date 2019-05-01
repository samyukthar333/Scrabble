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
                else if(((i>0||i<5)&&(j>0||j<5)&&(i==j||i==14-j))||(i==7&&j==7))
                {
                    board[i][j] = new Square(null, 3);
                }
            }
        }
    }
    
    public Square getSquare(int r, int c)
    {
        return board[r][c];
    }
    
    
}
