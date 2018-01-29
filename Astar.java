import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

class Node
{
	int cost, x, y;
	boolean obstacle = false;
	List<Node> neighbours = new ArrayList<Node>();
	List<Node> orderOfNodes = new ArrayList<Node>();
}


public class Astar {
	private Node grid[][];
	private char obs[][];
	private int sizex, sizey, destx, desty;
	private char direction = 'N';
	private int posx, posy;
	List<Node> finalPath = new ArrayList<Node>();
	private String[] actions = { "TURN_ON", "TURN_OFF", "TURN_RIGHT", "TURN_LEFT", "GO", "SUCK" };
	public Astar(int sizex, int sizey,int startx,int starty, char obs[][])
	{
		//Instantiate the class
		this.sizex = sizex;
		this.sizey = sizey;
		this.obs = obs;
		this.destx = 4; //CHANGE THIS
		this.desty = 4;
		grid = new Node[sizex][sizey];
		
		List<Node> dirtLeft = new ArrayList<Node>();
		
		
		
		getCost();

		
		finalPath.add(grid[startx-1][starty-1]);
		Node curr = grid[startx-1][starty-1];
		for(int i = 0; i < this.sizex;i++)
		{
			for(int j = 0; j < this.sizey; j++)
			{
				if(obs[i][j] == 'd')
				{
					dirtLeft.add(grid[i][j]);
				}
			}
		}
		while(!dirtLeft.isEmpty())
		{
			List<Node> shortestPath = new ArrayList<Node>();
			int index = 0;
			for(int i = 0; i < dirtLeft.size();i++)
			{
				this.destx = dirtLeft.get(i).x;
				this.desty = dirtLeft.get(i).y;
				getCost();
				List<Node> currentPath = findPath(curr.x, curr.y, dirtLeft.get(i).x, dirtLeft.get(i).y);
				if(currentPath.size() < shortestPath.size() || shortestPath.size() == 0)
					{
						shortestPath = currentPath;
						index = i;
					}
			}
			dirtLeft.remove(index);
			for(Node i:shortestPath)
			{
				finalPath.add(i);
			}
			curr = finalPath.get(finalPath.size()-1);
		}
		
		for(Node item:finalPath)
		{
			System.out.println("Next node: " + item.x + ", " + item.y);
		}
		
		
		//translate the path to a language the robot understand. 
		
		
		
		
	}
	
	public List<String> getRoute()
	{
		List<String> ret = new ArrayList<String>();
		while(!finalPath.isEmpty()) {
			//TODO: Create homing logic
			while(!finalPath.isEmpty())
			if(posx < finalPath.get(0).x) {
				if(direction != 'E') ret.add(changeDirection('E'));
				else ret.add(go());
			}
			if(posx > finalPath.get(0).x) {
				if(direction != 'W')  ret.add(changeDirection('W'));
				else  ret.add(go());
			}
			if(posy < finalPath.get(0).y) {
				if(direction != 'N')  ret.add(changeDirection('N'));
				else  ret.add(go());
			}
			if(posy > finalPath.get(0).y) {
				if(direction != 'S')  ret.add(changeDirection('S'));
				else  ret.add(go());
			}
			if(finalPath.get(0).x == posx && finalPath.get(0).y == posy)
			{
				finalPath.remove(0);
			}

			
		}
		return ret;
	}
	
	private List<Node> findPath(int x, int y, int destx, int desty)
	{
		
		List<Node> openList = new ArrayList<Node>();
		List<Node> closedList = new ArrayList<Node>();
		Node current;
		
		openList.add(grid[x][y]);
		
		while(!openList.isEmpty())
		{
			current = getFirstInOpen(openList);
			current.orderOfNodes.add(current);
			if(current.x == destx && current.y == desty) 
			{
				
				return current.orderOfNodes;
			}
			
			openList.remove(current);
			closedList.add(current);
			for(Node item:current.neighbours)
			{
				if(item.obstacle == false && closedList.contains(item) == false)
				{
					item.orderOfNodes = current.orderOfNodes;
					openList.add(item);
				}
			}
		}
		
		return null;
	}
	
