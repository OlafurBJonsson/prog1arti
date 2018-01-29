import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BFSearch {
	private int dirtCounter;
	
	public class Node {
		
		int pathCost;
		List<Node> nextNodes;
		Node parentNode;
		State state;
		String statePath;
		
		public void setState(State state) {
			this.state = state;
		}
		
	}
	
	BFSearch(int dirtCounter, MyMap[][] map) {
		this.dirtCounter = dirtCounter;
	}
	
	List<Node> goalPath(Node node) {
		LinkedList<Node> path = new LinkedList<Node>();
		
		while (node.parentNode != null) {
			path.addFirst(node);
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
			if (node.state.hasDirt()) {
				dirtCounter--;
				if(dirtCounter < 1) {
					return goalPath(node);
				}
			}
			else {
				Iterator<Node> i = node.nextNodes.iterator();
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
