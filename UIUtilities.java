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
                JToggleButton j = (JToggleButton)c;
                //System.out.println("" + j.getText());
                return new StringSelection(j.getText());
            };

            public int getSourceActions(JComponent c) {
                return TransferHandler.COPY_OR_MOVE;
            };

            protected void exportDone(JComponent c, Transferable data, int action) {
                JPanel parent = (JPanel)c.getParent();
                parent.remove(c);
                parent.updateUI();
            };
        };
        return tfh1;

    }

    public static TransferHandler getTransferHandlerForBoard(){
        TransferHandler tfh1 = new TransferHandler("scrabble"){
            public boolean canImport(TransferHandler.TransferSupport info) {
                return true;
            };

            public boolean importData(JComponent comp, Transferable t){
                //System.out.println("Haha");
                try{
                    String data = (String)t.getTransferData(DataFlavor.stringFlavor);
                    JButton jb = new JButton(data);
                    //System.out.println("In the board " + comp);
                    comp.add(jb);
                    comp.getDropTarget().setActive(false);
                    comp.updateUI();
                    return true;
                }catch(Exception ex){
                    System.out.println("Exception is " + ex);
                }
                return false;
            };

            public int getSourceActions(JComponent c) {
                return TransferHandler.COPY_OR_MOVE;
            };

        };
        return tfh1;
    }

}
