package agent.lfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import sandbox.Creature;
import sandbox.Direction;
import sandbox.MovementAction;
import sandbox.creature.DirtBasedCreature;
import agent.AbstractSandboxAgent;
import agent.AgentState;

public abstract class DirtBasedAgent extends AbstractSandboxAgent {

	public DirtBasedAgent(int size, Creature c) {
		super(size, c);
	}

	@Override
	protected Creature createCreature() {
		return new DirtBasedCreature(2, 2, Direction.NORTH);
	}

	@Override
	public void runAgent(int iterations) {
		for (int i = 0; i < iterations; i++){
			Creature c = box.getCreature().get(id);
			MovementAction action = testAction(c);
			state.add(new DirtBasedAgentState(action, c.getSensor()));
			box.takeAction(id, action);
		}
	}
	
	protected MovementAction calculateMovement(Direction c, Direction dirt){
		
		if (dirt.equals(c)){
			return MovementAction.FORWARD;
		}else if (Math.abs(dirt.ordinal() - c.ordinal()) == 2){
			return MovementAction.BACKWARD;
		}else if (Direction.getNextDirection(MovementAction.MOVE_RIGHT, c).equals(dirt)){
			return MovementAction.MOVE_RIGHT;
		}else if (Direction.getNextDirection(MovementAction.MOVE_LEFT, c).equals(dirt)){
			return MovementAction.MOVE_LEFT;
		}
		return null;
	}

	@Override
	public void saveTrace(String filename) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
			for  (AgentState s : state){
				if (s instanceof DirtBasedAgentState){
					DirtBasedAgentState b = (DirtBasedAgentState)s;
					int dist [] = b.getDistances();
					int type[] = b.getTypes();
					String output = "";
					for (Direction d : Direction.values()){
						output += dist[d.ordinal()] + "|" + type[d.ordinal()] + "|";
					}
					output += s.getAction().name();
					writer.write(output + "\n");
				}
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
