import javax.swing.JOptionPane;

public class ErrorMsg
{
    /* infoBox() */
    // this method makes an info box pop up on screen that must be accepted;
    // states the appropriate error msg for an attempt at an illegal move
    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}