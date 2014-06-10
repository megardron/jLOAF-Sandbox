package agent.lfo;

import java.util.Random;

import sandbox.Creature;
import sandbox.Direction;
import sandbox.MovementAction;
import sandbox.Obstacle;
import sandbox.sensor.Sensor;

public class SmartRandomExpert extends DirtBasedAgent{

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
		Sensor s = c.getSensor();
		for (Direction d : Direction.values()){
			int value = (int) s.getSense(d.name() + DirtBasedAgentSenseConfig.TYPE_SUFFIX).getValue();
			if (Obstacle.DIRT.getId() == value){
				int dist = (int) s.getSense(d.name() + DirtBasedAgentSenseConfig.DISTANCE_SUFFIX).getValue();
				if (dist <= 1){
					return MovementAction.REMOVE_OBSTACLE;
				}
				MovementAction a = calculateMovement(c.getDir(), d);
				if (a != null){
					return a;
				}
			}
		}
		int value = r.nextInt(Direction.values().length);
		return calculateMovement(c.getDir(), Direction.values()[value]);
	}

}
