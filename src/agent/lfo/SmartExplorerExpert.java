
package agent.lfo;

import java.util.Random;

import sandbox.Creature;
import sandbox.Direction;
import sandbox.MovementAction;

public class SmartExplorerExpert extends DirtBasedAgent{
    private int[][] Visited;
    private Random r;
    private int FirstLook;
    private int Size;
    
    public SmartExplorerExpert (int size, Creature c){
        super(size, c);
        r = new Random(0);
        Size = size;
        Visited = new int[size][size];
        for (int i=0;i<size;i++) {
            for (int j=0;j<size;j++) {
                Visited[i][j] = 0;
            }
        }
        Visited[c.getY()][c.getX()] = 1;
    }
    
    @Override
    public MovementAction testAction(Creature c) {
        Visited[c.getY()-1][c.getX()-1] = 1;
        FirstLook = r.nextInt(4);
        for (int i=1;i<Size;i++) {
            if (FirstLook==0) {
                if (Size>(c.getY()+i)) {
                    if ((Visited[c.getY()+i][c.getX()])==0){
                        if (c.getDir()==Direction.NORTH) {
                            return MovementAction.MOVE_RIGHT;
                        }
                      if (c.getDir()==Direction.EAST) {
                            //return MovementAction.FORWARD;
                        }
                     if (c.getDir()==Direction.SOUTH) {
                            return MovementAction.MOVE_LEFT;
                        }
                       return MovementAction.BACKWARD;
                    }
                }
         }
            if (FirstLook==1) {
             if (0<=(c.getY()-i)) {
                    if ((Visited[c.getY()-i][c.getX()])==0){
                        if (c.getDir()==Direction.NORTH) {
                            return MovementAction.MOVE_LEFT;
                        }
                        if (c.getDir()==Direction.EAST) {
                           return MovementAction.BACKWARD;
                        }
                        if (c.getDir()==Direction.SOUTH) {
                            return MovementAction.MOVE_RIGHT;
                        }
                        //return MovementAction.FORWARD;
                    }
                }
            }
            if (FirstLook==2) {
                if (0<=(c.getX()-i)) {
                    if ((Visited[c.getY()][c.getX()-i])==0){
                        if (c.getDir()==Direction.NORTH) {
                            return MovementAction.BACKWARD;
                        }
                        if (c.getDir()==Direction.EAST) {
                            return MovementAction.MOVE_RIGHT;
                        }
                        if (c.getDir()==Direction.SOUTH) {
                            return MovementAction.FORWARD;
                        }
                        return MovementAction.MOVE_LEFT;
                    }
                }
            }
            if (Size>(c.getX()+i)) {
                    if ((Visited[c.getY()][c.getX()+i])==0){
                        if (c.getDir()==Direction.NORTH) {
                            return MovementAction.FORWARD;
                        }
                        if (c.getDir()==Direction.EAST) {
                            return MovementAction.MOVE_LEFT;
                        }
                        if (c.getDir()==Direction.SOUTH) {
                            return MovementAction.BACKWARD;
                        }
                        return MovementAction.MOVE_RIGHT;
                    }
                }
            }
            return MovementAction.STAND;
    }
}
