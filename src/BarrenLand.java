import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class barrenLand {
	static int maxX = 400; 						// X bound
	static int maxY = 600; 						// Y bound
	static int[][] field; 						// A 2D array of the farm
	static Queue<int[]> barrenCoordinates; 		// A queue to hold int[] of the coordinates of the barren land bounds
	static Queue<int[]> fieldCoordinates; 		// A queue to hold int[] of coordinates to search in our BFS algorithm
	static HashMap<Integer, Integer> fieldSize; // Holds key value pairs of the different separated lands and their respective sizes
	
	/* initialize class variables */
	barrenLand() {
		field = new int[maxX][maxY];
		barrenCoordinates = new LinkedList<int[]>();
		fieldCoordinates = new LinkedList<int[]>();
		fieldSize = new HashMap<Integer, Integer>();
	}

	/*
	 * Read coordinates from stdin.
	 * This function and parseInput function separate so automated tests can be run.
	 * example inputs: 
	 * 		{“0 292 399 307”} 
	 * 		{“48 192 351 207”, “48 392 351 407”, “120 52 135 547”, “260 52 275 547”}
	 */
	public void getInput() {
		String input = null;
		Scanner sc = new Scanner(System.in);
		
		input = sc.nextLine();
		sc.close();
		
		if (input.length() > 0) {
			parseInput(input);
		}
		else {
			System.out.println("ERROR: No input detected. Exiting program...");
			System.exit(0);
		}
	}
	
	/* 
	 * Split string into parts by comma, then by spaces. Remove quotes and curly braces.
	 * Store pieces as coordinates either in a string/int arrays in an string/int array.
	 * 
	 * Small note: Looks like the example input has weird double quotes. Will need to 
	 * parse for those as well as double quotes.
	 */
	public void parseInput(String input) {
		
		// Whitelist regex.
		if (!input.matches("^[ 0-9\"“”{},]+$")) {
			System.out.println("Error: input has invalid characters. Exiting program...");
			System.exit(0);
		} 
		
		// get rid of unneeded characters
		input = input.replaceAll("[\"“”{}]", "");
		
		// spaces after commas will mess with the split and cause errors
		input = input.replace(", ", ",");
		
		System.out.println("input parsed: " + input);
		
		String[] splitString = input.split(","); 
		for (String s: splitString) {
			
			String[] splitCoords = s.split(" ");
			
			int[] barrenBounds = {Integer.parseInt(splitCoords[0]), Integer.parseInt(splitCoords[1]), 
					Integer.parseInt(splitCoords[2]), Integer.parseInt(splitCoords[3])};
			
			barrenCoordinates.add(barrenBounds);
			
		}
	}
	
	/* 
	 * Generate an int[400][600] map. 
	 * This will be the reference map. 
	 * Fertile land will be marked with '0'
	 * Barren land will be marked with '1' 
	 * Parsed areas will be marked later with an value > 1
	 */
	public void makeMap() {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				field[i][j] = 0;
			}
		}
		
		// add in barren lands into matrix
		while (!barrenCoordinates.isEmpty()) {
			int[] bounds = barrenCoordinates.remove();
			int xMin = bounds[0];
			int xMax = bounds[2];
			int yMin = bounds[1];
			int yMax = bounds[3];
			
			for (int i = xMin; i <= xMax; i++) {
				for (int j = yMin; j <= yMax; j++) {
					field[i][j] = 1;
				}
			}
		}
	}
	
	/*
	 * Breadth first search type algorithm being used. Look for a land not analyzed,
	 * add adjacent fertile lands to queue.
	 * O(|V| * |E|) complexity for this algorithm. Not sure that there is a faster way.
	 * A piece of land is fertile and undiscovered if 0, barren if 1, and a piece of a
	 * discovered field if > 1.
	 */
	public void analyzeMap() {
		int i = 0;
		int j = 0;
		int fieldNumber = 1;
		
		while ((i < maxX) && (j < maxY)) {
			// if field[i][j] == 0, then it hasn't been visited. Add it
			// to the queue and start a search of nearby squares.
			if (fieldCoordinates.isEmpty()) {
				if (field[i][j] == 0) {
					
					fieldNumber++;
					fieldSize.put(fieldNumber, 0);
					
					int coord[] = {i, j};
					fieldCoordinates.add(coord);
				}
				
				// iterate to next space
				if (i == (maxX - 1)) {
					i = 0;
					j++;
				}
				else {
					i++;
				}
			}
			// search nearby squares for undiscovered pieces of the field
			else {
				//System.out.println("Queue size: " + fieldCoordinates.size());
				int[] coords = fieldCoordinates.remove();
				int x = coords[0];
				int y = coords[1];
				
				if (field[x][y] == 0) {
					if ((x > 0) && (field[x-1][y] == 0)) {
						int[] temp = new int[] {x-1, y};
						fieldCoordinates.add(temp);
					}
					if ((x < maxX-1) && (field[x+1][y] == 0)) {
						int[] temp = new int[] {x+1, y};
						fieldCoordinates.add(temp);
					}
					if ((y > 0) && (field[x][y-1] == 0)) {
						int[] temp = new int[] {x, y-1};
						fieldCoordinates.add(temp);
					}
					if ((y < maxY-1) && (field[x][y+1] == 0)) {
						int[] temp = new int[] {x, y+1};
						fieldCoordinates.add(temp);
					}
					
					fieldSize.put(fieldNumber, (fieldSize.get(fieldNumber))+1);
					field[x][y] = fieldNumber;
					
				}
			}
		}
		printResult();
	}
	
	/*
	 * Get all key value pairs from our hash map and list in
	 * order of smallest -> largest 
	 * 
	 * TODO: order output.
	 */
	public void printResult() {
		Set<?> set = fieldSize.entrySet();
		Iterator<?> iterator = set.iterator();
		while(iterator.hasNext()) {
		Map.Entry mentry = (Map.Entry)iterator.next();
			System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
			System.out.println(mentry.getValue());
		}
	}
}
