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
 *  @author  Richa, saanvi, samyuktha
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
    private String frameName = "Scrabble - LHS project 2019 - By Richa, Samyukta, Sanvi";
    private JFrame frame;
    private JPanel mainPanel;
    private Game game;
    Rectangle dim = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    //Dimension dim = new Dimension(1400, 800);

    /**
     * 
     * calculates the percentages for the dimensions
     * @param w_percent width percentage
     * @param h_percent height percentage
     * @return new Dimension(width, height)
     */
    private Dimension getPercent(int w_percent, int h_percent){
        int width = (dim.width * w_percent) / 100;
        int height = (dim.height * h_percent) / 100; 
        return new Dimension(width, height);
    }

    /**
     * 
     * sets parameters for the frame
     */
    private void initFrame(){
        //Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame(this.frameName);
        frame.setSize(dim.width, dim.height);
        frame.setPreferredSize(new Dimension(dim.width, dim.height));
        frame.setResizable(false);
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }

    /**
     * 
     * creates the right panel
     * @return jp
     */
    private JPanel getRightPanel(){
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        JPanel jp = new JPanel();
        //jp.setSize(dim.width / 10, dim.height / 20);
        //jp.setPreferredSize(new Dimension(dim.width / 10, dim.height / 20));
        jp.setPreferredSize(getPercent(10, 20));
        //jp.setBorder(border);
        ScrabbleRightPanel.drawRightPanel(game, jp, game.getComputer());
        return jp;
    }
    /**
     * 
     * creates the left panel
     * @return sblp
     */
    private JPanel getLeftPanel(){
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        JPanel jp = new JPanel();
        //jp.setSize(dim.width / 10, dim.height / 20);
        //jp.setPreferredSize(new Dimension(dim.width / 10, dim.height / 20));
        jp.setPreferredSize(getPercent(10, 20));
        //jp.setBorder(border);
        ScrabbleLeftPanel sblp = new ScrabbleLeftPanel(game, jp, game.getHuman());
        //ScrabbleLeftPanel.drawLeftPanel(game, jp, game.getHuman());
        return sblp;
    }

    /**
     * 
     * creates the bottom panel
     * @return jp
     */
    private JPanel getBottomPanel(){
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        JPanel jp = new JPanel();
        //jp.setSize(dim.width, dim.height / 10);
        //jp.setPreferredSize(new Dimension(dim.width, dim.height / 10));
        jp.setPreferredSize(getPercent(10, 10));
        jp.setSize(getPercent(10,10).width, getPercent(10,10).height);
        //jp.setBorder(border);
        ScrabbleBottomPanel sbp = new ScrabbleBottomPanel(game, jp);
        //ScrabbleBottomPanel.drawBottomPanel(game, jp);
        return jp;
    }

    /**
     * 
     * recreates the game 
     */
    public void refreshGame(){
        repaintTop();
        repaintBottom();
        repaintLeft();
        //drawGame();
    }

    /**
     * 
     * recreates the left panel
     */
    public void repaintLeft(){
        ScrabbleLeftPanel slp = (ScrabbleLeftPanel)this.getComponent(2);
        slp.repaintLeft();
    }

    /**
     * 
     * recreates the bottom panel
     */
    public void repaintBottom(){
        JPanel jp = (JPanel)this.getComponent(1);
        ScrabbleBottomPanel sbp = (ScrabbleBottomPanel)jp.getComponent(0);
        sbp.resetPlayer();
    }

    /**
     * 
     * recreates the top panel
     */
    public void repaintTop(){
        JPanel jp = (JPanel)this.getComponent(0);
        JLabel jL = (JLabel)jp.getComponent(0);
        String message = "<html>Current Player: ";
        if(game.getCurrentPlayer() == game.getComputer()){
            message += " Computer<br/>Points : " + game.getComputer().getPoints() + "</html>";
        }else{
            message += " Human<br/>Total Points : " + game.getHuman().getPoints() + "</html>";
        }
        jL.setText(message);
    }

    /**
     * 
     * creates the top pane
     * @return jp
     */
    private JPanel getTopPanel(){
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        JPanel jp = new JPanel();
        //jp.setSize(dim.width, dim.height / 10);
        //jp.setPreferredSize(new Dimension(dim.width, dim.height / 10));
        JLabel jL = new JLabel();
        jL.setFont(new Font("Serif", Font.BOLD, 20));
        jp.add(jL);
        jp.setPreferredSize(getPercent(10, 10));
        //jp.setBorder(border);
        return jp;
    }

    /**
     * 
     * creates the center panel
     * @return jp
     */
    private JPanel getCenterPanel(){
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        JPanel jp = new JPanel();
        //jp.setSize(dim.width / 20, dim.height / 20);
        //jp.setPreferredSize(new Dimension(dim.width / 20, dim.height / 20));
        jp.setSize(getPercent(80, 80).width, getPercent(80, 80).height);
        jp.setPreferredSize(getPercent(80, 80));
        //jp.setBorder(border);
        ScrabbleBoard sb = new ScrabbleBoard(game, jp);
        //ScrabbleBoard.drawBoard(game, jp);
        return jp;
    }

    /**
     * 
     * draws the overall board
     * @param mainPanel
     */
    private void drawBoard(JPanel mainPanel){
        mainPanel.add(getTopPanel(), BorderLayout.NORTH);
        mainPanel.add(getBottomPanel(), BorderLayout.SOUTH);
        mainPanel.add(getLeftPanel(), BorderLayout.WEST);
        mainPanel.add(getRightPanel(), BorderLayout.EAST);
        mainPanel.add(getCenterPanel(), BorderLayout.CENTER);
        refreshGame();
    }

    /**
     * 
     * creates the main panel
     * @param jp
     */
    private void initMainPanel(JPanel jp){
        //mainPanel = new JPanel();
        jp.setBackground(Color.BLUE);
        jp.setLayout(new BorderLayout());
        frame.setContentPane(jp);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * 
     * draws the overal game
     */
    private void drawGame(){
        initFrame();
        initMainPanel(this);
        drawBoard(this);
        
        /*
        BorderLayout bl = (BorderLayout)mainPanel.getLayout();
        JPanel p = (JPanel)bl.getLayoutComponent(mainPanel, BorderLayout.NORTH);
        JLabel l = new JLabel("Hi");
        p.add(l);
        */
    }

    /**
     * calls for game
     */
    public ScrabbleFrontEnd(){
        game = new Game();
        mainPanel = new JPanel();
        //System.out.println("Width " + dim.width + " Height " + dim.height);
    }

    /**
     * 
     * creates / draws gui
     */
    private static void createAndShowGUI(){
        ScrabbleFrontEnd sfe = new ScrabbleFrontEnd();
        sfe.drawGame();
    }

    /**
     * 
     * main method
     * @param args
     */
    public static void main (String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
