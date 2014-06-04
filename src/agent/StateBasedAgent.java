package agent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import sandbox.Creature;
import sandbox.MovementAction;
import sandbox.Sandbox;

public abstract class StateBasedAgent {

	protected List<CreatureState> state;
	protected Sandbox box;
	protected int id;
	
	public StateBasedAgent(int size, Creature c){
		state = new ArrayList<CreatureState>();
		box = new Sandbox(size);
		if (c == null){
			id = box.addCreature(createCreature());
		}else{
			id = box.addCreature(c);
		}
		box.init();
		resetInternalState();
	}
	
	public abstract MovementAction testAction(Creature c);
	protected abstract Creature createCreature();
	
	protected abstract void resetInternalState();


	public void runAgent(int iterations){
		for (int i = 0; i < iterations; i++){
			Creature c = box.getCreature().get(id);
			MovementAction action = testAction(c);
			boolean hasTouched = (boolean)c.getSensor().getSense(StateBasedAgentSenseConfig.TOUCH).getValue();
			double sonar = (double)c.getSensor().getSense(StateBasedAgentSenseConfig.SONAR).getValue();
			int sound = (int)c.getSensor().getSense(StateBasedAgentSenseConfig.SOUND).getValue();
			state.add(new CreatureState(hasTouched, sonar, sound, action));
			box.takeAction(id, action);
		}
	}
	
	public void saveTrace(String filename){
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
			for  (CreatureState s : state){
				int touch = (s.isHasTouched()) ? 1 : 0;
				double sonar = s.getSonar();
				int sound = s.getSound();
				writer.append(touch + "|" + sonar + "|" + sound + "|" + s.getAction().toString() + "\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
