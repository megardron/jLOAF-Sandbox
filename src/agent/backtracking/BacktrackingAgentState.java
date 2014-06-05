package agent.backtracking;

import agent.AgentState;
import sandbox.MovementAction;

public class BacktrackingAgentState extends AgentState{
	private boolean hasTouched;
	private double sonar;
	private int sound;
	
	
	public BacktrackingAgentState(boolean hasTouched, double sonar, int sound, MovementAction action) {
		super(action);
		this.hasTouched = hasTouched;
		this.sonar = sonar;
		this.sound = sound;
	}
	
	public boolean isHasTouched() {
		return hasTouched;
	}

	public double getSonar() {
		return sonar;
	}

	public int getSound() {
		return sound;
	}

}
