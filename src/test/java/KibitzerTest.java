import org.junit.Test;
import static org.junit.Assert.*;

public class KibitzerTest{
     @Test
     public void StopTest1(){
          Kibitzer kib = new Kibitzer();
          assertFalse(kib.getStopKibitzer());
     }
     @Test
     public void StopTest2(){
          Kibitzer kib = new Kibitzer();
          kib.break_Kibitzer();
          assertTrue(kib.getStopKibitzer());
     }
}
