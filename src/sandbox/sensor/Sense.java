package sandbox.sensor;

public class Sense {

	private String name;
	private Object value;
	
	public Sense(String name, Object value){
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}
	
	public void setValue(Object value){
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o){
		if (!(o instanceof Sense)){
			return false;
		}
		Sense s = (Sense)o;
		return this.name.equals(s.name) && this.value.equals(s.value);
	}
}
