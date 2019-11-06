package CalculatorUnit;

import com.lzp.test.Calculator;
import org.junit.*;

import static org.junit.Assert.assertTrue;
import com.lzp.test.Calculator;

public class CalculatorTest {

    static Calculator calc = new Calculator();

    @BeforeClass
    public static void beforeClass(){
        calc.clear();
        System.out.println("beforeClass");
    }

    @Before
    public void beforeTest(){
        System.out.println("开始前的值是: "+calc.getResult());
    }

    @Test
    public void testAdd(){
        calc.add(8);
        assertTrue(8 == calc.getResult());
    }

    @Test
    public void testDivide(){
        calc.divide(2);
        assertTrue(4 == calc.getResult());
    }

    @Test
    public void testSquare(){
        calc.square(3);
        assertTrue(4 == calc.getResult());
    }

    @After
    public void AfterTest(){
        System.out.println("结束后的值是: "+calc.getResult());
    }


    @AfterClass
    public static void AfterClass(){
        System.out.println("afterClass");
    }

}
