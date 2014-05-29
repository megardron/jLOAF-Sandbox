package agent;

import sandbox.Creature;
import sandbox.Direction;
import sandbox.MovementAction;

public class ActionBasedAgent extends StateBasedAgent{
	public static void main(String args[]){
		ActionBasedAgent agent = new ActionBasedAgent(20);
		agent.runAgent(100);
		agent.saveTrace("E:\\CODING\\SandboxAgent\\GGGG");
	}
	
	public ActionBasedAgent(int size){
		super(size);
			
		int world[][] = new int[size][size];
		for (int i = 0; i < size; i++){
			world[0][i] = 1;
			world[i][0] = 1;
			world[size - 1][i] = 1;
			world[i][size - 1] = 1;
		}
		box.setWorld(world);
		box.init();
		isTurnRightState = false;
	}
	
	private boolean isTurnRightState;
	
	@Override
	public MovementAction testAction(Creature c){
		MovementAction action = null;
		if (c.isHasTouched()){
			action = MovementAction.BACKWARD;
		}else if (!c.isHasTouched() && c.getSonar() < 2){
			action = MovementAction.REVERSE; // TODO: Add Reverse
		}else if (!c.isHasTouched() && c.getSonar() >= 3){
			action = MovementAction.FORWARD;
		}else if (!c.isHasTouched() && c.getSonar() >= 2 && c.getSonar() < 3 && isTurnRightState){
			action = MovementAction.TURN_RIGHT;
			isTurnRightState = !isTurnRightState;
		}else if (!c.isHasTouched() && c.getSonar() >= 2 && c.getSonar() < 3 && !isTurnRightState){
			action = MovementAction.TURN_LEFT;
			isTurnRightState = !isTurnRightState;
		}
		return action;
	}

	@Override
	protected Creature createCreature() {
		return new Creature(2, 2, Direction.NORTH);
	}
}
