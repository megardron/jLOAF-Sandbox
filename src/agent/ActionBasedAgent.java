package agent;

import sandbox.Creature;
import sandbox.Direction;
import sandbox.MovementAction;

public class ActionBasedAgent extends StateBasedAgent{

	public ActionBasedAgent(int size){
		super(size);			
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

	@Override
	protected void resetInternalState() {
		isTurnRightState = false;
	}
}
