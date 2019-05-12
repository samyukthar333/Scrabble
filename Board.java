import java.util.ArrayList;


public class Board
{
    private Square[][] board;

    private ArrayList<Square> placeableLocs;


    public Board()
    {

        board = new Square[15][15];
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


    private void flipHorizontally()
    {
        for ( int i = 0; i < board.length; i++ )
            for ( int j = 0; j < board[0].length; j++ )
                board[i][board[0].length - 1 - j].setSpecial( board[i][j].getSpecial() );
    }


    private void flipVertically()
    {
        for ( int i = 0; i < board.length; i++ )
            for ( int j = 0; j < board[0].length; j++ )
                board[board.length - 1 - i][j].setSpecial( board[i][j].getSpecial() );
    }


    public Square getSquare( int r, int c )
    {
        if ( isValid( r, c ) )
        {
            return board[r][c];
        }
        return null;
    }


    public ArrayList<Square> getPlaceableLocs()
    {
        fixPlaceableLocs();
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
                int number = getOccupiedNeighbors( square ).size();
                if ( number == 0 || number == 1 )
                {
                    placeableLocs.add( square );
                }
                else if ( number == 2 )
                {
                    // if theres diagonal squares next to the location, then we
                    // are not considering for now
                    if ( getSquare( i - 1, j ) != null && getSquare( i + 1, j ) != null )
                    {
                        placeableLocs.add( square );
                    }
                    else if ( getSquare( i, j - 1 ) != null && getSquare( i, j + 1 ) != null )
                    {
                        placeableLocs.add( square );
                    }
                }
            }
        }

    }


    private ArrayList<Integer> sort( ArrayList<Integer> myArray )
    {
        quickSort( myArray, 0, myArray.size() - 1 );
        return myArray;
    }


    private ArrayList<Integer> quickSort( ArrayList<Integer> myArray, int low, int high )
    {
        if ( low < high )
        {
            int partLoc = partitionArray( myArray, low, high );

            quickSort( myArray, low, partLoc - 1 );
            quickSort( myArray, partLoc + 1, high );
        }
        return myArray;
    }


    private int partitionArray( ArrayList<Integer> myArray, int low, int high )
    {
        int pivot = myArray.get( high );
        int small = low - 1;
        for ( int i = low; i < high; i++ )
        {
            if ( myArray.get( i ) > pivot )
            {
                small++;
                int temp = myArray.get( small );
                myArray.set( small, myArray.get( i ) );
                myArray.set( i, temp );

            }

        }
        int temp = myArray.get( small + 1 );
        myArray.set( small + 1, myArray.get( high ) );
        myArray.set( high, temp );
        return small + 1;
    }


    /**
     * places word if possible. If not possible returns -1, else number of points the play is worth
     */
    public int placeWord( ArrayList<Square> input, Square start )
    {
        Square[][] temp = board;
        ArrayList<Integer> rowVals = new ArrayList<Integer>(); 
        ArrayList<Integer> columnVals = new ArrayList<Integer>();
        for (Square square : input) {
            rowVals.add( square.getRow() );
            columnVals.add( square.getCol() );
            if(!temp[square.getRow()][square.getCol()].isEmpty())
            {
                return -1;
            }
            board[square.getRow()][square.getCol()].setLetter( square.getLetter() );
        }
        int same = rowVals.get( 0 );
        boolean isRow = true, isCol = true; //if true, then is a column word else row word \\ temporary initialization
        for (int x = 1; x < rowVals.size(); x++) // checks if all the row values are the same
        {
            if(rowVals.get( x )!=same)
            {
                isRow = false;
                break;
            }
        }
        same = columnVals.get( 0 );
        for (int y = 1; y < columnVals.size(); y++)  // checks if all the column values are the same
        {
            if(columnVals.get( y )!=same)
            {
                isCol = false;
                break;
            }
        }
        int points = 0;
        if(isRow) //if row word, sent to private helper method
        {
            same = rowVals.get( 0 );
            columnVals = sort(columnVals);
            points = placeRow(columnVals, same);
            
        }
        else if(isCol) //if column word, sent to private helper method
        {
            same = columnVals.get( 0 );
            rowVals = sort(rowVals);
            points = placeColumn(rowVals, same);

        }
        if(points == -1)
        {
            board = temp;
        }
        return points;
    }

    
    /**
     *
     * returns the number of points 
     * @param temp
     * @param vals the column values or row values of indices
     * @param val the row value or the column value
     * @param direction direction of the word (true for right and false for down)
     * @param word the word itself
     * @return points the number of points the word is worth
     */
    private int getPoints(ArrayList<Integer> vals, int val, boolean direction) //true for right, false for down
    {
        
        int points = 0;
        int num = 1; //double word or triple word to multiply at the end
        for(int i : vals)
        {
            Square s;
            int p = 0;
            if(direction) //if horizontal word
            {
                s = board[val][i];
                p = s.getPoints();
            }
            else
            {
                s = board[i][val];
                p = s.getPoints();
            }
            if(p == -10) //double word
            {
                num*=2;
                p = s.getLetter().getPointValue();
            }
            else if(p == -20) //triple word
            {
                num*=3;
                p = s.getLetter().getPointValue();
            }
            points += p;
            s.removeSpecial();
                
        }
        points*=num;
        return points;
    }
    private int placeRow(ArrayList<Integer> columnVals, int row)
    {
        ArrayList<Integer> tempColVals = columnVals;
        ArrayList<Integer> letters = new ArrayList<Integer>();
        int start = 0;
        for(int i = columnVals.get( 0 ); i>=0; i--)
        {
            if(board[row][i].isEmpty())
            {
                start = i+1;
                break;
            }
        }
        String word = "";
        for(Integer i = start; i<=14; i++)
        {
            if(board[row][i].isEmpty())
            {
                break;
            }
            word += board[row][i].getLetter().getLetter();
            letters.add( (int) i );
            columnVals.remove( i );
        }
        
        if(!columnVals.isEmpty() || !Words.isWord( word ))
        {
            return -1;
        }
        
        for(int i:tempColVals)
        {
            if(!checkColumn(row, i))
                return -1;
        }
        int points = getPoints(letters, row, true);
        return points;
    }
    
    private boolean checkColumn( int row, int col)
    {
        int start = 0;
        for(int i = row; i>=0; i--)
        {
            if(board[i][col].isEmpty())
            {
                start = i+1;
                break;
            }
        }
        String word = "";
        for(int i = start; i<=14; i++)
        {
            if(board[i][col].isEmpty())
            {
                break;
            }
            word += board[i][col].getLetter().getLetter();
        }
        return Words.isWord( word );
    }
    private boolean checkRow( int row, int col)
    {
        int start = 0;
        for(int i = col; i>0; i--)
        {
            if(board[row][i].getLetter()==null)
            {
                start = i+1;
                break;
            }
        }
        String word = "";
        for(int i = start; i<=14; i++)
        {
            if(board[row][i].getLetter()==null)
            {
                break;
            }
            word += board[row][i].getLetter().getLetter();
        }
        return Words.isWord( word );
    }
    
    private int placeColumn(ArrayList<Integer> rowVals, int col)
    {
        ArrayList<Integer> letters = new ArrayList<Integer>();
        ArrayList<Integer> tempRowVals = rowVals;
        int start = 0;
        for(int i = rowVals.get( 0 ); i>0; i--)
        {
            if(board[i][col].getLetter()==null)
            {
                start = i+1;
                break;
            }
        }
        String word = "";
        for(int i = start; i<=14; i++)
        {
            if(board[i][col].getLetter()==null)
            {
                break;
            }
            word += board[i][col].getLetter().getLetter();
            letters.add( i);
            rowVals.remove( i );
        }
        if(!rowVals.isEmpty() || !Words.isWord( word ))
        {
            return -1;
        }
        
        for(Integer i:tempRowVals)
        {
            if(!checkRow(i, col))
                return -1;
        }
        
        int points = getPoints(letters, col, false);
        return points;
    }

    public boolean addLetter( Letter letter, int x, int y )
    {
        if (!isValid(x,y) || board[x][y].getLetter() != null )
            return false;
        board[x][y].setLetter( letter );
        return true;
    }
    
    public void printBoard()
    {
        for(int i = 0; i<board.length; i++)
        {
            for(int j = 0; j<board[0].length; j++)
            {
                System.out.print(board[i][j] + " ");
            }
            System.out.println( );
        }
    }
    
    /**
     * returns board for testing purposes
     * 
     * @return board
     */
    public Square[][] getBoard()
    {
        return board;
    }
    
    public static void main( String[] args )
    {
         Board board = new Board();
         board.getBoard()[2][6].setLetter(new Letter('D'));
         board.getBoard()[3][6].setLetter(new Letter('A'));
         board.getBoard()[4][6].setLetter(new Letter('D'));
         ArrayList<Square> input = new ArrayList<Square>();
         input.add( new Square( new Letter('B'), 2, 2 ) );
         input.add( new Square( new Letter('O'), 2, 3 ) );
         input.add( new Square( new Letter('A'), 2, 4 ));
         input.add( new Square( new Letter('R'), 2, 5 ) );
         int points = board.placeWord( input, board.getBoard()[2][2] );
         board.printBoard();
         System.out.println( points );
        // testing purposes
    }


}
