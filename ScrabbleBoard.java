import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.io.FileInputStream;

public class ScrabbleBoard extends JPanel
{
    private static final long serialVersionUID = 1L;
    private Game game;
    private JPanel centerPanel;
    private ArrayList<Square> squares;

    public ScrabbleBoard(Game game, JPanel centerPanel){
        super();
        this.game = game;
        this.centerPanel = centerPanel;
        this.squares = new ArrayList<Square>();
        drawBoard(game, this);
        centerPanel.add(this);
    }

    public ArrayList<Square> getSquares(){
        return squares;
    }

    private JPanel getPanel(Image img){
        final Image i = img;
        JPanel jP = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (i != null);
                   // Sam edit g.drawImage(img, 0, 0, null);
            }
        };
        return jP;
    }

    private Image getImage(String bg){
        try{
            String file = "./images/" + bg + ".jpg";
            System.out.println("File: " + file);
            FileInputStream fis = new FileInputStream(file);
            //BufferedImage myPicture = ImageIO.read(new File("./images/" + bg + ".jpeg"));
            BufferedImage myPicture = ImageIO.read(fis);
            Image dimg = myPicture.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            return dimg;
        } catch(Exception ex){
            System.out.println(bg + "Error: " + ex);
            ex.printStackTrace();
        }
        return null;
    }

    public void drawBoard(){
        drawBoard(game, this);
    }

    public Game getGame(){
        return this.game;
    }

    public void drawBoard(Game game, JPanel centerPanel){
        centerPanel.setLayout(new GridBagLayout());
        int height = this.centerPanel.getHeight();
        int weight = this.centerPanel.getWidth();
        int scrDim = 0;
        if (height > weight)
            scrDim = weight;
        else
            scrDim = height;

        scrDim = (scrDim / 100) * 100;
        System.out.println("VISH " + scrDim);
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

                Square s = squares[i][j];
                int special = squares[i][j].getSpecial();
                Image img = null;
                if (special == 4){
                    img = getImage("threeword");
                }
                if (special == 3){
                    img = getImage("twoword");
                }
                if (special == 2){
                    img = getImage("threeletter");
                }
                if (special == 1){
                    img = getImage("twoletter");
                }


                //JLabel jP = new JLabel("", SwingConstants.CENTER);
                //JPanel jP = new JPanel();
                JPanel jP = getPanel(img);
                jP.addMouseListener(UIUtilities.getMouseListener());
                //jP.setTransferHandler(UIUtilities.getTransferHandler());
                jP.setTransferHandler(UIUtilities.getTransferHandlerForBoard());
                jP.setBorder(border);
                //jP.setText(s.toString());
                /*
                // System.out.println(" " + i + "\t" + j + "\t" + squares[i][j].getSpecial());
                //int special = squares[i][j].getSpecial();
                if (special == 4){
                    Image img = getImage("tripleword");
                    //jP.setBackground(Color.RED);
                    jP.drawImage(img, 0, 0, null);
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
                */
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


