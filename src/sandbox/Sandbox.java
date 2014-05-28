package sandbox;
import java.util.ArrayList;
import java.util.List;


public class Sandbox {

	private List<Creature> creatureList;
	
	private int world[][];
	
	public Sandbox(int size){
		world = new int[size][size];
		this.creatureList = new ArrayList<Creature>();
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
			update(i, false);
		}
	}
	
	public int[][] getWorld(){
		return this.world;
	}
	
	public List<Creature> getCreature(){
		return this.creatureList;
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
		}
		
		update(index, bump);
	}
	
	private void update(int index, boolean bump){
		Creature c = this.creatureList.get(index);
		c.setHasTouched(bump);
		int sonar = 0;
		int oldX = c.getX();
		int oldY = c.getY();
		switch(c.getDir()){
		case NORTH:
			for (int i = oldX; i >= 0; i--){
				if (world[i][oldY] != 0){
					break;
				}
				sonar++;
			}
			break;
		case SOUTH:
			for (int i = oldX; i < world.length; i++){
				if (world[i][oldY] != 0){
					break;
				}
				sonar++;
			}
			break;
		case EAST:
			for (int i = oldY; i < world[0].length; i++){
				if (world[oldX][i] != 0){
					break;
				}
				sonar++;
			}
			break;
		case WEST:
			for (int i = oldY; i >= 0; i--){
				if (world[oldX][i] != 0){
					break;
				}
				sonar++;
			}
			break;
		}
		c.setSonar(Math.min(sonar / 2.0, 5));
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
