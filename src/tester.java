
public class tester {

	// {“0 292 399 307”} 
	
	public static void main(String[] args) {
		String input = "{“48 192 351 207”, “48 392 351 407”, “120 52 135 547”, “260 52 275 547”}";
		barrenLand bl = new barrenLand();
		
		bl.parseInput(input);
		bl.makeMap();
		bl.analyzeMap();
		
		String input2 = "{“0 292 399 307”}";
		barrenLand bl2 = new barrenLand();
		
		bl2.parseInput(input2);
		bl2.makeMap();
		//bl2.printField();
		bl2.analyzeMap();
	}

}
