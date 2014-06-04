package sandbox.sensor;

import java.util.List;

import agent.lfo.DirtBasedAgentSenseConfig;
import sandbox.Creature;
import sandbox.Direction;
import sandbox.Sandbox;

public class DirtBasedSensor extends Sensor{

	public DirtBasedSensor(List<Sense> senses, Creature c) {
		super(senses, c);
	}

	@Override
	public void updateSenses(Sandbox sandbox) {
		if (this.senses.size() != DirtBasedAgentSenseConfig.SENSOR_COUNT){
			return;
		}
		int[][] world = sandbox.getWorld();
		
		int oldX = c.getX();
		int oldY = c.getY();
		for (Direction d : Direction.values()){
			int count = 0;
			int obstacle = 0;
			switch(d){
			case NORTH:
				for (int i = oldX; i >= 0; i--){
					if (world[i][oldY] != 0){
						obstacle = world[i][oldY];
						break;
					}
					count++;
				}
				break;
			case SOUTH:
				for (int i = oldX; i < world.length; i++){
					if (world[i][oldY] != 0){
						obstacle = world[i][oldY];
						break;
					}
					count++;
				}
				break;
			case EAST:
				for (int i = oldY; i < world[0].length; i++){
					if (world[oldX][i] != 0){
						obstacle = world[oldX][i];
						break;
					}
					count++;
				}
				break;
			case WEST:
				for (int i = oldY; i >= 0; i--){
					if (world[oldX][i] != 0){
						obstacle = world[oldX][i];
						break;
					}
					count++;
				}
				break;
			}
			
			this.senses.get(d.name() + DirtBasedAgentSenseConfig.TYPE_SUFFIX).setValue(obstacle);
			this.senses.get(d.name() + DirtBasedAgentSenseConfig.DISTANCE_SUFFIX).setValue(count);
		}
	}

}
