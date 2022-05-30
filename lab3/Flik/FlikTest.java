import static org.junit.Assert.*;

import org.junit.Test;
public class FlikTest {
    @Test
    public void testIsSameNumber(){
        int a = 130;
        int b = 130;
        boolean res = Flik.isSameNumber(a,b);
        boolean exp = true;
        assertEquals(res,exp);

    }

}
