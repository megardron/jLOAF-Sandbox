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
			world[0][i] = 1;
			world[i][0] = 1;
			world[size - 1][i] = 1;
			world[i][size - 1] = 1;
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
		}
		Creature c = this.creatureList.get(index);
		this.lastActionHistory.put(c, new ActionHistory(action, !bump));
		c.getSensor().updateSenses(this);
	}
	
	private void reverse(int index){
		Creature c = this.creatureList.get(index);
		c.setDir(Direction.values()[(c.getDir().ordinal() + 2) % Direction.values().length]);
	}
	
	private boolean moveForward(int index){
		Creature c = this.creatureList.get(index);
		return move(index, c.getDir());
	}
	
	private boolean moveBackward(int index){
		Creature c = this.creatureList.get(index);
		return move(index, Direction.values()[(c.getDir().ordinal() + 2) % Direction.values().length]);
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
		}else if(world[oldX][oldY] != 0){
			return true;
		}
		c.setX(oldX);
		c.setY(oldY);
		return false;
	}
	
	private void turnLeft(int index){
		Creature c = this.creatureList.get(index);
		int i = ((c.getDir().ordinal() - 1) < 0)? (c.getDir().ordinal() - 1) + Direction.values().length :(c.getDir().ordinal() - 1);
		c.setDir(Direction.values()[i]);
	}
	
	private void turnRight(int index){
		Creature c = this.creatureList.get(index);
		c.setDir(Direction.values()[(c.getDir().ordinal() + 1) % Direction.values().length]);
	}
}
