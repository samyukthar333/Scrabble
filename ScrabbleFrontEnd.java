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
<<<<<<< HEAD
 *  @author  samyuktha, saanvi, richa
=======
 *  @author  Richa Bavadekar
>>>>>>> 062b89ddc36e68a4da41ef3da57b9050980505ed
 *  @version May 22, 2019
 *  @author  Period: 4
 *  @author  Assignment: Scrabble
 *
<<<<<<< HEAD
 *  @author  Sources: none
=======
 *  @author  Sources: TODO add sources
>>>>>>> 062b89ddc36e68a4da41ef3da57b9050980505ed
 */
public class ScrabbleFrontEnd extends JPanel
{//  testing log add a dicitonary at the bottom and players tiles at the bottom of the screen 
    // this class runs the game
    private static final long serialVersionUID = 1L;
    private int xSize = 800;
    private int ySize = 800;
    private String frameName = "Scrabble";
    private JFrame frame;
    Game game;
    ArrayList<JLabel> scrabble = new ArrayList<JLabel>();
<<<<<<< HEAD

    /**
     * 
     * draws the board
     */
=======
    
/**
 * 
 * TODO Write your method description here.
 */
>>>>>>> 062b89ddc36e68a4da41ef3da57b9050980505ed
    public void drawMain(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame(this.frameName);
        frame.setPreferredSize(new Dimension(xSize, ySize));
        frame.setSize(xSize, ySize);
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        JPanel p = drawInner();
        frame.setContentPane(p);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * 
<<<<<<< HEAD
     * draws the top panel
     * @return p
=======
     * TODO Write your method description here.
     * @return
>>>>>>> 062b89ddc36e68a4da41ef3da57b9050980505ed
     */
    public JPanel getTopPanel(){
        JPanel p = new JPanel();
        JLabel j = new JLabel("SCRABBLE");
        Dimension dim = frame.getSize();
        p.setSize(dim.width, (int)(dim.height * 0.2));
        p.add(j);
        return p;
    }

    /**
     * 
     * TODO Write your method description here.
     * @param button
     */
    public void setActionListener(JButton button){
        String buttonText = button.getText();
        int i = 0;
        if (buttonText.equals("Play")){
            i = 0;
            //game.play();
        }
        if (buttonText.equals("Exchange"))
            i = 1;
        if (buttonText.equals("Pass"))  
            i = 2;

        ActionListener al = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println(" ");
            }
        };
        button.addActionListener(al);
    }

    /**
     * 
     * TODO Write your method description here.
     * @return
     */
    public JPanel getBottomPanel(){
        JPanel p = new JPanel();
        //add a dictionary thing 

        Player current = game.getCurrentPlayer();

        String info = "";
        int points = current.getPoints();
        info = info + " Points: " + points;
        PlayerLetters pl = current.getLetters();
        //if (pl.size() == 0){
        //    pl = game.getRandomThree()
        //}
        for(Letter l : pl.getLetters()){
            info = info + " " + l.toString();
        }
        JLabel infoLabel = new JLabel();
        infoLabel.setText(info);

        JButton play = new JButton("Play");
        JButton exchange = new JButton("Exchange");
        JButton pass = new JButton("Pass");
        JTextField word = new JTextField("Enter a word here");
        String s = word.getText();
        System.out.println(s);
//        if (Words.isWord(s))
//        {
//            System.out.print("is a word");
//        }
        play.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ArrayList<Square> playerLetter = new ArrayList<Square>(); // the players letters after they drag, add finish button
                //game.play(playerLetter);
            }
        });

        exchange.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ArrayList<Letter> exchangeLetters = new ArrayList<Letter>(); // 3 letters that a player drags when they want to exchange 
                //game.exchange(exchangeLetters);
            }
        });

        pass.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               game.pass(); //nothing happens
            }
        
        
        });
        
//        word.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e){
//               if (w.isWord(s))
//               {
//                   
//               }
//            }
//        }); 
         
        

        p.add(infoLabel);
        p.add(play);
        p.add(exchange);
        p.add(pass);
        p.add( word );

        return p;
    }


    /**
     * 
     * TODO Write your method description here.
     * @return
     */
    public JPanel getLeftPanel(){
        JPanel p = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.rotate(Math.PI / 2); //, frame.getSize().width, (int)(frame.getSize().height * 0.2));
            }
        };

        Dimension dim = frame.getSize();
        p.setSize((int)(dim.width * 0.2), dim.height);
        JLabel j = new JLabel("SCRABBLE");
        p.add(j);
        return p;
    }

    /**
     * 
     * TODO Write your method description here.
     * @return
     */
    public JPanel getCenterPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(15, 15));
        Dimension dim = frame.getSize();
        panel.setSize((int)(dim.width * 0.5), (int)(dim.height * 0.5));
        panel.setPreferredSize(new Dimension((int)(dim.width * 0.6), (int)(dim.height * 0.6)));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        Board board = game.getBoard();
        Square[][] squares = board.getBoard();

        for(int i=0; i<squares.length; i++){
            for(int j=0; j<squares[0].length; j++){
                JLabel jP = new JLabel("", SwingConstants.CENTER);
                jP.setBorder(border);
                Square s = squares[i][j];
                jP.setText(s.toString());
                
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
                scrabble.add(jP);
            }
        }
        return panel;
    }

    /**
     * 
     * TODO Write your method description here.
     * @return
     */
    public JPanel drawInner(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(getTopPanel(), BorderLayout.NORTH);
        panel.add(getBottomPanel(), BorderLayout.SOUTH);
        panel.add(getLeftPanel(), BorderLayout.WEST);
        panel.add(getLeftPanel(), BorderLayout.EAST);
        panel.add(getCenterPanel(), BorderLayout.CENTER);
        return panel;
    }

    /**
     * 
     */
    public ScrabbleFrontEnd(){
        game = new Game();
    }

    /**
     * 
     * TODO Write your method description here.
     */
    private static void createAndShowGUI(){
        ScrabbleFrontEnd sfe = new ScrabbleFrontEnd();
        sfe.drawMain();
    }

   /**
    * 
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
