package agent.lfo;

import java.util.Random;

import sandbox.Creature;
import sandbox.Direction;
import sandbox.MovementAction;
import sandbox.Obstacle;
import sandbox.sensor.Sensor;

public class SmartStrightLineExpert extends DirtBasedAgent{
	
	private Random r;
	private boolean hitWall;
	
	public SmartStrightLineExpert(int size, Creature c) {
		super(size, c);
		r = new Random(0);
		hitWall = false;
	}

	@Override
	public MovementAction testAction(Creature c) {
		boolean hitSomething = false;
		Sensor s = c.getSensor();
		for (Direction d : Direction.values()){
			int value = (int) s.getSense(d.name() + DirtBasedAgentSenseConfig.TYPE_SUFFIX).getValue();
			if (Obstacle.DIRT.ordinal() == value){
				MovementAction a = calculateMovement(c.getDir(), d);
				if (a != null){
					return a;
				}
			}
			if ((int)s.getSense(d.name() + DirtBasedAgentSenseConfig.DISTANCE_SUFFIX).getValue() == -1){
				hitSomething = true;
			}
		}
		if (hitSomething){
			hitWall = true;
			return MovementAction.STAND;
		}else if (hitWall){
			hitWall = false;
			int value = r.nextInt(Direction.values().length);
			return calculateMovement(c.getDir(), Direction.values()[value]);
		}
		return MovementAction.FORWARD;
	}

}
