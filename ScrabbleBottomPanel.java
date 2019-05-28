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

    public ScrabbleBottomPanel(Game game, JPanel panel){
        System.out.println(" " + panel.getWidth() + " " + panel.getHeight());
        this.game = game;
        this.bottomPanel = panel;
        drawBottomPanel(game, this);
        panel.add(this);
    }

    public void drawBottomPanel(){
        drawBottomPanel(game, this);
    }

    public void drawBottomPanel(Game game, JPanel bottomPanel){

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
        ActionListener al = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JButton btn = (JButton)e.getSource();
                JPanel panel = (JPanel)btn.getParent();
                JTextField f = (JTextField)panel.getComponent(0);
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


