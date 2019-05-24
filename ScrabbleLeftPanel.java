import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import javax.swing.JLabel;
import javax.swing.JPanel; 
import javax.swing.JFrame;

public class ScrabbleLeftPanel
{
    private static final long serialVersionUID = 1L;
    private static JToggleButton getTButton(char c, int p){
        JToggleButton jtb = new JToggleButton("" + c + " - " + p);
        return jtb;
    }

    public static void drawLeftPanel(Game game, JPanel bottomPanel, Player player1){
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        JLabel user = new JLabel("Player1");
        user.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel points = new JLabel("Total Points: " + player1.getPoints());
        points.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.add(user);
        bottomPanel.add(points);
        bottomPanel.add(Box.createHorizontalGlue());
        PlayerLetters pl = player1.getLetters();
        ArrayList<Letter> letters = pl.getLetters();
        if (player1.getPoints() == 0 && letters.size() == 0){
            LetterBag myBag = game.getBag();
            for(int i=0; i<7; i++){
                Letter l = myBag.getRandomLetter();
                pl.add(l);
            }
        }
        
        for(Letter l: pl.getLetters()){
            char c = l.getLetter();
            int p = l.getPointValue();
            JToggleButton jb = getTButton(c, p);
            MouseListener ml = UIUtilities.getMouseListener();
            jb.addMouseListener(ml);
            TransferHandler tfh = UIUtilities.getTransferHandler();
            jb.setTransferHandler(tfh);
            bottomPanel.add(jb);
        }
    }
}


