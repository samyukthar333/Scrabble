import javax.swing.JPanel;
import javax.swing.JTextField;

public class ScrabbleGameGUI extends JPanel
{
    private JTextField textField;

    /**
     * Create the panel.
     */
    public ScrabbleGameGUI()
    {
        
        textField = new JTextField();
        add(textField);
        textField.setColumns(10);

    }

}
