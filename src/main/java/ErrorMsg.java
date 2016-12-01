import javax.swing.JOptionPane;

/**
 * ErrorMsg class implements a message being displayed through the GUI
 * for the player to see. 
 * Alert messages pop up on screen and player must verify taking note
 * of the alert message by clicking "OK" button.
 *
 */
public class ErrorMsg
{
	/* METHODS */
    /* infoBox() */
    // this method makes an info box pop up on screen that must be accepted;
    // states the appropriate error msg passed in as a parameter
    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

}
