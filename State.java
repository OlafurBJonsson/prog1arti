import java.util.List;

public class State {
	
	private String orientation;
	private int x, y;
	//private boolean isOn;
	//private boolean isGoal = false;
	private List<String> legalMoves;
	
	public State(int x, int y, String orientation) {
		this.orientation = orientation;
		this.x = x;
		this.y = y;
	}

	
	public List<String> getLegalMoves (char[][] obs, int sizeX, int sizeY) {
		
		legalMoves.add("TURN_LEFT");
		legalMoves.add("TURN_RIGHT");
		legalMoves.add("GO");
		
		switch (orientation) {
		case "NORTH":
			if (y == sizeY || obs[x][y+1] == 'o') 	legalMoves.remove("GO");
			if (x == sizeX || obs[x+1][y] == 'o') 	legalMoves.remove("TURN_RIGHT");
			if (x == 1 || obs[x-1][y] == 'o') 		legalMoves.remove("TURN_LEFT");
			
			return legalMoves;
			
		case "SOUTH":
			if (y == 1 || obs[x][y-1] == 'o') 		legalMoves.remove("GO");
			if (x == 1 || obs[x-1][y] == 'o') 		legalMoves.remove("TURN_RIGHT");
			if (x == sizeX || obs[x+1][y] == 'o') 	legalMoves.remove("TURN_LEFT");
			
			return legalMoves;
		case "EAST":
			if (x == sizeX || obs[x+1][y] == 'o') 	legalMoves.remove("GO");
			if (y == sizeY || obs[x][y+1] == 'o') 	legalMoves.remove("TURN_RIGHT");
			if (y == 1 || obs[x][y-1] == 'o') 		legalMoves.remove("TURN_LEFT");
				
			return legalMoves;
		case "WEST":
			if (x == 1 || obs[x-1][y] == 'o') 		legalMoves.remove("GO");
			if (y == 1 || obs[x][y-1] == 'o') 		legalMoves.remove("TURN_RIGHT");
			if (y == sizeY || obs[x][y+1] == 'o') 	legalMoves.remove("TURN_LEFT");
				
			return legalMoves;
			
		default:
			return legalMoves;
		}
	}
	 public String getState () {
		 
		return "CLEAN x y"; 
	 }
}
