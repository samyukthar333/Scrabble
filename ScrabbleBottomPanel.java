import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * 
 * creates the bottom panel for the game board
 *
 * @author Richa Bavadekar
 * @version May 29, 2019
 * @author Period: 4
 * @author Assignment: Scrabble
 *
 * @author Sources: none
 */
public class ScrabbleBottomPanel extends JPanel
{
    private static final long serialVersionUID = 1L;

    private Game game;

    private JPanel bottomPanel;

    private ArrayList<Letter> exchangeLetters;

    private final Image checkwordbtn;

    private final Image passbtn;

    private final Image playbtn;

    private final Image exchangebtn;


    /**
     * constructor method
     * 
     * @param game
     *            the current game being played
     * @param panel
     *            the panel being used
     */
    public ScrabbleBottomPanel( Game game, JPanel panel )
    {
        super();
        checkwordbtn = getImage( "checkWord" );
        passbtn = getImage( "pass" );
        playbtn = getImage( "play" );
        exchangebtn = getImage( "exchange" );
        // System.out.println(" " + panel.getWidth() + " " + panel.getHeight());
        this.game = game;
        this.bottomPanel = panel;
        exchangeLetters = new ArrayList<Letter>();
        drawBottomPanel( game, this );
        panel.add( this );
    }


    /**
     * retrieves the image that is being used
     * 
     * @param bg
     *            background
     * @return Image image to insert in board
     */
    private Image getImage( String bg )
    {
        try
        {
            String file = "./images/" + bg + ".jpg";
            // System.out.println( "File: " + file );
            FileInputStream fis = new FileInputStream( file );
            BufferedImage myPicture = ImageIO.read( fis );
            Image dimg = myPicture.getScaledInstance( 40, 40, Image.SCALE_SMOOTH );
            return dimg;
        }
        catch ( Exception ex )
        {
            System.out.println( bg + "Error: " + ex );
            ex.printStackTrace();
        }
        return null;
    }


    /**
     * accessor method
     * 
     * @return exchangeLetters array list of letters to exchange
     */
    public ArrayList<Letter> getExchangeLetters()
    {
        return exchangeLetters;
    }


    /**
     * mutator method
     * 
     * @param exch
     *            array list of letters to set
     */
    public void setExchangeLetters( ArrayList<Letter> exch )
    {
        this.exchangeLetters = new ArrayList<Letter>( exch );
    }


    /**
     * acessor method
     * 
     * @return game current game
     */
    public Game getGame()
    {
        return game;
    }


    /**
     * accessor method
     * 
     * @return JPanel the current bottom pannel
     */
    public JPanel getBottomPanel()
    {
        return this.bottomPanel;
    }


    /**
     * draws the bottom panel
     * 
     */
    public void drawBottomPanel()
    {
        drawBottomPanel( game, this );
    }


    /**
     * resets the player after the turn is finished
     * 
     */
    public void resetPlayer()
    {
        boolean enable = true;
        if ( game.isComputer( game.getCurrentPlayer() ) )
            enable = false;

        JPanel jp = (JPanel)this.getComponent( 0 );
        jp.getDropTarget().setActive( enable );
        int[] intArray = new int[] { 2, 4, 5 };
        for ( int i = 0; i < intArray.length; i++ )
        {
            JButton jb = (JButton)this.getComponent( intArray[i] );
            jb.setEnabled( enable );
        }
        ScrabbleFrontEnd sfe = (ScrabbleFrontEnd)this.getParent().getParent();
        ScrabbleBoard sb = (ScrabbleBoard)( (JComponent)sfe.getComponent( 4 ) ).getComponent( 0 );

        sb.resetSquares();
        sb.redraw();
        sfe.repaintTop();
    }


