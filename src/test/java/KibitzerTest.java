import org.junit.Test;
import static org.junit.Assert.*;

public class KibitzerTest{

     //Tests that constructor for initializing Kibitzer object correctly 
     //assigns stop_Kibitzer with value false; indicating Kibitzer has not stopped.
     //assertFalse() should make test pass bc getStopKibitzer should return false.
      @Test
     public void StopTest1(){
          //initialize a new Kibitzer object
          Kibitzer kib = new Kibitzer();
          //assertFalse(false)
          assertFalse(kib.getStopKibitzer());
     }

     //Tests that after call to function that breaks the loop in kibitzer, 
     //stop_Kibitzer is in fact true; indicating Kibitzer has stopped.
     //assertTrue() should make test pass bc getStopKibitzer should return true.
     @Test
     public void StopTest2(){
          //initialize a new Kibitzer object
          Kibitzer kib = new Kibitzer();
          //breaks loop in Kibitzer
          kib.break_Kibitzer();
          //assertTrue(true)
          assertTrue(kib.getStopKibitzer());
     }
}
