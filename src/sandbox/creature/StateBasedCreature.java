package sandbox.creature;

import java.util.ArrayList;

import sandbox.Creature;
import sandbox.Direction;
import sandbox.sensor.Sense;
import sandbox.sensor.Sensor;
import sandbox.sensor.StateBasedSensor;

public class StateBasedCreature extends Creature {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StateBasedCreature(int x, int y, Direction dir) {
		super(x, y, dir);
	}
	
	public StateBasedCreature(Creature c){
		super(c);
	}

	@Override
	public Sensor getSensor() {
		if (this.sensor == null){
			ArrayList<Sense> senses = new ArrayList<Sense>();
			senses.add(new Sense("Sound", 0));
			senses.add(new Sense("Sonar", 0));
			senses.add(new Sense("Touch", false));
			sensor = new StateBasedSensor(senses, this);
		}
		return sensor;
	}

}
