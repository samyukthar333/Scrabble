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
 *  TODO Write a one-sentence summary of your class here.
 *  TODO Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author  Richa Bavadekar
 *  @version May 22, 2019
 *  @author  Period: 4
 *  @author  Assignment: Scrabble
 *
 *  @author  Sources: none
 */
public class ScrabbleFrontEnd extends JPanel
{//  testing log add a dicitonary at the bottom and players tiles at the bottom of the screen 
    // this class runs the game
    private static final long serialVersionUID = 1L;
    private String frameName = "Scrabble";
    private JFrame frame;
    private JPanel mainPanel;
    private Game game;
    Rectangle dim = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();

    private Dimension getPercent(int w_percent, int h_percent){
        int width = (dim.width * w_percent) / 100;
        int height = (dim.height * h_percent) / 100; 
        return new Dimension(width, height);
    }

    private void initFrame(){
        //Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame(this.frameName);
        frame.setSize(dim.width, dim.height);
        frame.setPreferredSize(new Dimension(dim.width, dim.height));
        frame.setResizable(false);
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }

    private JPanel getRightPanel(){
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        JPanel jp = new JPanel();
        //jp.setSize(dim.width / 10, dim.height / 20);
        //jp.setPreferredSize(new Dimension(dim.width / 10, dim.height / 20));
        jp.setPreferredSize(getPercent(10, 20));
        jp.setBorder(border);
        ScrabbleRightPanel.drawLeftPanel(game, jp, game.getHuman());
        return jp;
    }

    private JPanel getLeftPanel(){
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        JPanel jp = new JPanel();
        //jp.setSize(dim.width / 10, dim.height / 20);
        //jp.setPreferredSize(new Dimension(dim.width / 10, dim.height / 20));
        jp.setPreferredSize(getPercent(10, 20));
        jp.setBorder(border);
        ScrabbleLeftPanel.drawLeftPanel(game, jp, game.getHuman());
        return jp;
    }

    private JPanel getBottomPanel(){
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        JPanel jp = new JPanel();
        //jp.setSize(dim.width, dim.height / 10);
        //jp.setPreferredSize(new Dimension(dim.width, dim.height / 10));
        jp.setPreferredSize(getPercent(10, 10));
        jp.setBorder(border);
        ScrabbleBottomPanel.drawBottomPanel(game, jp);
        return jp;
    }

    private JPanel getTopPanel(){
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        JPanel jp = new JPanel();
        //jp.setSize(dim.width, dim.height / 10);
        //jp.setPreferredSize(new Dimension(dim.width, dim.height / 10));
        jp.setPreferredSize(getPercent(10, 10));
        jp.setBorder(border);
        return jp;
    }

    private JPanel getCenterPanel(){
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        JPanel jp = new JPanel();
        //jp.setSize(dim.width / 20, dim.height / 20);
        //jp.setPreferredSize(new Dimension(dim.width / 20, dim.height / 20));
        jp.setSize(getPercent(80, 80).width, getPercent(80, 80).height);
        jp.setPreferredSize(getPercent(80, 80));
        jp.setBorder(border);
        ScrabbleBoard.drawBoard(game, jp);
        return jp;
    }

    private void drawBoard(){
        mainPanel.add(getTopPanel(), BorderLayout.NORTH);
        mainPanel.add(getBottomPanel(), BorderLayout.SOUTH);
        mainPanel.add(getLeftPanel(), BorderLayout.WEST);
        mainPanel.add(getRightPanel(), BorderLayout.EAST);
        mainPanel.add(getCenterPanel(), BorderLayout.CENTER);
    }

    private void initMainPanel(){
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLUE);
        mainPanel.setLayout(new BorderLayout());
        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private void drawGame(){
        initFrame();
        initMainPanel();
        drawBoard();
        
        /*
        BorderLayout bl = (BorderLayout)mainPanel.getLayout();
        JPanel p = (JPanel)bl.getLayoutComponent(mainPanel, BorderLayout.NORTH);
        JLabel l = new JLabel("Hi");
        p.add(l);
        */
    }

    public ScrabbleFrontEnd(){
        game = new Game();
    }

    private static void createAndShowGUI(){
        ScrabbleFrontEnd sfe = new ScrabbleFrontEnd();
        sfe.drawGame();
    }

    public static void main (String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
