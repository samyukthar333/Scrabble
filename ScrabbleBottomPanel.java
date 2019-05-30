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
 * @author saanvi, samyuktha, richa
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

    public ScrabbleBottomPanel(Game game, JPanel panel){
        super();
        checkwordbtn = getImage( "checkWord");
        passbtn = getImage( "pass");
        playbtn = getImage( "play");
        exchangebtn = getImage("exchange");
        System.out.println(" " + panel.getWidth() + " " + panel.getHeight());
        this.game = game;
        this.bottomPanel = panel;
        exchangeLetters = new ArrayList<Letter>();
        drawBottomPanel( game, this );
        panel.add( this );
    }

    /**
     *
     * TODO Write your method description here.
     * @param bg background
     * @return
     */
    private Image getImage( String bg )
    {
        try
        {
            String file = "./images/" + bg + ".jpg";
            System.out.println( "File: " + file );
            FileInputStream fis = new FileInputStream( file );
            // BufferedImage myPicture = ImageIO.read(new File("./images/" + bg
            // + ".jpeg"));
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




    public ArrayList<Letter> getExchangeLetters(){
        return exchangeLetters;
    }


    /**
     * 
     * sets the exchanged letters
     * 
     * @param exch
     */
    public void setExchangeLetters( ArrayList<Letter> exch )
    {
        this.exchangeLetters = new ArrayList<Letter>( exch );
    }


    /**
     * 
     * creates the game
     * 
     * @return game
     */
    public Game getGame()
    {
        return game;
    }


    /**
     * 
     * creates the bottom panel
     * 
     * @return this.bottomPanel
     */
    public JPanel getBottomPanel()
    {
        return this.bottomPanel;
    }


    /**
     * 
     * draws the bottom panel
     */
    public void drawBottomPanel()
    {
        drawBottomPanel( game, this );
    }


    /**
     * 
     * resets the player after the game is finished
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
        sfe.repaintTop();
    }


    /**
     * 
     * draws the bottom panel
     * 
     * @param game
     * @param bottomPanel
     */
    public void drawBottomPanel( Game game, JPanel bottomPanel )
    {
        JPanel j = new JPanel();
        j.addMouseListener( UIUtilities.getMouseListener() );
        j.setTransferHandler( UIUtilities.getTransferHandlerForExchange() );
        Border border = BorderFactory.createLineBorder( Color.BLACK, 1 );
        j.setBorder( border );
        // j.setLayout(null);
        // int width = this.bottomPanel.getWidth();
        // int height = this.bottomPanel.getHeight();
        // System.out.println(" " + width + "\t" + height);
        // j.setSize(new Dimension(width / 2, height));
        // j.setBounds(0, 0, width / 2, height);
        // j.setBackground(Color.BLUE);
        JLabel lbl = new JLabel();
        lbl.setText("Drag letters you want to exchange on this label");
        j.add(lbl);
        bottomPanel.add(j);

        JTextField f = new JTextField(20);
        JButton check = new JButton();
        check.setIcon(new ImageIcon(checkwordbtn));
        check.setMargin(new Insets(0, 0, 0, 0));
        check.setBorder(null);

        JButton play = new JButton();
        play.setIcon(new ImageIcon(playbtn));
        play.setMargin(new Insets(0, 0, 0, 0));
        play.setBorder(null);

        JButton exchange = new JButton();
        exchange.setIcon(new ImageIcon(exchangebtn));
        exchange.setMargin(new Insets(0, 0, 0, 0));
        exchange.setBorder(null);

        JButton pass = new JButton();
        pass.setIcon(new ImageIcon(passbtn));
        pass.setMargin(new Insets(0, 0, 0, 0));
        pass.setBorder(null);

        bottomPanel.add(f);
        bottomPanel.add(check);
        bottomPanel.add(play);
        bottomPanel.add(exchange);
        bottomPanel.add(pass);
        ActionListener exchangeListener = new ActionListener()
        {
            public void actionPerformed( ActionEvent e )
            {
                JButton btn = (JButton)e.getSource();
                ScrabbleBottomPanel slp = (ScrabbleBottomPanel)btn.getParent();
                System.out.println( "IH MYLetter size is " + slp.getExchangeLetters().size() );
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
                        System.out.println( "Comp is " + jc );
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
                if ( game.isComputer( game.getCurrentPlayer() ) )
                {
                    ArrayList<Square> squares = ( (ComputerPlayer)game.getComputer() )
                        .findWord( game.getBoard() );
                    boolean isValid = game.play( squares );
                    System.out.println( "Computer play is done? " + isValid );
                    if ( isValid )
                        resetPlayer();
                    return;
                }

                int j = 0;
                Container jc = (Container)btn;
                while ( jc.getParent() != null )
                {
                    System.out.println( " " + j + " " + jc.getParent().getClass() );
                    jc = jc.getParent();
                    j++;
                }
                ArrayList<Square> squares = jp.getSquares();
                if ( squares.size() == 0 )
                {
                    return;
                }
                for ( Square s : squares )
                {
                    System.out.println( "S is " + s );
                }
                boolean isValid = jp.getGame().play( squares );
                if ( !isValid )
                {
                    JOptionPane.showMessageDialog( null, "Invalid Words. Try again" );
               }
            }
        };
        play.addActionListener( playListener );

        ActionListener passListener = new ActionListener()
        {
            public void actionPerformed( ActionEvent e )
            {
                game.pass();
                resetPlayer();
            };
        };
        pass.addActionListener( passListener );
    }
}
