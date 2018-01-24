import java.util.Collection;

public class SmartAgent implements Agent{
    public void init(Collection<String> percepts) {
    	
    }
    public String nextAction(Collection<String> percepts) {
    	
    	System.out.print("Perceiving:");
		for(String percept:percepts) {
			System.out.print("'" + percept + "', ");
		}
		System.out.println("");
		String[] actions = { "TURN_ON", "TURN_OFF", "TURN_RIGHT", "TURN_LEFT", "GO", "SUCK" };
		return actions[4];
    }
	
}
