package sandbox;

public enum Direction {
	NORTH,
	EAST,
	SOUTH,
	WEST,
	;
	public static Direction getPreviousDirection(MovementAction action, Direction currentDirection){
		switch(action){
		case BACKWARD:
		case REMOVE_OBSTACLE:
		case STAND:
		case FORWARD:
			return currentDirection;
		case MOVE_LEFT:
		case TURN_LEFT:
			return Direction.values()[(currentDirection.ordinal() + 1) % Direction.values().length];
		case MOVE_RIGHT:
		case TURN_RIGHT:
			int i = ((currentDirection.ordinal() - 1) < 0)? (currentDirection.ordinal() - 1) + Direction.values().length :(currentDirection.ordinal() - 1);
			return Direction.values()[i];
		case REVERSE:
			return Direction.values()[(currentDirection.ordinal() + 2) % Direction.values().length];
		}
		return null;
	}
	
	public static Direction getNextDirection(MovementAction action, Direction currentDirection){
		switch(action){
		case BACKWARD:
		case REMOVE_OBSTACLE:
		case STAND:
		case FORWARD:
		case REVERSE:
			return getPreviousDirection(action, currentDirection);
		case MOVE_LEFT:
		case TURN_LEFT:
			return getPreviousDirection(MovementAction.TURN_RIGHT, currentDirection);
		case MOVE_RIGHT:
		case TURN_RIGHT:
			return getPreviousDirection(MovementAction.TURN_LEFT, currentDirection);
		}
		return null;
	}
}
