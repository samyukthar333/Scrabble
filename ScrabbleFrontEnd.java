import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;


/**
 * Driver class of this project.
 *
 * @author Richa Bavadekar
 * @version May 22, 2019
 * @author Period: 4
 * @author Assignment: Scrabble
 *
 * @author Sources: none
 */
public class ScrabbleFrontEnd extends JPanel
{
    private static final long serialVersionUID = 1L;

    private String frameName = "Scrabble";

    private JFrame frame;

    private JPanel mainPanel;

    private Game game;

    Rectangle dim = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();


    /**
     * calculates a specific percent of any dimension (helper method to place
     * labels, buttons, etc)
     * 
     * @param w_percent
     *            width percentage
     * @param h_percent
     *            height percentage
     * 
     * @return new Dimension a percent of the old dimension
     */
    private Dimension getPercent( int w_percent, int h_percent )
    {
        int width = ( dim.width * w_percent ) / 100;
        int height = ( dim.height * h_percent ) / 100;
        return new Dimension( width, height );
    }


    /**
     * initialzes the frame
     */
    private void initFrame()
    {
        frame = new JFrame( this.frameName );
        frame.setSize( dim.width, dim.height );
        frame.setPreferredSize( new Dimension( dim.width, dim.height ) );
        frame.setResizable( false );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }


    /**
     * initializes right panel
     * 
     * @return jp right Jpanel
     */
    private JPanel getRightPanel()
    {
        Border border = BorderFactory.createLineBorder( Color.BLACK, 1 );
        JPanel jp = new JPanel();
        jp.setPreferredSize( getPercent( 10, 20 ) );
        ScrabbleRightPanel.drawRightPanel( game, jp, game.getComputer() );
        return jp;
    }


    /**
     * initializes left panel
     * 
     * @return slbp left Jpanel
     */
    private JPanel getLeftPanel()
    {
        Border border = BorderFactory.createLineBorder( Color.BLACK, 1 );
        JPanel jp = new JPanel();
        jp.setPreferredSize( getPercent( 10, 20 ) );
        ScrabbleLeftPanel sblp = new ScrabbleLeftPanel( game, jp, game.getHuman() );
        return sblp;
    }


    /**
     * initializes bottom panel
     * 
     * @return jp bottom Jpanel
     */
    private JPanel getBottomPanel()
    {
        Border border = BorderFactory.createLineBorder( Color.BLACK, 1 );
        JPanel jp = new JPanel();
        jp.setPreferredSize( getPercent( 10, 10 ) );
        jp.setSize( getPercent( 10, 10 ).width, getPercent( 10, 10 ).height );
        ScrabbleBottomPanel sbp = new ScrabbleBottomPanel( game, jp );
        return jp;
    }


    /**
     * repaints the middle of the screen
     * 
     */
    public void refreshGame()
    {
        repaintTop();
        repaintBottom();
        repaintLeft();
    }


    /**
     * repaints the left of the screen
     * 
     */
    public void repaintLeft()
    {
        ScrabbleLeftPanel slp = (ScrabbleLeftPanel)this.getComponent( 2 );
        slp.repaintLeft();
    }


    /**
     * repaints the right of the screen
     * 
     */
    public void repaintBottom()
    {
        JPanel jp = (JPanel)this.getComponent( 1 );
        ScrabbleBottomPanel sbp = (ScrabbleBottomPanel)jp.getComponent( 0 );
        sbp.resetPlayer();
    }


    /**
     * repaints the top of the screen
     * 
     */
    public void repaintTop()
    {
        JPanel jp = (JPanel)this.getComponent( 0 );
        JLabel jL = (JLabel)jp.getComponent( 0 );
        String message = "<html>Current Player: ";
        if ( game.getCurrentPlayer() == game.getComputer() )
        {
            message += " Computer<br/>Points : " + game.getComputer().getPoints() + "</html>";
        }
        else
        {
            message += " Human<br/>Total Points : " + game.getHuman().getPoints() + "</html>";
        }
        jL.setText( message );
    }


    /**
     * initializes top panel
     * 
     * @return jp top Jpanel
     */
    private JPanel getTopPanel()
    {
        Border border = BorderFactory.createLineBorder( Color.BLACK, 1 );
        JPanel jp = new JPanel();
        JLabel jL = new JLabel();
        jL.setFont( new Font( "Serif", Font.BOLD, 20 ) );
        jp.add( jL );
        jp.setPreferredSize( getPercent( 10, 10 ) );
        return jp;
    }


    /**
     * initializes center panel
     * 
     * @return jp center Jpanel
     */
    private JPanel getCenterPanel()
    {
        Border border = BorderFactory.createLineBorder( Color.BLACK, 1 );
        JPanel jp = new JPanel();
        jp.setSize( getPercent( 80, 80 ).width, getPercent( 80, 80 ).height );
        jp.setPreferredSize( getPercent( 80, 80 ) );
        // jp.setBorder(border);
        ScrabbleBoard sb = new ScrabbleBoard( game, jp );
        return jp;
    }


    /**
     * draws the board + incorporates all the panels
     * 
     * @param mainPanel
     *            the main panel of the board
     */
    private void drawBoard( JPanel mainPanel )
    {
        mainPanel.add( getTopPanel(), BorderLayout.NORTH );
        mainPanel.add( getBottomPanel(), BorderLayout.SOUTH );
        mainPanel.add( getLeftPanel(), BorderLayout.WEST );
        mainPanel.add( getRightPanel(), BorderLayout.EAST );
        mainPanel.add( getCenterPanel(), BorderLayout.CENTER );
        refreshGame();
    }


    /**
     * initializes main panel
     * 
     * @return jp main Jpanel
     */
    private void initMainPanel( JPanel jp )
    {
        jp.setBackground( Color.BLUE );
        jp.setLayout( new BorderLayout() );
        frame.setContentPane( jp );
        frame.pack();
        frame.setVisible( true );
    }


    /**
     * draws the game
     *
     */
    private void drawGame()
    {
        initFrame();
        initMainPanel( this );
        drawBoard( this );
    }


    /**
     * constructor method
     * 
     */
    public ScrabbleFrontEnd()
    {
        game = new Game();
        mainPanel = new JPanel();
    }


    /**
     * draws overall game
     * 
     */
    private static void createAndShowGUI()
    {
        ScrabbleFrontEnd sfe = new ScrabbleFrontEnd();
        sfe.drawGame();
        String contentHelp = "<html>Notes<br/>";
        contentHelp += "<li>You cannot move a tile once it is placed on the board</li>";
        contentHelp += "<li>Drag exactly three tiles in the label at the bottom to exchange</li>";
        contentHelp += "<li>Do not drag a tile in the exchange label if you have placed any tile on the board on a turn</li>";
        contentHelp += "</html>";

        JOptionPane.showMessageDialog(null, contentHelp);
    }


    /**
     * main method
     * 
     * @param args
     *            string array
     */
    public static void main( String[] args )
    {
        javax.swing.SwingUtilities.invokeLater( new Runnable()
        {
            public void run()
            {
                createAndShowGUI();
            }
        } );
    }
}
