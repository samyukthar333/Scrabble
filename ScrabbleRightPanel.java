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
 * @author Richa Bavadekar
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
     * gets button
     * 
     * @param c
     *            char
     * @param p
     *            int
     * @return JToggleButton button
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
     *            the game that is currently being played
     * @param bottomPanel
     *            the bottom panel of the game
     * @param player1
     *            the player that is currently playing
     */
    public static void drawRightPanel( Game game, JPanel bottomPanel, Player player1 )
    {
        return;
    }
}
