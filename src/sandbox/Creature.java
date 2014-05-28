package sandbox;

import java.io.Serializable;

public class Creature implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private Direction dir;
	
	private boolean hasTouched;
	private double sonar;
	private int sound;
	
	public Creature(int x, int y, Direction dir) {
		this(x, y, dir, false, 0, 0);
	}
	
	public Creature(int x, int y, Direction dir, boolean hasTouched, double sonar, int sound){
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.hasTouched = hasTouched;
		this.sonar = sonar;
		this.sound = sound;
	}
	
	public boolean isHasTouched() {
		return hasTouched;
	}

	public void setHasTouched(boolean hasTouched) {
		this.hasTouched = hasTouched;
	}

	public double getSonar() {
		return sonar;
	}

	public void setSonar(double sonar) {
		this.sonar = sonar;
	}

	public int getSound() {
		return sound;
	}

	public void setSound(int sound) {
		this.sound = sound;
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Direction getDir() {
		return dir;
	}
	public void setDir(Direction dir) {
		this.dir = dir;
	}
	
	@Override
	public String toString(){
		return "X:" + x + ",Y:" + y + ",D:" + dir.toString();
	}
	
	@Override
	public boolean equals(Object o){
		if (!(o instanceof Creature)){
			return false;
		}
		Creature c = (Creature)o;
		if (this.x != c.x || this.y != c.y || !this.dir.equals(c.dir)){
			return false;
		}
		if (!this.hasTouched || !c.hasTouched || this.sonar != c.sonar || this.sound != c.sound){
			return false;
		}
		return true;
	}
	
}
