import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.util.Scanner;
public class SaveGame{
     public SaveGame(){

     }
     public void save(){
          try{
               Scanner k = new Scanner(System.in);
               System.out.println("Please enter a name for the output file: ");
               String f = k.next();
               f = f + ".pgn";
               //PrintWriter printWriter = new PrintWriter (f);
               BufferedWriter writer = new BufferedWriter(new FileWriter(f));
               for(String s: Chess.fenList){
                    writer.write(s);
               }
               writer.close ();
               k.close();
          }
          catch (Exception e){

          }
     }
}
