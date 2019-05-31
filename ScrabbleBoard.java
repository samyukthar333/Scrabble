import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.io.FileInputStream;


/**
 *
 * creates and draws the board (the center panel of the screen)
 *
 * @author Richa Bavadekar
 * @version May 29, 2019
 * @author Period: 4
 * @author Assignment: Scrabble
 *
 * @author Sources: none
 */
public class ScrabbleBoard extends JPanel
{
    private static final long serialVersionUID = 1L;

    private Game game;

    private JPanel centerPanel;

    private ArrayList<Square> squares;

    private final Image threeword;

    private final Image twoword;

    private final Image threeletter;

    private final Image twoletter;


    /**
     * constructor
     * 
     * @param game
     *            current game
     * @param centerPanel
     *            current panel
     */
    public ScrabbleBoard( Game game, JPanel centerPanel )
    {
        super();
        this.game = game;
        this.centerPanel = centerPanel;
        this.squares = new ArrayList<Square>();
        threeword = getImage( "threeword" );
        threeletter = getImage( "threeletter" );
        twoword = getImage( "twoword" );
        twoletter = getImage( "twoletter" );
        drawBoard( game, this );
        centerPanel.add( this );
    }


    /**
     *
     * gets squares
     * 
     * @return squares
     */
    public ArrayList<Square> getSquares()
    {
        return squares;
    }


    /**
     * reset squares
     *
     */
    public void resetSquares()
    {
        squares = new ArrayList<Square>();
    }


    /**
     *
     * gets the panel
     * 
     * @param img
     *            image being used
     * @return jPanel this panel
     */
    private JPanel getPanel( final Image img )
    {
        // final Image i = img;
        JPanel jP = new JPanel()
        {
            @Override
            protected void paintComponent( Graphics g )
            {
                super.paintComponent( g );
                if ( img != null )
                    ;
                g.drawImage( img, 0, 0, null );
            }
        };
        return jP;
    }


    /**
     *
     * gets the image used for the game board
     * 
     * @param bg
     *            the background
     * @return Image current image
     */
    private Image getImage( String bg )
    {
        try
        {
            String file = "./images/" + bg + ".jpg";
            //System.out.println( "File: " + file );
            FileInputStream fis = new FileInputStream( file );
            // BufferedImage myPicture = ImageIO.read(new File("./images/" + bg
            // + ".jpeg"));
            BufferedImage myPicture = ImageIO.read( fis );
            Image dimg = myPicture.getScaledInstance( 40, 40, Image.SCALE_SMOOTH );
            return dimg;
        }
        catch ( Exception ex )
        {
            //System.out.println( bg + "Error: " + ex );
            ex.printStackTrace();
        }
        return null;
    }


    /**
     *
     * draws the board
     */
    public void drawBoard()
    {
        drawBoard( game, this );
    }


    /**
     *
     * gets game
     * 
     * @return this.game
     */
    public Game getGame()
    {
        return this.game;
    }


    /**
     *
     * draws the board
     * 
     * @param game
     *            the current game being played
     * @param centerPanel
     *            current panel
     */
    public void drawBoard( Game game, JPanel centerPanel )
    {
        centerPanel.setLayout( new GridBagLayout() );
        int height = this.centerPanel.getHeight();
        int weight = this.centerPanel.getWidth();
        int scrDim = 0;
        if ( height > weight )
            scrDim = weight;
        else
            scrDim = height;

        scrDim = ( scrDim / 100 ) * 100;
       // System.out.println( "Dimensions " + scrDim );
        JPanel panel = new JPanel();
        panel.setLayout( new GridLayout( 15, 15 ) );
        // panel.setBackground(Color.BLACK);
        panel.setPreferredSize( new Dimension( scrDim, scrDim ) );
        centerPanel.add( panel );
        Border border = BorderFactory.createLineBorder( Color.BLACK, 1 );
        Board board = game.getBoard();
        Square[][] squares = board.getBoard();

        for ( int i = 0; i < squares.length; i++ )
        {
            for ( int j = 0; j < squares[0].length; j++ )
            {

                Square s = squares[i][j];
                int special = squares[i][j].getSpecial();
                Image img = null;
                if ( special == 4 )
                {
                    // img = getImage("threeword");
                    img = threeword;
                }
                if ( special == 3 )
                {
                    // img = getImage("twoword");
                    img = twoword;
                }
                if ( special == 2 )
                {
                    // img = getImage("threeletter");
                    img = threeletter;
                }
                if ( special == 1 )
                {
                    // img = getImage("twoletter");
                    img = twoletter;
                }

                // JLabel jP = new JLabel("", SwingConstants.CENTER);
                // JPanel jP = new JPanel();
                JPanel jP = getPanel( img );
                jP.addMouseListener( UIUtilities.getMouseListener() );
                // jP.setTransferHandler(UIUtilities.getTransferHandler());
                jP.setTransferHandler( UIUtilities.getTransferHandlerForBoard() );
                jP.setBorder( border );
                if ( s.getLetter() != null )
                {
                    JLabel jl = new JLabel();
                    jl.setText( s.toString() );
                }

                panel.add( jP );
            }
        }

    }


    /**
     * 
     * repaints the board
     */
    public void redraw()
    {
        JPanel jp = (JPanel)this.getComponent( 0 );
        Board board = game.getBoard();
        Square[][] squares = board.getBoard();
        int k = 0;
        for ( int i = 0; i < squares.length; i++ )
        {
            for ( int j = 0; j < squares[0].length; j++ )
            {
                Square s = squares[i][j];
                if ( s.getLetter() != null )
                {
                   // System.out.println( k + "ME " + s.toString() );
                    String data = s.toString();
                    String file = "./images/" + data + ".jpg";
                   // System.out.println( "File: " + file );
                    java.net.URL imageURL = UIUtilities.class.getResource( file );
                    ImageIcon ii = new ImageIcon( imageURL );
                    Image iii = ii.getImage();
                    Image newi = iii.getScaledInstance( 40, 40, java.awt.Image.SCALE_SMOOTH );
                    JLabel jb = new JLabel();
                    jb.setBounds( 0, 0, 40, 40 );
                    jb.setToolTipText( data );
                    jb.setIcon( new ImageIcon( newi ) );

                    ( (JPanel)( jp.getComponent( k ) ) ).add( jb );
                }
                k++;
            }
        }
    }
}
