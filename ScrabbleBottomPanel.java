import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class ScrabbleBottomPanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    private Game game;
    private JPanel bottomPanel;
    private ArrayList<Letter> exchangeLetters;

    public ScrabbleBottomPanel(Game game, JPanel panel){
        System.out.println(" " + panel.getWidth() + " " + panel.getHeight());
        this.game = game;
        this.bottomPanel = panel;
        exchangeLetters = new ArrayList<Letter>();
        drawBottomPanel(game, this);
        panel.add(this);
    }

    public ArrayList<Letter> getExchangeLetters(){
        return exchangeLetters;
    }

    public void setExchangeLetters(ArrayList<Letter> exch){
        this.exchangeLetters = new ArrayList<Letter>(exch);
    }

    public Game getGame(){
        return game;
    }

    public JPanel getBottomPanel(){
        return this.bottomPanel;
    }

    public void drawBottomPanel(){
        drawBottomPanel(game, this);
    }

    public void drawBottomPanel(Game game, JPanel bottomPanel){
        JPanel j = new JPanel();
        j.addMouseListener(UIUtilities.getMouseListener());
        j.setTransferHandler(UIUtilities.getTransferHandlerForExchange());
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        j.setBorder(border);
        //j.setLayout(null);
        //int width = this.bottomPanel.getWidth();
        //int height = this.bottomPanel.getHeight();
        //System.out.println(" " + width + "\t" + height);
        //j.setSize(new Dimension(width / 2, height));
        //j.setBounds(0, 0, width / 2, height);
        //j.setBackground(Color.BLUE);
        JLabel lbl = new JLabel();
        lbl.setText("Drag letters you want to exchange on this label");
        j.add(lbl);
        bottomPanel.add(j);

        JTextField f = new JTextField(20);
        JButton check = new JButton("Check Word");
        JButton play = new JButton("Play");
        JButton exchange = new JButton("Exchange");
        JButton pass = new JButton("Pass");
        bottomPanel.add(f);
        bottomPanel.add(check);
        bottomPanel.add(play);
        bottomPanel.add(exchange);
        bottomPanel.add(pass);

        ActionListener exchangeListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JButton btn = (JButton)e.getSource();
                ScrabbleBottomPanel slp = (ScrabbleBottomPanel) btn.getParent();
                System.out.println("IH MYLetter size is " + slp.getExchangeLetters().size());
                Game game = slp.getGame();
                game.exchange(slp.getExchangeLetters());
                JPanel lp = slp.getBottomPanel();
                int esize = slp.getExchangeLetters().size();
                slp.setExchangeLetters(new ArrayList<Letter>());
                JPanel jpl = (JPanel)slp.getComponent(0);
                int j = 0;
                for (Component jc: jpl.getComponents()){
                    if(j != 0){
                        System.out.println("Comp is " + jc);
                        jpl.remove(jc);
                    }
                    j++;
                }
                jpl.updateUI();
                Player player = null;
                Container jc = (Container) slp;
                ScrabbleLeftPanel slpp = null;
                while(jc.getParent() != null){
                    if(jc instanceof ScrabbleFrontEnd){
                        slpp = (ScrabbleLeftPanel)jc.getComponent(2);
                        player = slpp.getPlayer();
                        break;
                    }
                    jc = jc.getParent();
                }
                if (player == null)
                    return;

                ArrayList<Letter> myLetters = slpp.getMyLetters();

                for(int k =0; k< esize; k++){
                    Letter l = game.getBag().getRandomLetter();
                    player.getLetters().add(l);
                    myLetters.add(l);
                }
                if(slpp != null)
                    slpp.drawLeftPanel();
            }
        };
        exchange.addActionListener(exchangeListener);


        ActionListener al = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JButton btn = (JButton)e.getSource();
                JPanel panel = (JPanel)btn.getParent();
                JTextField f = (JTextField)panel.getComponent(1);
                String text = f.getText();
                if (text == null || text.trim().length() == 0){
                    JOptionPane.showMessageDialog(null,
                            "Please enter a word to check");
                    return;
                }
                String uText = text.toUpperCase();
                boolean bln = Words.isWord(uText);
                String message = "";
                if (bln)
                    message = text + " is a valid word";
                else
                    message = text + " is not a word";

                JOptionPane.showMessageDialog(null, message);
            }
        };
        check.addActionListener(al);

        ActionListener playListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JButton btn = (JButton)e.getSource();
                ScrabbleBottomPanel panel = (ScrabbleBottomPanel)btn.getParent();
                int j = 0;
                Container jc = (Container) btn;
                while(jc.getParent() != null){
                    System.out.println(" " + j + " " + jc.getParent().getClass());
                    jc = jc.getParent();
                    j++;
                }
                ScrabbleFrontEnd sfe = (ScrabbleFrontEnd) panel.getParent().getParent();
                ScrabbleBoard jp = (ScrabbleBoard)((JComponent)sfe.getComponent(4)).getComponent(0);
                ArrayList<Square> squares = jp.getSquares();
                if (squares.size() == 0){
                    return;
                }
                for (Square s: squares){
                    System.out.println("S is " + s);
                }
                boolean isValid = jp.getGame().play(squares);
                if (!isValid){
                    JOptionPane.showMessageDialog(null, "Invalid Words. Try again");
                }
            }
        };
        play.addActionListener(playListener);

    ActionListener passListener = new ActionListener(){
        public void actionPerformed(ActionEvent e){
            // Sam edit game.pass();
        };
    };
    pass.addActionListener(passListener);
    }
}


