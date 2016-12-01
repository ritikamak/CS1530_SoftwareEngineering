import java.io.PrintWriter;
import java.io.File;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
public class LoadGame{
     public LoadGame(){

     }
     public void load(){
          try{
               Stockfish fish = new Stockfish();
               fish.startEngine();
               Scanner k = new Scanner(System.in);
               System.out.println("Please enter a name for the input file: ");
               String f = k.next();
               //f = f + ".pgn";
               BufferedReader reader = new BufferedReader(new FileReader(f));
               int i = 1;
               while(reader.ready()){
                    String s = reader.readLine();
                    Chess.fenList.add(s);
                    System.out.println();
                    System.out.println("Move Number: " + i);
                    fish.drawBoard(s);
                    i++;
               }
               //String last_fen = Chess.fenList.get(Chess.fenList.size() - 1);
               reader.close();
               k.close();
               //System.out.println(last_fen);
               //fish.drawBoard(last_fen);
          }
          catch (Exception e){

          }
     }
}
