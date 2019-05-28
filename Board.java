
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;

/**
 * 
 *  Represents a board filled with Square objects
 *  
 *
 *  @author  Samyuktha, saanvi, richa
 *  @version May 25, 2019
 *  @author  Period: 4
 *  @author  Assignment: Scrabble
 *
 *  @author  Sources: none
 */
public class Board //does not work only for when letter has both up and side neighbors
{
    private Square[][] board;

    private ArrayList<Square> anchors;

    private boolean transposed;

    private BitSet[][] bitVectors;


    /**
     * initializes board to special squares
     */
    public Board()
    {

        board = new Square[15][15];
        for ( int i = 0; i < board.length; i++ )
            for ( int j = 0; j < board[0].length; j++ )
                board[i][j] = new Square( i, j );
        initBoard();
        transposed = false;
        bitVectors = new BitSet[15][15];
        anchors = new ArrayList<Square>();
        initBitSet();

    }


    /**
     * sets/resets bitVectors for all squares in board
     * 
     */
    private void initBitSet()
    {
        for ( int i = 0; i < bitVectors.length; i++ )
        {
            for ( int j = 0; j < bitVectors[0].length; j++ )
            {
                bitVectors[i][j] = new BitSet(26);
                if(board[i][j].isEmpty())
                {
                    if((!isValid(i-1, j) || board[i-1][j].isEmpty()) && (!isValid(i+1, j) || board[i+1][j].isEmpty()))
                    {
                       // System.out.println( board[i][j] + "pleaseeeeeee" );
                        bitVectors[i][j].set( 0, 25, true );
                    }
                    else
                    {
                        int num = i-1;
                        String s = "";
                        while ( num >= 0 && board[num][j].getLetter()!=null )
                        {
                           //System.out.println( "if it doesn't..." + s);
                            s = board[num][j].getLetter().getLetter() + s;
                            num--;
                        }
                        //System.out.println("s: " + s );
                        if(s.isEmpty()) //if empty square is above 
                        {
                            num = i+1;
                            while ( num <= 14 && board[num][j].getLetter() != null )
                            {
                                s = s + board[num][j].getLetter().getLetter();
                                num++;
                                
                            }
                            //System.out.println( "s1: " + s );
                            String tmp = new String(s);
                            String alphabet = "ABCDEFGHIJKLMOPQRSTUVWXYZ";
                            for(char c: alphabet.toCharArray())
                            {
                                //System.out.println("c: " + c);
                                s = new String(c+tmp);
                                if(Words.wordTrie.contains( s ))
                                {
                                     bitVectors[i][j].set(Character.getNumericValue( c ) - Character.getNumericValue( 'A' ), true);
                                }
                            }
                        }
                        else
                        {
                            //System.out.println( "goodnessaslfjkas" );
                            if(Words.wordTrie.getEndNode( s )!=null)
                            {
                                HashMap<Character, TrieNode> children = Words.wordTrie.getEndNode( s ).getChildren();
                                if(children!=null)
                                {
                                    for ( char c : children.keySet() )
                                    {
                                        //System.out.println("char: "+ c );
                                        bitVectors[i][j].set(Character.getNumericValue( c ) - Character.getNumericValue( 'A' ), true );
                                    }                         
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    /**
     * 
     * initializes board
     */
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


    /**
     * 
     * flips board horizontally
     */
    private void flipHorizontally()
    {
        for ( int i = 0; i < board.length; i++ )
            for ( int j = 0; j < board[0].length; j++ )
                board[i][board[0].length - 1 - j].setSpecial( board[i][j].getSpecial() );
    }


    /**
     * 
     * flips board vertically
     */
    private void flipVertically()
    {
        for ( int i = 0; i < board.length; i++ )
            for ( int j = 0; j < board[0].length; j++ )
                board[board.length - 1 - i][j].setSpecial( board[i][j].getSpecial() );
    }


    /**
     * 
     * transposes board clockwise
     */
    public void transposeBack()
    {
        Square[][] temp = new Square[15][15];
        for ( int i = 0; i < board.length; i++ )
        {
            for ( int j = 0; j < board[0].length; j++ )
            {
                temp[i][j] = board[board.length - 1 - j][i];
            }
        }
        board = temp;
        transposed = false;
    }


    /**
     * 
     * transposes board counterclockwise
     */
    public void transpose()
    {
        Square[][] temp = new Square[15][15];
        for ( int i = 0; i < board.length; i++ )
        {
            for ( int j = 0; j < board[0].length; j++ )
            {
                temp[i][j] = board[j][board[0].length - 1 - i];
            
            }
        }
        board = temp;
        transposed = true;
    }
    
    /**
     * 
     * returns a copy of board
     * @return copy
     */
    public Square[][] copy()
    {
        Square[][] copy = new Square[15][15];
        for ( int i = 0; i < board.length; i++ )
            for ( int j = 0; j < board[0].length; j++ )
            {
                copy[i][j] = new Square(i, j, board[i][j].getSpecial());
                copy[i][j].setLetter(board[i][j].getLetter());
            }
        return copy;
    }


    /**
     * 
     * returns square if coordinates are valid
     * 
     * @param r
     *            row
     * @param c
     *            col
     * @return square
     */
    public Square getSquare( int r, int c )
    {
        if ( isValid( r, c ) )
        {
            return board[r][c];
        }
        return null;
    }


    /**
     * 
     * get the anchors on board (for computer player use)
     * 
     * @return anchors on board
     */
    public ArrayList<Square> getAnchors()
    {
        return anchors;
    }


    /**
     * returns whether the coordinates are valid on the board
     * 
     * @param x
     *            row
     * @param y
     *            col
     * @return whether the coordinates are valid on the board
     */
    public boolean isValid( int x, int y )
    {
        return ( x >= 0 && x < 15 && y >= 0 && y < 15 );
    }
    
    /**
     * 
     * gets the bit vector
     * @param row
     * @param col
     * @return bitVectors[row][col]
     */
    public BitSet getBitVector(int row, int col)
    {
        return bitVectors[row][col];
    }


    /**
     * 
     * returns all squares next to given square that are occupied (have letters)
     * 
     * @param square
     *            to check
     * @return arraylist of occupied squares
     */
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


    /**
     * returns neighbors to the left and right of given square
     * 
     * @param square input square
     * @return right and left neighbors in an arraylist
     */
    public ArrayList<Square> getRightandLeft( Square square )
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
        return myOccNeighbors;
    }
    /**
     * 
     * cross checks 
     */
    public void fixAnchors()
    {
        for ( int i = 0; i < 15; i++ )
        {
            for ( int j = 0; j < 15; j++ )
            {
                Square square = board[i][j];
                if(!square.isEmpty())
                {
                    if(isValid(i, j-1) && board[i][j-1].isEmpty())
                    {
                        anchors.add( board[i][j-1] );
                    }
                }
            }
        }
    }

    //there are cases when this method may not work - jib

    /**
     * 
     * places a word on the board if possible and returns point value
     * 
     * @param squares input locations
     * @return number of points the word is worth, or -1 if not placeable
     */
    public int placeWord( ArrayList<Square> squares )
    {
        if(squares.isEmpty())
        {
            System.out.println("squares is empty");
            return -1;
        }
        Square[][] temp = copy();
        printBoard();
        squares = sortSquares( squares );
        initBitSet();
        //System.out.println( "t: " + transposed );
        for(Square s : squares)
        {
            addLetter(s.getLetter(), s.getRow(), s.getCol());
            BitSet set = bitVectors[s.getRow()][s.getCol()];
            int num = Character.getNumericValue(s.getLetter().getLetter()) - Character.getNumericValue( 'A' );
            System.out.println("" + num + set.get( num ));
            if(!set.get( num ))
            {
                //System.out.println("aikhkajdsfhakjlsdfhaldskjfhaskdfj");
                board = temp;
                return -1;
            }     
        }
        //System.out.println("gdfgk");
        int points = checkRow(squares);
        if(points == -1)
        {
            System.out.println("points wrong...");
            board = temp;
            return -1;
        }
        
        if(transposed)
            transposeBack();
        
        return points;
    }
    
    
    /**
     * 
     * finds the points of the word without placing it on the board
     * @param squares
     * @return points
     */
    public int findPointsWithoutPlacing( ArrayList<Square> squares )
    {
        Square[][] temp = copy();
        printBoard();
        squares = sortSquares( squares );
        initBitSet();
        //System.out.println( "t: " + transposed );
        for(Square s : squares)
        {
            addLetter(s.getLetter(), s.getRow(), s.getCol());
            BitSet set = bitVectors[s.getRow()][s.getCol()];
            int num = Character.getNumericValue(s.getLetter().getLetter()) - Character.getNumericValue( 'A' );
            //System.out.println("" + num + set.get( num ));
            if(!set.get( num ))
            {
                //System.out.println("aikhkajdsfhakjlsdfhaldskjfhaskdfj");
                board = temp;
                return -1;
            }     
        }
        //System.out.println("gdfgk");
        int points = checkRow(squares);
        if(points == -1)
        {
            System.out.println("points wrong...");
            board = temp;
            return -1;
        }
        
        if(transposed)
            transposeBack();
        
        board = temp;
        return points;
    }

    /**
     * 
     * sorts the squares
     * @param squares
     * @return squares
     */
    private ArrayList<Square> sortSquares( ArrayList<Square> squares )
    {
        //System.out.println( squares.size() );
        if(squares.size()==1)
        {
            //System.out.println( "one letter" );
            if(containsToporBottom(squares.get( 0 ).getRow(), squares.get( 0 ).getCol()))
            {
                if(getRightandLeft( squares.get( 0 )).size()==0)
                {
                    transpose();
                    squares = transposeSquares( squares );
                }
                printBoard();
            }
            return squares;
        }
        boolean x = true, y = true;
        int sameX = squares.get( 0 ).getRow(), sameY = squares.get( 0 ).getCol();
        for ( Square s : squares )
        {
            if ( s.getRow() != sameX )
            {
                x = false;
            }
            if ( s.getCol() != sameY )
            {
                y = false;
            }
        }
        if ( !( x || y ) )
        {
            return null;
        }
        else if ( x ) // rows are the same -- sort by column
        {
            squares = sortbyY( squares );
        }
        else if ( y ) // columns are the same -- sort by row
        {
            squares = sortbyX( squares );
            transpose();
            printBoard();
            squares = transposeSquares( squares );
        }
        return squares;

    }
    
    /**
     * 
     * checks if it is transposed
     * @return transposed
     */
    public boolean isTransposed()
    {
        return transposed;
    }

    /**
     * 
     * checks if it has a top or bottom letter
     * @param i
     * @param j
     * @return (isValid(i-1, j) && !board[i-1][j].isEmpty()) || (isValid(i+1, j) && !board[i+1][j].isEmpty())
     */
    private boolean containsToporBottom(int i, int j)
    {
        return (isValid(i-1, j) && !board[i-1][j].isEmpty()) || (isValid(i+1, j) && !board[i+1][j].isEmpty());
    }

    /**
     * 
     * checks the row
     * @param squares
     * @return -1
     */
    private int checkRow( ArrayList<Square> squares)
    {
        int row = squares.get(0).getRow();
        int start = 0;
        for ( int i = squares.get( 0 ).getCol(); i > 0; i-- )
        {
            if ( board[row][i].getLetter() == null )
            {
                start = i + 1;
                break;
            }
        }
        boolean connected = false;
        String word = "";
        int points = 0;
        int pointNum = 1;
        for ( int i = start; i <= 14; i++ )
        {
            Square s = board[row][i];
            if ( s.getLetter() == null )
            {
                break;
            }
            char c = s.getLetter().getLetter();
            word += c;
            if(!squares.contains( s ) || containsToporBottom(s.getRow(), s.getCol()))
                connected = true;
            
            //calculating points stuff
            int temp = s.getPoints();
            if(temp == -10)
            {
                temp = s.getLetter().getPointValue();
                pointNum = 2;
            }
            else if(temp == -20)
            {
                temp = s.getLetter().getPointValue();
                pointNum = 3;
            }
            points += temp;
            s.removeSpecial();
            
        }
        points = points*pointNum;
        //System.out.println("t: " + transposed );
        if(connected && (squares.size()==1 || Words.isWord( word )))
        {
            return points;
        }
        return -1;
    }


    /**
     * 
     * transposes the squares' locations clockwise
     * @param squares squares to transpose
     * @return transposed squares
     */
    public ArrayList<Square> transposeSquares( ArrayList<Square> squares )
    {
        ArrayList<Square> squares2 = new ArrayList<Square>();
        for ( Square s : squares )
        {
            int tempX = s.getRow();
            s.setRow( board[0].length - 1 - s.getCol() );
            s.setCol( tempX );
            squares2.add( s );
            
            
        }

        return squares2;
    }


    /**
     * 
     * transposes the squares' locations clockwise
     * @param squares squares to transpose
     * @return transposed squares
     */
    public ArrayList<Square> transposeSquaresBack( ArrayList<Square> squares )
    {
        for ( Square s : squares )
        {
            int tempX = s.getRow();
            s.setRow( s.getCol() );
            s.setCol( board[0].length - 1 - tempX );
            
        }

        return squares;
    }


    /**
     * main sort method to sort by row
     * 
     * @param myArray
     *            arraylist to sort
     * @return the sorted arraylist *pretty useless tbh
     */
    private ArrayList<Square> sortbyX( ArrayList<Square> myArray )
    {
        quickSortX( myArray, 0, myArray.size() - 1 );
        return myArray;
    }


    /**
     * 
     * helper method to sort array by row quicksort algorithm
     * 
     * @param myArray
     *            array to sort
     * @param low
     *            low bound
     * @param high
     *            high bound
     * @return sorted array
     */
    private ArrayList<Square> quickSortX( ArrayList<Square> myArray, int low, int high )
    {
        if ( low < high )
        {
            int partLoc = partitionArrayX( myArray, low, high );

            quickSortX( myArray, low, partLoc - 1 );
            quickSortX( myArray, partLoc + 1, high );
        }
        return myArray;
    }


    /**
     * 
     * another helper method for quicksort to sort by row
     * 
     * @param myArray
     *            arraylist
     * @param low
     *            low bound
     * @param high
     *            high bound
     * @return partitioned array
     */
    private int partitionArrayX( ArrayList<Square> myArray, int low, int high )
    {
        int pivot = myArray.get( high ).getRow();
        int small = low - 1;
        for ( int i = low; i < high; i++ )
        {
            if ( myArray.get( i ).getRow() < pivot )
            {
                small++;
                Square temp = myArray.get( small );
                myArray.set( small, myArray.get( i ) );
                myArray.set( i, temp );

            }

        }
        Square temp = myArray.get( small + 1 );
        myArray.set( small + 1, myArray.get( high ) );
        myArray.set( high, temp );
        return small + 1;
    }


    /**
     * main sort method for sorting by column
     * 
     * @param myArray
     *            arraylist to sort
     * @return the sorted arraylist *pretty useless tbh
     */
    private ArrayList<Square> sortbyY( ArrayList<Square> myArray )
    {
        quickSortY( myArray, 0, myArray.size() - 1 );
        return myArray;
    }


    /**
     * 
     * helper method to sort array based on column quicksort algorithm
     * 
     * @param myArray
     *            array to sort
     * @param low
     *            low bound
     * @param high
     *            high bound
     * @return sorted array
     */
    private ArrayList<Square> quickSortY( ArrayList<Square> myArray, int low, int high )
    {
        if ( low < high )
        {
            int partLoc = partitionArrayY( myArray, low, high );

            quickSortY( myArray, low, partLoc - 1 );
            quickSortY( myArray, partLoc + 1, high );
        }
        return myArray;
    }


    /**
     * 
     * another helper method for quicksort based on col
     * 
     * @param myArray
     *            arraylist
     * @param low
     *            low bound
     * @param high
     *            high bound
     * @return partitioned array
     */
    private int partitionArrayY( ArrayList<Square> myArray, int low, int high )
    {
        int pivot = myArray.get( high ).getCol();
        int small = low - 1;
        for ( int i = low; i < high; i++ )
        {
            if ( myArray.get( i ).getCol() < pivot )
            {
                small++;
                Square temp = myArray.get( small );
                myArray.set( small, myArray.get( i ) );
                myArray.set( i, temp );

            }

        }
        Square temp = myArray.get( small + 1 );
        myArray.set( small + 1, myArray.get( high ) );
        myArray.set( high, temp );
        return small + 1;
    }


    /**
     * 
     * adds letter to board at specific location
     * 
     * @param letter
     *            letter to add
     * @param x
     *            row
     * @param y
     *            col
     * @return whether possible to add
     */
    public boolean addLetter( Letter letter, int x, int y )
    {
        if ( !isValid( x, y ) || board[x][y].getLetter() != null )
            return false;
        board[x][y].setLetter( letter );
        return true;
    }


    /**
     * 
     * prints letters of board
     */
    public void printBoard()
    {
        for ( int i = 0; i < board.length; i++ )
        {
            for ( int j = 0; j < board[0].length; j++ )
            {
                System.out.print( board[i][j] + " " );
            }
            System.out.println();
        }
    }


    /**
     * for testing prints special numbers for each square
     * 
     */
    public void printBoardSp()
    {
        for ( int i = 0; i < board.length; i++ )
        {
            for ( int j = 0; j < board[0].length; j++ )
            {
                System.out.print( board[i][j].getSpecial() + " " );
            }
            System.out.println();
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


    /**
     * 
     * for testing purposes
     * 
     * @param args
     *            not used
     */
    public static void main( String[] args )
    {
        Board board = new Board();
        board.getBoard()[2][6].setLetter( new Letter( 'D' ) );
        board.getBoard()[3][6].setLetter( new Letter( 'A' ) );
        board.getBoard()[4][6].setLetter( new Letter( 'D' ) );
        board.printBoardSp();
        
        ArrayList<Square> input = new ArrayList<Square>();
        input.add( new Square( new Letter( 'B' ), 2, 2 ) );
        input.add( new Square( new Letter( 'O' ), 2, 3 ) );
        input.add( new Square( new Letter( 'A' ), 2, 4 ) );
        input.add( new Square( new Letter( 'R' ), 2, 5 ) );
        int points = board.placeWord( input );
        board.printBoard();
        System.out.println( points );
        
        input = new ArrayList<Square>();
        input.add( new Square( new Letter( 'O' ), 3, 2 ) );
        input.add( new Square( new Letter( 'A' ), 4, 2 ) );
        input.add( new Square( new Letter( 'R' ), 5, 2 ) );
        points = board.placeWord( input );
        board.printBoard();
        System.out.println( points );
        
        input = new ArrayList<Square>();
        input.add( new Square( new Letter( 'O' ), 4, 7 ) );
        input.add( new Square( new Letter( 'L' ), 4, 8 ) );
        input.add( new Square( new Letter( 'L' ), 4, 9 ) );
        points = board.placeWord( input );
        board.printBoard();
        System.out.println( points );
        
        input = new ArrayList<Square>();
        input.add( new Square( new Letter( 'U' ), 5, 9 ) );
        input.add( new Square( new Letter( 'M' ), 6, 9 ) );
        input.add( new Square( new Letter( 'B' ), 7, 9 ) );
        input.add( new Square( new Letter( 'E' ), 8, 9 ) );
        input.add( new Square( new Letter( 'R' ), 9, 9 ) );
        points = board.placeWord( input );
        board.printBoard();
        System.out.println( points );
        
        input = new ArrayList<Square>();
        input.add( new Square( new Letter( 'D' ), 6, 2 ) );
        //input.add( new Square( new Letter( 'S' ), 7, 2 ) );
        points = board.placeWord( input );
        board.printBoard();
        System.out.println( points );
        
        input = new ArrayList<Square>();
        input.add( new Square( new Letter( 'S' ), 4, 10 ) );
        points = board.placeWord( input );
        board.printBoard();
        System.out.println( points );
        
        input = new ArrayList<Square>();
        input.add( new Square( new Letter( 'R' ), 3, 3 ) );
        points = board.placeWord( input );
        board.printBoard();
        System.out.println( points );
        
        input = new ArrayList<Square>();
        input.add( new Square( new Letter( 'N' ), 5, 7 ) );
        points = board.placeWord( input );
        board.printBoard();
        System.out.println( points );
        
        board.printBoardSp();
//        
//        Board board = new Board();
//        board.addLetter( new Letter('A'), 3, 5 );
//        board.addLetter( new Letter('T'), 3, 6 );
//        board.printBoard();
//        ArrayList<Square> input = new ArrayList<Square>();
//        input.add( new Square( new Letter( 'B' ), 3, 3 ) );
//        input.add( new Square( new Letter( 'R' ), 3, 4 ) );
//        input.add( new Square( new Letter( 'T' ), 3, 7 ) );
//        input.add( new Square( new Letter( 'Y' ), 3, 8 ) );
//        int points = board.placeWord( input );
//        board.printBoard();
//        System.out.println( points );
        
        // testing purposes
    }

}
