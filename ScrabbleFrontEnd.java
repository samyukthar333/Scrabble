import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JPanel; 
import javax.swing.JFrame;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class ScrabbleFrontEnd extends JPanel
{

    public ScrabbleFrontEnd()
    {
        //super(new BoxLayout());
        super();
        Border paneEdge = BorderFactory.createLineBorder(Color.BLACK);

        JPanel listPane = new JPanel();
        listPane.setPreferredSize(new Dimension(700, 700));
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));
        //
        // TOP ROW
        JPanel topRowHelperPanel = new JPanel();
        topRowHelperPanel.setPreferredSize(new Dimension(30, 30));
        JLabel topText = new JLabel("User1 to play !!");
        //topText.setHorizontalAlignment(JLabel.CENTER);
        //topText.setVerticalAlignment(JLabel.CENTER);
        topRowHelperPanel.add(topText);
        topRowHelperPanel.setPreferredSize(new Dimension(600, 30));

        listPane.add(topRowHelperPanel);
        listPane.setBorder(paneEdge);

        // MIDDLE ROW
        JPanel middleRow = new JPanel();
        java.util.List<JTextField> myScrabble = new ArrayList<JTextField>();
        int rows = 15;
        int cols = 15;
        middleRow.setLayout(new GridLayout(rows, cols));
        for(int x=0; x<rows; x++){
            for(int y=0; y<cols; y++){
                JTextField txt = new JTextField(1);
                txt.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyTyped(java.awt.event.KeyEvent evt) {
//                        if(txt.getText().length()>=1&&!(evt.getKeyChar()==KeyEvent.VK_DELETE||evt.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
//                            getToolkit().beep();
//                            evt.consume();
//                        }
                    }
                });
                txt.setText("" + y);
                myScrabble.add(txt);
                middleRow.add(txt);
            }
        }

        middleRow.setPreferredSize(new Dimension(600, 600));
        listPane.add(middleRow);

        // LAST ROW
        JPanel bottomRowHelperPanel = new JPanel();
        bottomRowHelperPanel.setPreferredSize(new Dimension(30, 30));
        JLabel topText2 = new JLabel("User1: 200, Computer: 150");
        bottomRowHelperPanel.add(topText2);
        listPane.add(bottomRowHelperPanel);
        listPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        listPane.setAlignmentY(Component.CENTER_ALIGNMENT);
        add(listPane);
    }

    private static void createAndShowGUI(){
        int xSize = 800;
        int ySize = 800;
        JFrame frame = new JFrame("Scrabble - APCS Lynbrook Lab by Richa, S, A");
        frame.setPreferredSize(new Dimension(xSize, ySize));
        //frame.setMinimumSize(new Dimension(xSize, ySize));
        frame.setSize(xSize, ySize);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.getContentPane().setLayout(new BorderLayout());

        //Create and set up the content pane.
        ScrabbleFrontEnd newContentPane = new ScrabbleFrontEnd();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);

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

