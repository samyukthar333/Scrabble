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
 * 
 * creates the right panel
 *
 * @author saanvi, samyuktha, richa
 * @version May 29, 2019
 * @author Period: 4
 * @author Assignment: Scrabble
 *
 * @author Sources: none
 */
public class ScrabbleRightPanel
{
    private static final long serialVersionUID = 1L;


    /**
     * 
     * creates the toggle button
     * 
     * @param c
     * @param p
     * @return jtb
     */
    private static JToggleButton getTButton( char c, int p )
    {
        JToggleButton jtb = new JToggleButton( "" + c + " - " + p );
        return jtb;
    }


    /**
     * 
     * draws the right panel
     * 
     * @param game
     * @param bottomPanel
     * @param player1
     */
    public static void drawRightPanel( Game game, JPanel bottomPanel, Player player1 )
    {
        return;
        /*
         * bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
         * JLabel user = new JLabel("Computer Player");
         * user.setAlignmentX(Component.CENTER_ALIGNMENT); JLabel points = new
         * JLabel("Total Points: " + player1.getPoints());
         * points.setAlignmentX(Component.CENTER_ALIGNMENT);
         * bottomPanel.add(user); bottomPanel.add(points);
         * bottomPanel.add(Box.createHorizontalGlue()); PlayerLetters pl =
         * player1.getLetters(); for(Letter l: pl.getLetters()){ char c =
         * l.getLetter(); int p = l.getPointValue(); JToggleButton jb =
         * getTButton(c, p); bottomPanel.add(jb); }
         */
    }
}
