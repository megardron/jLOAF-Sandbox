package agent;

import java.util.ArrayList;
import java.util.List;

import agent.backtracking.CreatureState;

import sandbox.Creature;
import sandbox.MovementAction;
import sandbox.Sandbox;

public abstract class AbstractSandboxAgent {

	protected List<CreatureState> state;
	protected Sandbox box;
	protected int id;

	public AbstractSandboxAgent(int size, Creature c) {
		state = new ArrayList<CreatureState>();
		box = new Sandbox(size);
		if (c == null){
			id = box.addCreature(createCreature());
		}else{
			id = box.addCreature(c);
		}
		box.init();
	}

	public abstract MovementAction testAction(Creature c);

	protected abstract Creature createCreature();

	public abstract void runAgent(int iterations);
	
	public abstract void saveTrace(String filename);

}