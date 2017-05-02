import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;

public class tester {
	
	 
	// This test was retrieved from StackOverflow, link: http://stackoverflow.com/a/2581754
	@Test
    public void testSortByValue()
    {
        Random random = new Random(System.currentTimeMillis());
        Map<String, Integer> testMap = new HashMap<String, Integer>(1000);
        for(int i = 0 ; i < 1000 ; ++i) {
            testMap.put( "SomeString" + random.nextInt(), random.nextInt());
        }

        testMap = MapUtil.sortByValue( testMap );
        Assert.assertEquals( 1000, testMap.size() );

        Integer previous = null;
        for(Map.Entry<String, Integer> entry : testMap.entrySet()) {
            Assert.assertNotNull( entry.getValue() );
            if (previous != null) {
                Assert.assertTrue( entry.getValue() >= previous );
            }
            previous = entry.getValue();
        }
    }
	
	// test for no coordinates.
	@Test
	public void testNoCoords() {
		String input = "{“”}";
		barrenLand bl = new barrenLand();
		
		bl.parseInput(input);
		bl.makeMap();
		bl.analyzeMap();
		//bl.printResult();
		
		assertEquals("240000", bl.printResult());
	}
	
	// test for one area of barren land
	@Test
	public void testOneCoord() {
		String input = "{“0 292 399 307”}";
		barrenLand bl = new barrenLand();
		
		bl.parseInput(input);
		bl.makeMap();
		bl.analyzeMap();
		//bl.printResult();
		
		assertEquals("116800 116800", bl.printResult());
	}
	
	// test for multiple areas of barren land
	@Test
	public void testMultipleCoords() {
		String input = "{“48 192 351 207”, “48 392 351 407”, “120 52 135 547”, “260 52 275 547”}";
		barrenLand bl = new barrenLand();
		
		bl.parseInput(input);
		bl.makeMap();
		bl.analyzeMap();
		//bl.printResult();
		
		assertEquals("22816 192608", bl.printResult());
	}

}
