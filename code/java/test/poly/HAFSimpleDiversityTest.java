/*
 */
package poly;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pubsim.VectorFunctions;
import org.mckilliam.distributions.Gaussian;

/**
 *
 * @author Robby McKilliam
 */
public class HAFSimpleDiversityTest {
    
    public HAFSimpleDiversityTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of estimate method, of class HAFSimpleDiversityTest.
     */
    @Test
    public void testEstimateSmall() {
        System.out.println("testEstimate");

        int n = 24;
        double[] params = {0.11, 0.05002, 0.0205, 0.0001};
        int m = params.length-1;

        PolynomialPhaseSignal siggen = new PolynomialPhaseSignal(n, new Gaussian(0, 0.0), params);
        siggen.generateReceivedSignal();

        HAFSimpleDiversity inst = new HAFSimpleDiversity(m,n,5,7);

        double[] p = inst.estimate(siggen.real(), siggen.imag());

        System.out.println(VectorFunctions.print(p));

        assertTrue(VectorFunctions.distance_between(p, params) < 0.001);
    }
    
    /**
     * Test of estimate method, of class HAFSimpleDiversityTest.
     */
    @Test
    public void testEstimateBig() {
        System.out.println("testEstimate");

        int n = 24;
        double[] params = {0.11, 0.1, 0.1, 1.0/24};
        int m = params.length-1;

        PolynomialPhaseSignal siggen = new PolynomialPhaseSignal(n, new Gaussian(0, 0.0), params);

        siggen.generateReceivedSignal();
        
        HAFSimpleDiversity inst = new HAFSimpleDiversity(m,n,6,7);

        double[] p = inst.estimate(siggen.real(), siggen.imag());

        System.out.println(VectorFunctions.print(p));

        assertTrue(VectorFunctions.distance_between(p, params) < 0.001);
    }


}
