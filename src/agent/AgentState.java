package agent;

import sandbox.MovementAction;

public abstract class AgentState {

	private MovementAction action;
	
	public AgentState(MovementAction action){
		this.action = action;
	}
	
	public MovementAction getAction(){
		return action;
	}
}
