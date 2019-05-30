import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * 
 * parent class for the gui of the scrabble game
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
     * Creates the panel.
     */
    public ScrabbleGameGUI()
    {
        textField = new JTextField();
        add( textField );
        textField.setColumns( 10 );
    }

}
