import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import javax.swing.JLabel;
import javax.swing.JPanel; 
import javax.swing.JFrame;

public class ScrabbleBottomPanel
{
    private static final long serialVersionUID = 1L;
    public static void drawBottomPanel(Game game, JPanel bottomPanel){
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


    }
}


