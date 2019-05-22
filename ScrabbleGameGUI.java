import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * 
 * TODO Write a one-sentence summary of your class here. TODO Follow it with
 * additional details about its purpose, what abstraction it represents, and how
 * to use it.
 *
 * @author samyuktha, saanvi, richa
 * @version May 22, 2019
 * @author Period: 4
 * @author Assignment: Scrabble
 *
 * @author Sources: none
 */
public class ScrabbleGameGUI extends JPanel
{
    private JTextField textField;


    /**
     * Create the panel.
     */
    public ScrabbleGameGUI()
    {
        textField = new JTextField();
        add( textField );
        textField.setColumns( 10 );
    }

}
