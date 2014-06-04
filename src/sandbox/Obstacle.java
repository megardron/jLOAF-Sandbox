package sandbox;

public enum Obstacle {
	NOTHING(0, false),
	WALL(1, true),
	DIRT(2, false);
	
	private final int id;
	private final boolean clippable;
	
	Obstacle(int id, boolean clipable){
		this.id = id;
		this.clippable = clipable;
	}
	
	public int getId(){
		return this.id;
	}
	
	public boolean isClippable(){
		return this.clippable;
	}
	
	public static Obstacle idToEnum(int id){
		for (Obstacle o : Obstacle.values()){
			if (o.getId() == id){
				return o;
			}
		}
		return null;
	}
}
