package agent;

import sandbox.Creature;
import sandbox.Direction;
import sandbox.MovementAction;

public class InputBasedAgent extends StateBasedAgent{

	public static void main(String args[]){
		InputBasedAgent agent = new InputBasedAgent(20);
		agent.runAgent(100);
		agent.saveTrace("E:\\CODING\\SandboxAgent\\GGGG");
	}
	
	public InputBasedAgent(int size) {
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
	public MovementAction testAction(Creature c) {
		MovementAction action = null;
		
		boolean tch = c.isHasTouched();
		double snr = c.getSonar();
		int snd = c.getSound();
		
		if (tch && snd != 2 && !isTurnRightState){
			action = MovementAction.BACKWARD;
		}else if(!tch && snr < 2 && snd != 2 && !isTurnRightState){
			action = MovementAction.BACKWARD; // Reverse
		}else if (!tch && snr >= 2 && snr < 3 && snd != 2 && !isTurnRightState){
			action = MovementAction.TURN_LEFT;
		}else if (!tch && snr >= 3 && snd != 2 && !isTurnRightState){
			action = MovementAction.FORWARD;
		}else if (tch && snd != 1 && isTurnRightState){
			action = MovementAction.BACKWARD;
		}else if(!tch && snr < 2 && snd != 1 && isTurnRightState){
			action = MovementAction.BACKWARD; // Reverse
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
			action = MovementAction.BACKWARD; // Reverse
			isTurnRightState = !isTurnRightState;
		}else if (!tch && snr < 2 && snd == 1 && isTurnRightState){
			action = MovementAction.BACKWARD; // Reverse
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

}