	private Node getFirstInOpen(List<Node> open)
	{
		Node low = null;
		for(Node item:open)
		{
			if(low == null) low = item;
			else{
				if(item.cost < low.cost)
				{
					low = item;
				}
			}
		}
		return low;
	}
	
	private List<Node> getNeighbours(Node n)
	{
		List<Node> nl = new ArrayList<Node>();
		
		//Make sure we are checking valid grid
		
		//West neighbour
		if(n.x-1 >= 0)
		{
			if(!grid[n.x-1][n.y].obstacle) nl.add(grid[n.x-1][n.y]);
		}
		//East neighbour
		if(n.x+1 < sizex)
		{
			if(!grid[n.x+1][n.y].obstacle) nl.add(grid[n.x+1][n.y]);
		}
		//North neighbour
		if(n.y+1 < sizey)
		{
			if(!grid[n.x][n.y+1].obstacle) nl.add(grid[n.x][n.y+1]);
		}
		//South neighbour
		if(n.y-1 >= 0)
		{
			if(!grid[n.x][n.y-1].obstacle) nl.add(grid[n.x][n.y-1]);
		}
		return nl;
	}
	private void getCost()
	{
		//Get cost for all nodes
		for(int i = 0; i < this.sizex; i++)
		{
			for(int j = 0; j < this.sizey; j++)
			{
				grid[i][j] = getCost(i, j);
				//System.out.println(grid[i][j].x+ ", " + grid[i][j].y + " Cost: " + grid[i][j].cost);
				
			}
		}
		for(int i = 0; i < this.sizex;i++)
		{
			for(int j = 0; j < this.sizey; j++)
			{
				grid[i][j].neighbours = getNeighbours(grid[i][j]);
				System.out.println(grid[i][j].x+ ", " + grid[i][j].y + " Cost: " + grid[i][j].cost);
			}
		}
	}
	private Node getCost(int x, int y)
	{
		Node n = new Node();
		n.x = x;
		n.y = y;
		n.cost = Math.abs(x - destx) + Math.abs(y - desty);
		if(obs[x][y] == 'o')
		{
			n.obstacle = true;
		}
		return n;
	}
	private String turn(String turn)
	{
		if(turn == "RIGHT")
		{
			if(direction == 'N') {
				direction = 'E';
			}
			else if(direction == 'E') {
				direction = 'S';
			}
			else if(direction == 'S') {
				direction = 'W';
			}
			else if(direction == 'W') {
				direction = 'N';
			}
			return actions[2];
		}
		else
		{
			if(direction == 'N') {
				direction = 'W';
			}
			else if(direction == 'E') {
				direction = 'N';
			}
			else if(direction == 'S') {
				direction = 'E';
			}
			else if(direction == 'W') {
				direction = 'S';
			}
			
			return actions[3];
		}
	}
	
	private String changeDirection(char c)
	{
		if(c == 'E' && (direction == 'N' || direction == 'W')) return turn("RIGHT");
		if(c == 'E' && direction == 'S') return turn("LEFT");
		if(c == 'N' && (direction == 'S' || direction == 'W')) return turn("RIGHT");
		if(c == 'N' && direction == 'E') return turn("LEFT");
		if(c == 'S' && (direction == 'N' || direction == 'E')) return turn("RIGHT");
		if(c == 'S' && direction == 'W') return turn("LEFT");
		if(c == 'W' && (direction == 'E' || direction == 'S')) return turn("RIGHT");
		if(c == 'W' && direction == 'N') return turn("LEFT");
		
		
		System.out.println("Direction not found, turning right by default");
		return actions[2];
	}
	
	private String go() {
		if(direction == 'N') {
			posy++;
		}
		if(direction == 'E') {
			posx++;
		}
		if(direction == 'S') {
			posy--;
		}
		if(direction == 'W') {
			posx--;
		}
		return actions[4];

	}
}

