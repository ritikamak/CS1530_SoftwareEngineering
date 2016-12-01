import java.util.Random;
import java.lang.InterruptedException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.GroupLayout.*;

public class Kibitzer extends Thread{
     private int wait_time;
     private int message_number;
     private static boolean stop_Kibitzer;
     private boolean legendary;
     private Kibitzer_gui kib_gui;

     public Kibitzer(){
          kib_gui = new Kibitzer_gui();
          legendary = false;
          stop_Kibitzer = false;

     }
     public void run(){
          //random.nextInt(max - min + 1) + min
          Random r = new Random();

          while(!stop_Kibitzer){
               message_number = r.nextInt(30-1+1) + 1;
               //anywhere from 1 to 5 seconds
               wait_time = r.nextInt(5-1+1) + 1;
               //System.out.println(message(message_number));
               kib_gui.setLabel(message(message_number));
               try{
                    Thread.sleep(wait_time*1000);
               }
               catch(InterruptedException ex){

               }
          }
     }

     //returns a message based on the int that was passed in
     public String message(int i){
          switch (i){
               case 1:
                    return "He who hesitates has lost.";
               case 2:
                    return "If you ever think about using JavaScript, don't.";
               case 3:
                    return "JavaScript is a sword without a hilt.";
               case 4:
                    return "Rust is a 3d printed gun. It may work some day.";
               case 5:
                    return "Ruby is a ruby encrusted sword, it is usually only used because of how shiny it is.";
               case 6:
                    return "Lisp is a shiv, anyone who uses this is probably crazy and dangerous.";
               case 7:
                    return "Life isn't fair, get over it";
               case 8:
                    return "The ostrich algorithm is a strategy of ignoring potential problems on the basis that they may be exceedingly rare";
               case 9:
                    return "When in doubt, blame the user";
               case 10:
                    return "When you don't know the answer, stack overflow";
               case 11:
                    return "git ignore";
               case 12:
                    return "Is your elevator running? Because you better go catch it";
               case 13:
                    return "What do you call a skinny ghost? BOOLEAN";
               case 14:
                    return "Pokemon Go just lost 10 million users. Should I switch to Pokemon Rust or Pokemon Node?";
               case 15:
                    return "There are no bugs, only features";
               case 16:
                    return "[\"hip\",\"hip\"]";
               case 17:
                    return "How many prolog programmers does it take to change a lightbulb? Yes";
               case 18:
                    return "To understand what recursion is, you must first understand recursion.";
               case 19:
                    return "Unix is user friendly. It's just very particular about who its friends are.";
               case 20:
                    return "A foo walks into a bar, takes a look around and says \"Hello World!\" and meet up his friend Baz";
               case 21:
                    return "Keyboard not found ... press F1 to continue ";
               case 22:
                    return "How long does it take to copy a file in Vista? Yeah, I don't know either, I'm still waiting to find out.";
               case 23:
                    return "Programming, turning coffee into code";
               case 24:
                    return "Hardware, the part of the computer you can kick";
               case 25:
                    return "Programming, fixing a problem you don't know you had in a way you can't understand";
               case 26:
                    return "Where do programmers go for a drink? Foo Bar";
               case 27:
                    return "There are three kinds of lies: Lies, damned lies, and benchmarks";
               case 28:
                    return "Don’t worry about warnings; we only worry about errors.";
               case 29:
                    return "Thinking outside the bottle. Coding drunk.";
               case 30:
                    if(legendary){
                         legendary = true;
                         return "This Kibitzer is legen...";
                    }
                    else{
                         legendary = false;
                         return "...wait for it ... dary!";
                    }
          }
          return "At least we aren't using C";
     }

     //function to break the loop in kibitzer
     public static void break_Kibitzer(){
          stop_Kibitzer = true;
     }
     public boolean getStopKibitzer(){
          return stop_Kibitzer;
     }
     //test
     public static void main(String[] args){
          Kibitzer kib = new Kibitzer();
          kib.run();
     }
}
//creating a separate gui for the kibitzer
class Kibitzer_gui {
     private JTextArea textArea;
     private JScrollPane areaScrollPane;
     private JFrame frame;
     private JLabel label;
     public Kibitzer_gui(){
          //Create and set up the window.
          frame = new JFrame("Kibitizer");
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.getContentPane().setPreferredSize(new Dimension(725, 100));

          label = new JLabel("Kibitizer");
          label.setPreferredSize(new Dimension(175, 100));
          frame.getContentPane().add(label, BorderLayout.CENTER);

          //Display the window.
          frame.pack();
          frame.setVisible(true);
     }
     public void setLabel(String s){
          // System.out.println("s = " + s);
          label.setText(s);
          //textArea.append("\n" + s);
     }
}
