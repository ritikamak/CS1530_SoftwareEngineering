import java.io.PrintWriter;
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
               PrintWriter printWriter = new PrintWriter (f);
               for(String s: Chess.fenList){
                    printWriter.println(s);
               }

               printWriter.close ();
               k.close();
          }
          catch (Exception e){

          }
     }
}
