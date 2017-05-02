import java.io.IOException;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class testRunner {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(tester.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		
		barrenLand bl = new barrenLand();
		
		try {
			System.out.println("Please enter your own coordinates: ");
			bl.getInput();
		} catch (IOException e) {
			e.printStackTrace();
		}
		bl.makeMap();
		bl.analyzeMap();
		bl.printResult();
	}
}
