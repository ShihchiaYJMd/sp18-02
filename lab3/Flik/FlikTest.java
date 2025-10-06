import org.junit.Test;
import static org.junit.Assert.*;

public class FlikTest {
    @Test
    public void testIsSameNumber() {
        // test fractional numbers (within memory)
        assertTrue(Flik.isSameNumber(42, 42));
        assertTrue(Flik.isSameNumber(0, 0));
        assertTrue(Flik.isSameNumber(-128, -128));
        assertTrue(Flik.isSameNumber(127, 127));

        // test boundary condition
        assertTrue(Flik.isSameNumber(128, 128));
        assertFalse(Flik.isSameNumber(500, 500));

        // test different numbers
        assertFalse(Flik.isSameNumber(1, 2));
        assertFalse(Flik.isSameNumber(128, 129));

        // test null
        assertFalse(Flik.isSameNumber(null, 128));
        assertFalse(Flik.isSameNumber(128, null));
        assertFalse(Flik.isSameNumber(null, null));
    }

    @Test
    public void testHorribleSteveCase() {
        // 模拟HorribleSteve中的情况
        for (int i = 0; i < 500; i++) {
            int j = i;  // 自动装箱
            if (!Flik.isSameNumber(i, j)) {
                System.out.println("在 i = " + i + " 时失败");
                fail("在 i = " + i + " 时失败");
            }
        }
    }
}
