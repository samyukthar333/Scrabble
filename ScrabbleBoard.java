import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import javax.swing.JLabel;
import javax.swing.JPanel; 
import javax.swing.JFrame;

public class ScrabbleBoard
{
    private static final long serialVersionUID = 1L;
    public static void drawBoard(Game game, JPanel centerPanel){
        centerPanel.setLayout(new GridBagLayout());
        int height = centerPanel.getHeight();
        int weight = centerPanel.getWidth();
        int scrDim = 0;
        if (height > weight)
            scrDim = weight;
        else
            scrDim = height;

        scrDim = (scrDim / 100) * 100;
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(15, 15));
        //panel.setBackground(Color.BLACK);
        panel.setPreferredSize(new Dimension(scrDim, scrDim));
        centerPanel.add(panel);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1); 
        Board board = game.getBoard();
        Square[][] squares = board.getBoard();

        for(int i=0; i<squares.length; i++){
            for(int j=0; j<squares[0].length; j++){
                //JLabel jP = new JLabel("", SwingConstants.CENTER);
                JPanel jP = new JPanel();
                jP.addMouseListener(UIUtilities.getMouseListener());
                //jP.setTransferHandler(UIUtilities.getTransferHandler());
                jP.setTransferHandler(UIUtilities.getTransferHandlerForBoard());
                jP.setBorder(border);
                Square s = squares[i][j];
                //jP.setText(s.toString());
    
                // System.out.println(" " + i + "\t" + j + "\t" + squares[i][j].getSpecial());
                int special = squares[i][j].getSpecial();
                if (special == 4){
                    jP.setBackground(Color.RED);
                    jP.setOpaque(true);
                }
                if (special == 3){
                    jP.setBackground(Color.CYAN);
                    jP.setOpaque(true);
                }
                if (special == 2){
                    jP.setBackground(Color.BLUE);
                    jP.setOpaque(true);
                }
                if (special == 1){
                    jP.setBackground(Color.MAGENTA);
                    jP.setOpaque(true);
                }
                panel.add(jP);
            }
        }

/*
JPanel panel = new JPanel();
panel.setLayout(new GridLayout(15, 15));
Dimension dim = frame.getSize();
panel.setSize((int)(dim.width * 0.5), (int)(dim.height * 0.5));
panel.setPreferredSize(new Dimension((int)(dim.width * 0.6), (int)(dim.height * 0.6)));
Border border = BorderFactory.createLineBorder(Color.BLACK, 1); 
Board board = game.getBoard();
Square[][] squares = board.getBoard();
*/



    }
}


