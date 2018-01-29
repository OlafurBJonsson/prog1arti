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
	List<Node> orderOfNodes = new ArrayList<Node>(); //This is the frontier
}

public class Astar {
	private Node grid[][];
	private char obs[][];
	private int sizex, sizey, destx, desty;
	
	public Astar(int sizex, int sizey,int startx,int starty, char obs[][])
	{
		//Instantiate the class
		this.sizex = sizex;
		this.sizey = sizey;
		this.obs = obs;
		this.destx = 4; //CHANGE THIS
		this.desty = 4;
		grid = new Node[sizex][sizey];
		List<Node> path = new ArrayList<Node>();
		
		//Get cost for all nodes
		for(int i = 0; i < this.sizex; i++)
		{
			for(int j = 0; j < this.sizey; j++)
			{
				grid[i][j] = getCost(i, j);
				System.out.println(grid[i][j].x+ ", " + grid[i][j].y + " Cost: " + grid[i][j].cost);
			}
		}
		
		//Find all neighbours for every Node.
		for(int i = 0; i < this.sizex; i++)
		{
			for(int j = 0; j < this.sizey; j++)
			{
				grid[i][j].neighbours = getNeighbours(grid[i][j]);
				System.out.println(grid[i][j].x+ ", " + grid[i][j].y + " Cost: " + grid[i][j].cost);
			}
		}
		path = findPath(0,0,4,4);
		for(Node item:path)
		{
			System.out.println("Next node: " + item.x + ", " + item.y);
		}
		
		
		
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
	
	private int distance (int x, int y, int destx, int desty) {
		return 	Math.abs(x - destx) + Math.abs(y - desty);
		
	}
		
	
	List<Node> front = new ArrayList<Node>();
	front.add(grid[startx-1][starty-1]);
	
	int minim = distance(front.get(front-1).x, front.get(front.size()-1).y, dirtLeft.get(0).x, dirtLeft.get(0).y);
	int index = 0;
	while (front.length < dirtleft.size() ) {
		for (i = 0, i< dirtleft.size(), i++) {
			if (distance(front.get(0).x, front.get(0).y, dirtLeft.get(0).x, dirtLeft.get(0).y) < minim) {
				index = i;
			}
		}
		front.add(grid[startx-1][starty-1]);
	}
}