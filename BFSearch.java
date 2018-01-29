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
	public BFSearch(int sizeX, int sizeY,int startX,int startY, char obs[][]) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		grid = new Node[sizeX][sizeY];
		
		for(int i = 1; i < sizeX; i++) {
			for(int j = 1; j < sizeY; j++) {
				
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
	
	List<Node> search(Node start) {
		
		LinkedList<Node> visitedNodes = new LinkedList<Node>();
		LinkedList<Node> toVisit = new LinkedList<Node>();
		
		start.parentNode = null;
		toVisit.push(start);
		
		while(!toVisit.isEmpty()) {
			Node node = toVisit.pop();
			if (obs[node.x][node.y] == 'd') {
				dirtCounter--;
				if(dirtCounter < 1) {
					return goalPath(node);
				}
			}
			else {
				Iterator<Node> i = node.neighbours.iterator();
				while (i.hasNext()) {
					Node nextTile = i.next();
					
					if(!visitedNodes.contains(nextTile) && !toVisit.contains(nextTile)) {
						nextTile.parentNode = node;
						toVisit.push(nextTile);
					}
				}
			}
			
		}
		
		return null;
	}
}
