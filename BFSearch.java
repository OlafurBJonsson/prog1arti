import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BFSearch {
	private Node grid[][];
	private int dirtCounter;
	private char[][] obs;
	private int sizeX, sizeY;
	
	public class Node {
		int x, y;
		boolean obstacle;
		List<Node> neighbours = new ArrayList<Node>();
		Node parentNode;
		State state; 
		
		Node(int x, int y, String orientation){
			this.x = x;
			this.y = y;
			
			state = new State(x, y, orientation);
		}
		
	}
	
	//Constructor
	public BFSearch(int sizeX, int sizeY,int startX,int startY, String startOrientation, char obs[][]) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		grid = new Node[sizeX][sizeY];
		this.obs = obs;
		
		//Set Nodes
		for(int i = 0; i < sizeX; i++) {
			for(int j = 0; j < sizeY; j++) {
				grid[i][j]= setNode(i,j, startOrientation);
			}
		}
		
		//Get neighbours
		for(int i = 0; i < sizeX; i++) {
			for(int j = 0; j < sizeY; j++) {
				grid[i][j].neighbours = getNeighbours(grid[i][j]);
			}
		}
	}
	
	List<Node> goalPath(Node node) {
		LinkedList<Node> path = new LinkedList<Node>();
		
		while (node.parentNode != null) {
			path.push(node);
			node = node.parentNode;
		}
		return path;
	}
	
	List<Node> search(int x, int y) {
		
		LinkedList<Node> visitedNodes = new LinkedList<Node>();
		LinkedList<Node> toVisit = new LinkedList<Node>();
		
		grid[x][y].parentNode = null;
		toVisit.push(grid[x][y]);
		
		while(!toVisit.isEmpty()) {
			Node node = toVisit.pop();
			if (obs[node.x][node.y] == 'd') {
				obs[node.x][node.y] = 'c';
				dirtCounter--;
				if(dirtCounter < 1) {
					return goalPath(node);
				}
			}
			else {
				for(Node nextTile:node.neighbours) {
					if(!visitedNodes.contains(nextTile) && !toVisit.contains(nextTile)) {
						nextTile.parentNode = node;
						toVisit.push(nextTile);
					}
				}
				/*Iterator<Node> i = node.neighbours.iterator();
				while (i.hasNext()) {
					Node nextTile = i.next();
					
					if(!visitedNodes.contains(nextTile) && !toVisit.contains(nextTile)) {
						nextTile.parentNode = node;
						toVisit.push(nextTile);
					}
				}*/
			}
			
		}
		
		return null;
	}
	
	private Node setNode(int x, int y, String orientation) {
		
		Node n = new Node(x, y, orientation);
		if(obs[x][y] == 'o') n.obstacle = true;
		return n;
	}
	private List<Node> getNeighbours(Node n)
	{
		List<Node> nl = new ArrayList<Node>();
		
		//Make sure we are checking valid grid
		
		//West neighbour
		if(n.x-1 >= 0)
		{
			if(!grid[n.x-1][n.y].obstacle) { 
				nl.add(grid[n.x-1][n.y]);
				grid[n.x-1][n.y].state.setOrientation("WEST");
			}
		}
		//East neighbour
		if(n.x+1 < sizeX)
		{
			if(!grid[n.x+1][n.y].obstacle) {
				nl.add(grid[n.x+1][n.y]);
				grid[n.x+1][n.y].state.setOrientation("EAST");
			}
		}
		//North neighbour
		if(n.y+1 < sizeY)
		{
			if(!grid[n.x][n.y+1].obstacle) {
				nl.add(grid[n.x][n.y+1]);
				grid[n.x][n.y+1].state.setOrientation("NORTH");
			}
		}
		//South neighbour
		if(n.y-1 >= 0)
		{
			if(!grid[n.x][n.y-1].obstacle) {
				nl.add(grid[n.x][n.y-1]);
				grid[n.x][n.y-1].state.setOrientation("SOUTH");
			}
		}
		return nl;
	}
}
