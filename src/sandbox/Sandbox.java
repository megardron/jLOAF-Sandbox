package sandbox;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Sandbox {

	private List<Creature> creatureList;
	
	private int world[][];
	
	private Map<Creature, ActionHistory> lastActionHistory;
	
	public Sandbox(int size){
		world = new int[size][size];
		this.creatureList = new ArrayList<Creature>();
		
		for (int i = 0; i < size; i++){
			world[0][i] = Obstacle.WALL.getId();
			world[i][0] = Obstacle.WALL.getId();
			world[size - 1][i] = Obstacle.WALL.getId();
			world[i][size - 1] = Obstacle.WALL.getId();
		}
		this.lastActionHistory = new HashMap<Creature, ActionHistory>();
	}
	
	public int addCreature(Creature creature){
		this.creatureList.add(creature);
		return this.creatureList.size() - 1;
	}
	
	public void setWorld(int world[][]){
		this.world = world;
	}
	
	public void init(){
		for (int i = 0; i < this.creatureList.size(); i++){
			this.creatureList.get(i).getSensor().updateSenses(this);
		}
	}
	
	public int[][] getWorld(){
		return this.world;
	}
	
	public List<Creature> getCreature(){
		return this.creatureList;
	}
	
	public ActionHistory getLastActionHistory(Creature c){
		return this.lastActionHistory.get(c);
	}
	
	public void takeAction(int index, MovementAction action){
		boolean bump = false;
		switch(action){
		case FORWARD:
			bump = moveForward(index);
			break;
		case BACKWARD:
			bump = moveBackward(index);
			break;
		case TURN_LEFT:
			turnLeft(index);
			break;
		case TURN_RIGHT:
			turnRight(index);
			break;
		case REVERSE:
			reverse(index);
			break;
		case MOVE_LEFT:
			turnLeft(index);
			bump = moveForward(index);
			break;
		case MOVE_RIGHT:
			turnRight(index);
			bump = moveForward(index);
			break;
		case REMOVE_OBSTACLE:
			//removeObstacle(index);
			action = MovementAction.STAND;
			break;
		case STAND:
			break;
		}
		removeObstacle(index);
		
		Creature c = this.creatureList.get(index);
		this.lastActionHistory.put(c, new ActionHistory(action, !bump));
		c.getSensor().updateSenses(this);
	}
	
	private void removeObstacle(int index){
		Creature c = this.creatureList.get(index);
		int x = c.getX();
		int y = c.getY();
		
		clearSpace(x, y);
		//clearSpace(Math.max(x - 1, 0), y);
		//clearSpace(Math.min(x + 1, world.length - 1), y);
		//clearSpace(x, Math.max(y - 1, 0));
		//clearSpace(x, Math.min(y + 1, world[0].length - 1));
	}
	
	private void clearSpace(int x, int y){
		Obstacle o = Obstacle.idToEnum(world[x][y]);
		if(!o.isClippable()){
			world[x][y] = Obstacle.NOTHING.getId();
		}
	}
	
	private void reverse(int index){
		Creature c = this.creatureList.get(index);
		c.setDir(Direction.getNextDirection(MovementAction.REVERSE, c.getDir()));
	}
	
	private boolean moveForward(int index){
		Creature c = this.creatureList.get(index);
		return move(index, c.getDir());
	}
	
	private boolean moveBackward(int index){
		Creature c = this.creatureList.get(index);
		return move(index, Direction.getNextDirection(MovementAction.REVERSE, c.getDir()));
	}
	
	private boolean move(int index, Direction dir){
		Creature c = this.creatureList.get(index);
		int oldX = c.getX();
		int oldY = c.getY();
		switch(dir){
		case NORTH:
			oldX--;
			break;
		case SOUTH:
			oldX++;
			break;
		case EAST:
			oldY++;
			break;
		case WEST:
			oldY--;
			break;
		}
		if (oldX < 0 || oldY < 0){
			return true;
		}else if(oldX >= world.length || oldY >= world[0].length){
			return true;
		}
		Obstacle o = Obstacle.idToEnum(world[oldX][oldY]);
		if(o.isClippable()){
			return true;
		}
		c.setX(oldX);
		c.setY(oldY);
		return false;
	}
	
	private void turnLeft(int index){
		Creature c = this.creatureList.get(index);
		c.setDir(Direction.getNextDirection(MovementAction.TURN_LEFT, c.getDir()));
	}
	
	private void turnRight(int index){
		Creature c = this.creatureList.get(index);
		c.setDir(Direction.getNextDirection(MovementAction.TURN_RIGHT, c.getDir()));
	}
}
