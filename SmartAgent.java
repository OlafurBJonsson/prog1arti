import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class Item{
	int y, x;
}

public class SmartAgent implements Agent {

	private int homeX, homeY;
	private int sizeX, sizeY;
	private int dirtCounter;
	private char obs[][];
	private List<Item> dirt = new ArrayList<Item>();
	private List<Item> obstacle = new ArrayList<Item>(); 
	
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
							Item d = new Item();
							d.x = Integer.parseInt(m.group(2));
							d.y = Integer.parseInt(m.group(3));
							dirt.add(d);
						}
						else{
							Item o = new Item();
							o.x = Integer.parseInt(m.group(2));
							o.y = Integer.parseInt(m.group(3));
							obstacle.add(o);
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
		obs = new char[sizeX][sizeY];
		
		for(Item item:dirt)
		{
			obs[item.x-1][item.y-1] = 'd';
		}
		for(Item item:obstacle)
		{
			obs[item.x-1][item.y-1] = 'o';
		}
		Astar a = new Astar(sizeX, sizeY, homeX, homeY, obs);
		
    }
	
    public String nextAction(Collection<String> percepts) {
	System.out.print("Perceiving:");
	for(String percept:percepts) {
		System.out.print("'" + percept + "', ");
	}
	System.out.println("");
	String[] actions = { "TURN_ON", "TURN_OFF", "TURN_RIGHT", "TURN_LEFT", "GO", "SUCK" };
	return actions[1];
    }
}
