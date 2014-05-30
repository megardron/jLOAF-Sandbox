package agent;

import sandbox.Creature;
import sandbox.Direction;
import sandbox.MovementAction;

class CreatureState {
	private boolean hasTouched;
	private double sonar;
	private int sound;
	private MovementAction action;
	
	public CreatureState(boolean hasTouched, double sonar, int sound, MovementAction action) {
		this.hasTouched = hasTouched;
		this.sonar = sonar;
		this.sound = sound;
		this.action = action;
	}
	
	public Creature buildCreatureFromState(){
		return new Creature(-1, -1, Direction.NORTH, this.hasTouched, this.sonar, this.sound);
	}
	
	public MovementAction getAction(){
		return action;
	}
	
	public boolean isHasTouched() {
		return hasTouched;
	}

	public double getSonar() {
		return sonar;
	}

	public int getSound() {
		return sound;
	}

}
