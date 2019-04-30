public class Board
{
    private Square[][] board;
    
    public Board()
    {
        board = new Square[15][15];
    }
    
    public Square getSquare(int r, int c)
    {
        return board[r][c];
    }
}
