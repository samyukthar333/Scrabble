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


/**
 * 
 * constructs the left panel
 *
 * @author Richa Bavadekar
 * @version May 29, 2019
 * @author Period: 4
 * @author Assignment: Scrabble
 *
 * @author Sources: none
 */
public class ScrabbleLeftPanel extends JPanel
{
    private static final long serialVersionUID = 1L;

    private ArrayList<Letter> myLetters;

    private ArrayList<Letter> onBoardLetters;

    private Game game;

    private Player player1;

    private JPanel bottomPanel;


    /**
     * 
     * gets button
     * 
     * @param c
     *            buttons character
     * @param p
     *            int
     * @return JLabel current label
     */
    private static JLabel getTButton( char c, int p )
    {
        try
        {
            String s = new String( "" + c );
            String fileName = "./images/" + s + ".jpg";
            BufferedImage myPicture = ImageIO.read( new File( fileName ) );
            Image dimg = myPicture.getScaledInstance( 32, 32, Image.SCALE_SMOOTH );
            JLabel picLabel = new JLabel( new ImageIcon( dimg ) );
            picLabel.setSize( 32, 32 );
            picLabel.setToolTipText( Character.toString( c ) );
            return picLabel;
        }
        catch ( Exception ex )
        {
        }

        return null;
    }


    /**
     * 
     * repaints left panel
     */
    public void repaintLeft()
    {
        drawLeftPanel();
    }


    /**
     * constructor method
     * 
     * @param game
     *            the game that is currently being played
     * @param bottomPanel
     *            the bottom panel of the game
     * @param player1
     *            the player that is playing the game
     */
    public ScrabbleLeftPanel( Game game, JPanel bottomPanel, Player player1 )
    {
        super();
        this.game = game;
        this.bottomPanel = bottomPanel;
        this.player1 = player1;
        myLetters = new ArrayList<Letter>( player1.getLetters().getLetters() );
        System.out.println( "MYLetterSize " + myLetters.size() );
        onBoardLetters = new ArrayList<Letter>();
        System.out.println( "Player 1 is at " + player1 );
        drawLeftPanel( game, this, player1 );
        bottomPanel.add( this );
    }


    /**
     * 
     * returns player
     * 
     * @return Player player 1
     */
    public Player getPlayer()
    {
        // System.out.println( "Player 1 is at " + player1 );
        return player1;
    }


    /**
     * 
     * returns the player letters
     * 
     * @return ArrayList<Letter> my letters
     */
    public ArrayList<Letter> getMyLetters()
    {
        return myLetters;
    }


    /**
     * 
     * draws left panel
     */
    public void drawLeftPanel()
    {
        drawLeftPanel( game, this, player1 );
    }


    /**
     * 
     * draws left panel
     * 
     * @param game
     *            current game
     * @param bottomPanel
     *            bottom panel
     * @param player1
     *            current player
     */
    public void drawLeftPanel( Game game, JPanel bottomPanel, Player player1 )
    {
        // System.out.println( "RICHA " + bottomPanel.getLayout() );

        for ( Component c : bottomPanel.getComponents() )
            bottomPanel.remove( c );
        bottomPanel.setLayout( new BoxLayout( bottomPanel, BoxLayout.Y_AXIS ) );
        bottomPanel.addMouseListener( UIUtilities.getMouseListener() );
        TransferHandler tfhP = UIUtilities.getTransferHandlerForLeftPanel();
        bottomPanel.setTransferHandler( tfhP );
        // JLabel user = new JLabel("<html>\tHuman Player</html>");
        JLabel user = new JLabel( "                                                        " );
        user.setAlignmentX( Component.CENTER_ALIGNMENT );
        // JLabel points = new JLabel("Total Points: " + player1.getPoints());
        JLabel points = new JLabel( "<html><br/><br/></html" );
        points.setAlignmentX( Component.CENTER_ALIGNMENT );
        bottomPanel.add( user );
        bottomPanel.add( points );
        bottomPanel.add( Box.createHorizontalGlue() );
        /*
         * try{ BufferedImage myPicture = ImageIO.read(new
         * File("./images/A.jpeg")); Image dimg =
         * myPicture.getScaledInstance(20, 20, Image.SCALE_SMOOTH); JLabel
         * picLabel = new JLabel(new ImageIcon(dimg)); picLabel.setSize(20,20);
         * bottomPanel.add(picLabel); } catch(Exception ex){ }
         */

        PlayerLetters pl = player1.getLetters();
        ArrayList<Letter> letters = pl.getLetters();
        // System.out.println(letters);
        if ( player1.getPoints() == 0 && myLetters.size() == 0 )
        {
            LetterBag myBag = game.getBag();
            for ( int i = 0; i < 7; i++ )
            {
                Letter l = myBag.getRandomLetter();
                pl.add( l );
            }
            myLetters = new ArrayList<Letter>( letters );
        }

        myLetters = new ArrayList<Letter>( letters );
        System.out.println( "MYLetter size is " + myLetters.size() );

        int i = 0;
        for ( Letter l : myLetters )
        {
            char c = l.getLetter();
            int p = l.getPointValue();
            JLabel jb = getTButton( c, p );
            MouseListener ml = UIUtilities.getMouseListenerForLabelInLeftPanel();
            jb.addMouseListener( ml );
            // TransferHandler tfh = UIUtilities.getTransferHandler();
            TransferHandler tfh = UIUtilities.getTransferHandlerForLabel();
            jb.setTransferHandler( tfh );
            jb.getDropTarget().setActive( false );
            bottomPanel.add( jb );
        }

    }
}
