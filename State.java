import java.util.List;

public class State {
	
	private String orientation;
	private int x, y;
	private int[][] dirtPos;
	private boolean isOn;
	private boolean isGoal = false;
	private List legalMoves;
	
	public void setState(int x, int y, String orientation, boolean isOn, int [][] dirtPos) {
		this.dirtPos = dirtPos;
		this.isOn = isOn;
		this.orientation = orientation;
		this.x = x;
		this.y = y;
	}
	
	public List getLegalMoves (boolean[][] obs, int sizeX, int sizeY) {
		switch (orientation) {
		case "NORTH":
			if (sizeY == y || obs[x][y+1] == true) {
				legalMoves.add("TURN_LEFT");
				legalMoves.add("TURN_RIGHT");
			}

			return legalMoves;
		case "SOUTH":
			if (sizeY == 1 || obs[x][y-1] == true) {
				legalMoves.add("TURN_LEFT");
				legalMoves.add("TURN_RIGHT");
			}
			return legalMoves;
		case "EAST":
			if (sizeX == x || obs[x+1][y] == true) {
				legalMoves.add("TURN_LEFT");
				legalMoves.add("TURN_RIGHT");
			}
			return legalMoves;
		case "WEST":
			if (sizeX == 1 || obs[x-1][y] == true) {
				legalMoves.add("TURN_LEFT");
				legalMoves.add("TURN_RIGHT");
			}
			return legalMoves;
			
		default:
			legalMoves.add("TURN_LEFT");
			legalMoves.add("TURN_RIGHT");
			legalMoves.add("GO");
		
			return legalMoves;
		}
	}
	 public String getState () {
		 
		return "CLEAN x y"; 
	 }
}
