package sandbox;

public class ActionHistory {
	private MovementAction lastAction;
	private boolean ableToTake;
	
	public ActionHistory(MovementAction lastAction, boolean ableToTake) {
		this.lastAction = lastAction;
		this.ableToTake = ableToTake;
	}

	public MovementAction getLastAction() {
		return lastAction;
	}

	public boolean isAbleToTake() {
		return ableToTake;
	}
	
}
