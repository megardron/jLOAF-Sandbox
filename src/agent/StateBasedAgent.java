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
	
	public StateBasedAgent(int size){
		state = new ArrayList<CreatureState>();
		box = new Sandbox(size);
		id = box.addCreature(createCreature());
	}
	
	public abstract MovementAction testAction(Creature c);
	protected abstract Creature createCreature();

	public void runAgent(int iterations){
		for (int i = 0; i < iterations; i++){
			Creature c = box.getCreature().get(id);
			MovementAction action = testAction(c);
			state.add(new CreatureState(c.isHasTouched(), c.getSonar(), c.getSound(), action));
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
