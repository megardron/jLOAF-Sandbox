package agent.lfo;

import java.util.Random;

import sandbox.Creature;
import sandbox.Direction;
import sandbox.MovementAction;
import sandbox.Obstacle;

public class SmartRandomExpert extends SmartExpert{

	private Random r;
	
	public SmartRandomExpert(int size, Creature c) {
		super(size, c);
		r = new Random(0);
		
		int world[][] = box.getWorld();
		world[4][2] = Obstacle.DIRT.ordinal();
		world[7][1] = Obstacle.DIRT.ordinal();
		
		box.setWorld(world);
		box.init();
		
	}

	@Override
	public MovementAction testAction(Creature c) {
		MovementAction action = this.nextSmartDirection(c);
		if (action != null){
			return action;
		}
		int value = r.nextInt(Direction.values().length);
		return calculateMovement(c.getDir(), Direction.values()[value]);
	}

}
