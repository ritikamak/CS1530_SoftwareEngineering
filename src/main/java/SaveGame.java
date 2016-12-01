import java.io.PrintWriter;
import java.io.File;
public class SaveGame extends Thread{
     public SaveGame(){

     }
     public void run(){
          try{
               System.out.println("here");
               PrintWriter printWriter = new PrintWriter ("save_game.pgn");
               for(String s: Chess.fenList){
                    printWriter.println(s);
               }
               printWriter.close ();
          }
          catch (Exception e){

          }
     }
}
