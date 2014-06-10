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
	}

	@Override
	public MovementAction testAction(Creature c) {
		Sensor s = c.getSensor();
		for (Direction d : Direction.values()){
			int value = (int) s.getSense(d.name() + DirtBasedAgentSenseConfig.TYPE_SUFFIX).getValue();
			if (Obstacle.DIRT.ordinal() == value){
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
