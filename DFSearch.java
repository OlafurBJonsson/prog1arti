import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;





public class DFSearch {
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
		public DFSearch(int sizeX, int sizeY,int startX,int startY, char obs[][]) {
			this.sizeX = sizeX;
			this.sizeY = sizeY;
			grid = new Node[sizeX][sizeY];
			
			for(int i = 1; i < sizeX; i++) {
				for(int j = 1; j < sizeY; j++) {
					
				}
			}
		}
	
	
	private List<Node> depthSearch(Node node) {
		
		LinkedList<Node> frontier = new LinkedList<Node>();
		LinkedList<Node> visitedNodes = new LinkedList<Node>();
		LinkedList<Node> toVisit = new LinkedList<Node>();
		
		while(!node.neighbours.isEmpty()) {
			visitedNodes.push(node);
			frontier.push(node);
			
			for (Node n: node.neighbours) {
				if(!visitedNodes.contains(n)) {
					return depthSearch(n);
			 	
					
				}
				
			}
			
		}
		return frontier;
	}
	
	
}
