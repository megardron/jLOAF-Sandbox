package sandbox.creature;

import java.util.ArrayList;

import agent.lfo.DirtBasedAgentSenseConfig;

import sandbox.Creature;
import sandbox.Direction;
import sandbox.sensor.DirtBasedSensor;
import sandbox.sensor.Sense;
import sandbox.sensor.Sensor;

public class DirtBasedCreature extends Creature {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DirtBasedCreature(int x, int y, Direction dir) {
		super(x, y, dir);
	}

	@Override
	public Sensor getSensor() {
		if (this.sensor == null){
			ArrayList<Sense> senses = new ArrayList<Sense>();
			for (Direction d : Direction.values()){
				senses.add(new Sense(d.name() + DirtBasedAgentSenseConfig.DISTANCE_SUFFIX, 0));
				senses.add(new Sense(d.name() + DirtBasedAgentSenseConfig.TYPE_SUFFIX, 0));
			}
			this.sensor = new DirtBasedSensor(senses, this);
		}
		return this.sensor;
	}

}
