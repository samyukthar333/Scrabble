// https://docs.oracle.com/javase/tutorial/uiswing/dnd/dropmodedemo.html
// https://docs.oracle.com/javase/tutorial/uiswing/dnd/dropaction.html
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.TransferHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.dnd.*;

public class UIUtilities {
    private static JPanel getPanel(Image img){
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

    public static MouseListener getMouseListener(){
        MouseListener listener = new MouseAdapter(){
            public void mousePressed(MouseEvent me){
                JComponent comp = (JComponent) me.getSource();
                //System.out.println(comp);
                TransferHandler handler = comp.getTransferHandler();
                handler.exportAsDrag(comp, me, TransferHandler.COPY);
            }
        };
        return listener;
    }

    public static TransferHandler getTransferHandler(){
        TransferHandler tfh1 = new TransferHandler("scrabble"){
            public boolean importData(JComponent comp, Transferable t){
                try{
                    String data = (String)t.getTransferData(DataFlavor.stringFlavor);
                    JToggleButton jb = new JToggleButton(data);
                    comp.add(jb);
                    comp.updateUI();
                    return true;
                }catch(Exception ex){
                    System.out.println("Exception is " + ex);
                }
                return false;
            };
            public boolean canImport(TransferHandler.TransferSupport info) {
                return true;
            };

            protected Transferable createTransferable(JComponent c) {
                JLabel j = (JLabel)c;
                //System.out.println("" + j.getText());
                return new StringSelection(j.getToolTipText());
            };

            public int getSourceActions(JComponent c) {
                return TransferHandler.COPY_OR_MOVE;
            };

            protected void exportDone(JComponent c, Transferable data, int action) {
                if (action == 0)
                    return;

                try{
                String d = (String)data.getTransferData(DataFlavor.stringFlavor);
                System.out.println("Data here is " + d);
                ScrabbleLeftPanel parent = (ScrabbleLeftPanel)c.getParent();
                ArrayList<Letter> letters = parent.getMyLetters();
                for(Letter l : letters){
                    if (l.getLetter() == d.charAt(0)){
                        letters.remove(l);
                        break;
                    }
                }
                parent.remove(c);
                parent.updateUI();
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            };
        };
        return tfh1;

    }

    public static TransferHandler getTransferHandlerForBoard(){
        TransferHandler tfh1 = new TransferHandler("scrabble"){
            public boolean canImport(TransferHandler.TransferSupport info) {
                return info.isDrop();
            };

            public boolean importData(JComponent comp, Transferable t){
                try{
                    String data = (String)t.getTransferData(DataFlavor.stringFlavor);
                    try{
                        String file = "./images/" + data + ".jpg";
                        System.out.println("File: " + file);
                        java.net.URL imageURL = UIUtilities.class.getResource(file);
                        ImageIcon ii = new ImageIcon(imageURL);
                        Image i = ii.getImage();
                        Image newi = i.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
                        JLabel jb = new JLabel();
                        jb.setBounds(0,0, 40, 40);
                        jb.setToolTipText(data);
                        jb.setIcon(new ImageIcon(newi));
                        jb.addMouseListener(getMouseListener());
                        jb.setTransferHandler(getTransferHandlerForLabel());
                        comp.setLayout(null);
                        comp.add(jb);
                        int j = 0;

                        for(Component c: comp.getParent().getComponents()){
                                System.out.println("Component is " + c);
                                if(c == comp){
                                    System.out.println("This is the square " + j);
                                    int col = j / 15;
                                    int row = j % 15;
                                    Square s = new Square(new Letter(data.charAt(0)), row, col);
                                    System.out.println("SS is " + s);
                                    System.out.println("SS is " + s.getCol() + " " + s.getRow());
                                    ((ScrabbleBoard)comp.getParent().getParent()).getSquares().add(s);
                                    break;
                                }
                                j++;
                        }
                        //JLabel jL = new JLabel(new ImageIcon(newi));
                        //comp.setLayout(null);
                        //comp.add(jL);
                        //comp = getPanel(i);
                    }catch(Exception ex){
                        ex.printStackTrace();

                    }
                    //JButton jb = new JButton(data);
                    //System.out.println("In the board " + comp);
                    //comp.add(jb);
                    comp.getDropTarget().setActive(false);
                    comp.updateUI();
                    comp.repaint();
                    return true;
                }catch(Exception ex){
                    System.out.println("Exception is " + ex);
                    ex.printStackTrace();
                }
                return false;
            };

            public int getSourceActions(JComponent c) {
                return TransferHandler.COPY_OR_MOVE;
            };

        };
        return tfh1;
    }

    public static TransferHandler getTransferHandlerForLabel(){
        TransferHandler tfh1 = new TransferHandler("scrabble"){
            public boolean canImport(TransferHandler.TransferSupport info) {
                return false;
            };

            public int getSourceActions(JComponent c) {
                return TransferHandler.COPY_OR_MOVE;
            };

            protected void exportDone(JComponent c, Transferable data, int action) {
                if (action == 0)
                    return;
                JPanel parent = (JPanel)c.getParent();
                parent.remove(c);
                parent.getDropTarget().setActive(true);
                parent.updateUI();
            };

            protected Transferable createTransferable(JComponent c) {
                JLabel j = (JLabel)c;
                //System.out.println("" + j.getText());
                return new StringSelection(j.getToolTipText());
            };
        };
        return tfh1;
    }

    public static TransferHandler getTransferHandlerForLeftPanel(){
        TransferHandler tfh1 = new TransferHandler("scrabble"){
            public boolean canImport(TransferHandler.TransferSupport info) {
                return info.isDrop();
            };

            public int getSourceActions(JComponent c) {
                return TransferHandler.COPY_OR_MOVE;
            };

            public boolean importData(JComponent comp, Transferable t){
                try{
                    String data = (String)t.getTransferData(DataFlavor.stringFlavor);
                    Letter l = new Letter(data.charAt(0));
                    System.out.println("Letter is " + l.getLetter() + " point " + l.getPointValue());
                    System.out.println("Comp " + comp + "\n" + comp.getClass());

                    ScrabbleLeftPanel slp = (ScrabbleLeftPanel) comp;
                    System.out.println("IH MYLetter size is " + slp.getMyLetters().size());
                    slp.getMyLetters().add(l);
                    //Player player1 = slp.getPlayer();
                    //System.out.println("Player 1 is " + player1);
                    //player1.getLetters().add(l);
                    slp.drawLeftPanel();
                    return true;
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                return false;
            };
        };
        return tfh1;
    }
    public static TransferHandler getTransferHandlerForExchange(){
        TransferHandler tfh1 = new TransferHandler("scrabble"){
            public boolean canImport(TransferHandler.TransferSupport info) {
                return info.isDrop();
            };

            public int getSourceActions(JComponent c) {
                return TransferHandler.COPY_OR_MOVE;
            };

            public boolean importData(JComponent comp, Transferable t){
                try{
                    String data = (String)t.getTransferData(DataFlavor.stringFlavor);
                    Letter l = new Letter(data.charAt(0));
                    System.out.println("Letter is " + l.getLetter() + " point " + l.getPointValue());
                    System.out.println("Comp " + comp + "\n" + comp.getClass());

                    ScrabbleBottomPanel slp = (ScrabbleBottomPanel) comp.getParent();
                    System.out.println("IH MYLetter size is " + slp.getExchangeLetters().size());
                    slp.getExchangeLetters().add(l);
                    String file = "./images/" + data + ".jpg";
                    System.out.println("File: " + file);
                    java.net.URL imageURL = UIUtilities.class.getResource(file);
                    ImageIcon ii = new ImageIcon(imageURL);
                    Image i = ii.getImage();
                    Image newi = i.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
                    JLabel jb = new JLabel();
                    jb.setBounds(0,0, 40, 40);
                    jb.setToolTipText(data);
                    jb.setIcon(new ImageIcon(newi));
                    comp.add(jb);
                    return true;
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                return false;
            };
        };
        return tfh1;
    }

}