    /**
     * 
     * draws the bottom panel
     * 
     * @param game
     *            the game currently being played
     * @param bottomPanel
     *            the bottom panel
     */
    public void drawBottomPanel( Game game, JPanel bottomPanel )
    {
        JPanel j = new JPanel();
        j.addMouseListener( UIUtilities.getMouseListener() );
        j.setTransferHandler( UIUtilities.getTransferHandlerForExchange() );
        Border border = BorderFactory.createLineBorder( Color.BLACK, 1 );
        j.setBorder( border );
        JLabel lbl = new JLabel();
        lbl.setText( "Drag letters you want to exchange on this label" );
        j.add( lbl );
        bottomPanel.add( j );

        JTextField f = new JTextField( 20 );
        JButton check = new JButton();
        check.setIcon( new ImageIcon( checkwordbtn ) );
        check.setMargin( new Insets( 0, 0, 0, 0 ) );
        check.setBorder( null );

        JButton play = new JButton();
        play.setIcon( new ImageIcon( playbtn ) );
        play.setMargin( new Insets( 0, 0, 0, 0 ) );
        play.setBorder( null );

        JButton exchange = new JButton();
        exchange.setIcon( new ImageIcon( exchangebtn ) );
        exchange.setMargin( new Insets( 0, 0, 0, 0 ) );
        exchange.setBorder( null );

        JButton pass = new JButton();
        pass.setIcon( new ImageIcon( passbtn ) );
        pass.setMargin( new Insets( 0, 0, 0, 0 ) );
        pass.setBorder( null );

        bottomPanel.add( f );
        bottomPanel.add( check );
        bottomPanel.add( play );
        bottomPanel.add( exchange );
        bottomPanel.add( pass );
        ActionListener exchangeListener = new ActionListener()
        {
            public void actionPerformed( ActionEvent e )
            {
                JButton btn = (JButton)e.getSource();
                ScrabbleBottomPanel slp = (ScrabbleBottomPanel)btn.getParent();
                // System.out.println( "IH MYLetter size is " +
                // slp.getExchangeLetters().size() );
                int esize = slp.getExchangeLetters().size();
                if ( esize != 3 )
                {
                    JOptionPane.showMessageDialog( null, "You can exchange only 3 words" );
                    return;
                }
                Game game = slp.getGame();
                game.exchange( slp.getExchangeLetters() );
                JPanel lp = slp.getBottomPanel();
                slp.setExchangeLetters( new ArrayList<Letter>() );
                JPanel jpl = (JPanel)slp.getComponent( 0 );
                int j = 0;
                for ( Component jc : jpl.getComponents() )
                {
                    if ( j != 0 )
                    {
                        // System.out.println( "Comp is " + jc );
                        jpl.remove( jc );
                    }
                    j++;
                }
                jpl.updateUI();
                Player player = null;
                Container jc = (Container)slp;
                ScrabbleLeftPanel slpp = null;
                while ( jc.getParent() != null )
                {
                    if ( jc instanceof ScrabbleFrontEnd )
                    {
                        slpp = (ScrabbleLeftPanel)jc.getComponent( 2 );
                        player = slpp.getPlayer();
                        break;
                    }
                    jc = jc.getParent();
                }
                if ( player == null )
                    return;

                ArrayList<Letter> myLetters = slpp.getMyLetters();

                for ( int k = 0; k < esize; k++ )
                {
                    Letter l = game.getBag().getRandomLetter();
                    player.getLetters().add( l );
                    myLetters.add( l );
                }
                if ( slpp != null )
                    slpp.drawLeftPanel();
                
                resetPlayer();
            }
        };
        exchange.addActionListener( exchangeListener );

        ActionListener al = new ActionListener()
        {
            public void actionPerformed( ActionEvent e )
            {
                JButton btn = (JButton)e.getSource();
                JPanel panel = (JPanel)btn.getParent();
                JTextField f = (JTextField)panel.getComponent( 1 );
                String text = f.getText();
                if ( text == null || text.trim().length() == 0 )
                {
                    JOptionPane.showMessageDialog( null, "Please enter a word to check" );
                    return;
                }
                String uText = text.toUpperCase();
                boolean bln = Words.isWord( uText );
                String message = "";
                if ( bln )
                    message = text + " is a valid word";
                else
                    message = text + " is not a word";

                JOptionPane.showMessageDialog( null, message );
            }
        };
        check.addActionListener( al );

        ActionListener playListener = new ActionListener()
        {
            public void actionPerformed( ActionEvent e )
            {
                JButton btn = (JButton)e.getSource();
                ScrabbleBottomPanel panel = (ScrabbleBottomPanel)btn.getParent();
                ScrabbleFrontEnd sfe = (ScrabbleFrontEnd)panel.getParent().getParent();
                ScrabbleBoard jp = (ScrabbleBoard)( (JComponent)sfe.getComponent( 4 ) )
                    .getComponent( 0 );
                Game game = jp.getGame();
                if ( game.isComputer( game.getCurrentPlayer() ) )
                {
                    ArrayList<Square> squares = ( (ComputerPlayer)game.getComputer() )
                        .findWord( game.getBoard() );
                    if ( squares == null )
                    {
                        ArrayList<Letter> letters = game.getComputer().getExchange();
                        game.exchange( letters );
                        JOptionPane.showMessageDialog( null, "Computer exchanged" );
                    }
                    else
                    {
                        int points = game.play( squares );
                        JOptionPane.showMessageDialog( null,
                            "Computer placed word for " + points + " points" );

                    }
                    resetPlayer();

                    return;
                }

                int j = 0;
                Container jc = (Container)btn;
                while ( jc.getParent() != null )
                {
                    // System.out.println( " " + j + " " +
                    // jc.getParent().getClass() );
                    jc = jc.getParent();
                    j++;
                }
                ArrayList<Square> squares = jp.getSquares();

                // System.out.println( "Squares: " + squares );
                if ( squares.size() == 0 )
                {
                    JOptionPane.showMessageDialog( null,
                        "Please move tiles to board to play" );
                    return;
                }
                // for ( Square s : squares )
                // {
                // System.out.println( "S is " + s );
                // }

                Player myPlayer = jp.getGame().getCurrentPlayer();
                int points = jp.getGame().play( squares );
                // System.out.println("Play done? " + isValid);
                // System.out.println("Player points are " +
                // myPlayer.getPoints());
                if ( points == -1 )
                {
                    JOptionPane.showMessageDialog( null, "Invalid Words. Try again" );
                }
                else
                {
                    JOptionPane.showMessageDialog( null, "You get " + points + " points" );
                    // sfe.repaintLeft();
                    sfe.refreshGame();
                }
            }
        };
        play.addActionListener( playListener );

        ActionListener passListener = new ActionListener()
        {
            public void actionPerformed( ActionEvent e )
            {

                resetPlayer();
                JButton btn = (JButton)e.getSource();
                ScrabbleBottomPanel panel = (ScrabbleBottomPanel)btn.getParent();
                ScrabbleFrontEnd sfe = (ScrabbleFrontEnd)panel.getParent().getParent();
                Game game = panel.getGame();
                game.pass();
                sfe.refreshGame();
            };
        };
        pass.addActionListener( passListener );
    }
}
