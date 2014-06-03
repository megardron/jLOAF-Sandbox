package agent;

import sandbox.Creature;
import sandbox.Direction;
import sandbox.MovementAction;

public class InputBasedAgent extends StateBasedAgent{
	
	public InputBasedAgent(int size, Creature c) {
		super(size, c);
	}

	private boolean isTurnRightState;
	
	@Override
	public MovementAction testAction(Creature c) {
		MovementAction action = null;
		
		boolean tch = c.isHasTouched();
		double snr = c.getSonar();
		int snd = c.getSound();
		
		if (tch && snd != 2 && !isTurnRightState){
			action = MovementAction.BACKWARD;
		}else if(!tch && snr < 2 && snd != 2 && !isTurnRightState){
			action = MovementAction.REVERSE; // Reverse
		}else if (!tch && snr >= 2 && snr < 3 && snd != 2 && !isTurnRightState){
			action = MovementAction.TURN_LEFT;
		}else if (!tch && snr >= 3 && snd != 2 && !isTurnRightState){
			action = MovementAction.FORWARD;
		}else if (tch && snd != 1 && isTurnRightState){
			action = MovementAction.BACKWARD;
		}else if(!tch && snr < 2 && snd != 1 && isTurnRightState){
			action = MovementAction.REVERSE; // Reverse
		}else if (!tch && snr >= 2 && snr < 3 && snd != 1 && isTurnRightState){
			action = MovementAction.TURN_RIGHT;
		}else if (!tch && snr >= 3 && snd != 1 && isTurnRightState){
			action = MovementAction.FORWARD;
		}else if (tch && snd == 2 && !isTurnRightState){
			action = MovementAction.BACKWARD;
			isTurnRightState = !isTurnRightState;
		}else if (tch && snd == 1 && isTurnRightState){
			action = MovementAction.BACKWARD;
			isTurnRightState = !isTurnRightState;
		}else if (!tch && snr < 2 && snd == 2 && !isTurnRightState){
			action = MovementAction.REVERSE; // Reverse
			isTurnRightState = !isTurnRightState;
		}else if (!tch && snr < 2 && snd == 1 && isTurnRightState){
			action = MovementAction.REVERSE; // Reverse
			isTurnRightState = !isTurnRightState;
		}else if (!tch && snr >= 3 && snd == 2 && !isTurnRightState){
			action = MovementAction.FORWARD;
			isTurnRightState = !isTurnRightState;
		}else if (!tch && snr >= 3 && snd == 1 && isTurnRightState){
			action = MovementAction.FORWARD;
			isTurnRightState = !isTurnRightState;
		}else if (!tch && snr >= 2 && snr < 3 && snd == 2 && !isTurnRightState){
			action = MovementAction.TURN_RIGHT;
			isTurnRightState = !isTurnRightState;
		}else if (!tch && snr >= 2 && snr < 3 && snd == 1 && isTurnRightState){
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
