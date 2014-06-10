package agent.lfo;

import sandbox.Direction;
import sandbox.MovementAction;
import sandbox.sensor.Sensor;
import agent.AgentState;

public class DirtBasedAgentState extends AgentState{

	private int[] dist;
	private int[] type;
	
	public DirtBasedAgentState(MovementAction action, Sensor sensor) {
		super(action);
		
		for (String s : sensor.getSenseKeys()){
			String tag[] = s.split("-");
			Direction d = Direction.valueOf(tag[0]);
			if (s.contains(DirtBasedAgentSenseConfig.DISTANCE_SUFFIX)){
				dist[d.ordinal()] = (int) sensor.getSense(s).getValue();
			}else if (s.contains(DirtBasedAgentSenseConfig.TYPE_SUFFIX)){
				type[d.ordinal()] = (int) sensor.getSense(s).getValue();
			}
		}
	}

	public int[] getDistances(){
		return this.dist;
	}
	
	public int[] getTypes(){
		return this.type;
	}
}
