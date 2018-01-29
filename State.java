import java.util.List;

public class State {
	
	private String orientation;
	private int x, y;
	private boolean hasDirt;
	//private boolean isOn;
	//private boolean isGoal = false;
	private List<String> legalMoves;
	
	public void setState(int x, int y, String orientation, boolean hasDirt) {
		this.hasDirt = hasDirt;
		//this.isOn = isOn;
		this.orientation = orientation;
		this.x = x;
		this.y = y;
	}
	
	public boolean hasDirt() {
		return hasDirt;
	}
	
	public List<String> getLegalMoves (MyMap[][] map, int sizeX, int sizeY) {
		
		legalMoves.add("TURN_LEFT");
		legalMoves.add("TURN_RIGHT");
		legalMoves.add("GO");
		
		switch (orientation) {
		case "NORTH":
			if (y == sizeY || map[x][y+1].isObsticle) 	legalMoves.remove("GO");
			if (x == sizeX || map[x+1][y].isObsticle) 	legalMoves.remove("TURN_RIGHT");
			if (x == 1 || map[x-1][y].isObsticle) 		legalMoves.remove("TURN_LEFT");
			
			return legalMoves;
			
		case "SOUTH":
			if (y == 1 || map[x][y-1].isObsticle) 		legalMoves.remove("GO");
			if (x == 1 || map[x-1][y].isObsticle) 		legalMoves.remove("TURN_RIGHT");
			if (x == sizeX || map[x+1][y].isObsticle) 	legalMoves.remove("TURN_LEFT");
			
			return legalMoves;
		case "EAST":
			if (x == sizeX || map[x+1][y].isObsticle) 	legalMoves.remove("GO");
			if (y == sizeY || map[x][y+1].isObsticle) 	legalMoves.remove("TURN_RIGHT");
			if (y == 1 || map[x][y-1].isObsticle) 		legalMoves.remove("TURN_LEFT");
				
			return legalMoves;
		case "WEST":
			if (x == 1 || map[x-1][y].isObsticle) 		legalMoves.remove("GO");
			if (y == 1 || map[x][y-1].isObsticle) 		legalMoves.remove("TURN_RIGHT");
			if (y == sizeY || map[x][y+1].isObsticle) 	legalMoves.remove("TURN_LEFT");
				
			return legalMoves;
			
		default:
			return legalMoves;
		}
	}
	 public String getState () {
		 
		return "CLEAN x y"; 
	 }
}
