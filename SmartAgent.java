import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmartAgent implements Agent {

	private int homeX, homeY;
	private int sizeX, sizeY;
	private int dirtCounter;
	
	
	public void init(Collection<String> percepts) {
		/*
		Possible percepts are:
		- "(SIZE x y)" denoting the size of the environment, where x,y are integers
		- "(HOME x y)" with x,y >= 1 denoting the initial position of the robot
		- "(ORIENTATION o)" with o in {"NORTH", "SOUTH", "EAST", "WEST"} denoting the initial orientation of the robot
		- "(AT o x y)" with o being "DIRT" or "OBSTACLE" denoting the position of a dirt or an obstacle
		Moving north increases the y coordinate and moving east increases the x coordinate of the robots position.
		The robot is turned off initially, so don't forget to turn it on.
		 */
    	
		Pattern perceptNamePattern = Pattern.compile("\\(\\s*([^\\s]+).*");
		for (String percept:percepts) {
			Matcher perceptNameMatcher = perceptNamePattern.matcher(percept);
			if (perceptNameMatcher.matches()) {
				String perceptName = perceptNameMatcher.group(1);
				if (perceptName.equals("HOME")) {
					Matcher m = Pattern.compile("\\(\\s*HOME\\s+([0-9]+)\\s+([0-9]+)\\s*\\)").matcher(percept);
					if (m.matches()) {
						System.out.println("Home is at " + m.group(1) + "," + m.group(2));
						homeX = Integer.parseInt(m.group(1));
						homeY = Integer.parseInt(m.group(2));
					}
				} 
				else if (perceptName.equals("ORIENTATION")) {
					Matcher m = Pattern.compile("\\(\\s*ORIENTATION\\s+([A-Z]+)\\s*\\)").matcher(percept);
					if(m.matches())
					{
						System.out.println("Orientation is: "  + m.group(1));
					}
				}
				else if (perceptName.equals("AT")) {
					Matcher m = Pattern.compile("\\(\\s*AT\\s+([A-Z]+)\\s+([0-9]+)\\s+([0-9]+)\\s*\\)").matcher(percept);
					if (m.matches()) {
						System.out.println(m.group(1) + " is at: " + m.group(2) + "," + m.group(3));
						if (m.group(1).contains("DIRT")) { 
							dirtCounter++;
							System.out.println("counter is: " + dirtCounter);
						}
					}
				} 
				else if (perceptName.equals("SIZE")) {
					Matcher m = Pattern.compile("\\(\\s*SIZE\\s+([0-9]+)\\s+([0-9]+)\\s*\\)").matcher(percept);
					if (m.matches()) {
						System.out.println("Size is " + m.group(1) + "," + m.group(2));
						sizeX = Integer.parseInt(m.group(1));
						sizeY = Integer.parseInt(m.group(2));
					}
				} 
				else {
					System.out.println("Other: " + percept);
				}
			} else {
				System.err.println("strange percept that does not match pattern: " + percept);
			}
		}
    }
	
    public String nextAction(Collection<String> percepts) {
    	
    	return "";
    }
}
