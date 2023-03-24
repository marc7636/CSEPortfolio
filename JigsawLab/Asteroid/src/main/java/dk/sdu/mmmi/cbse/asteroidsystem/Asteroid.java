package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;

public class Asteroid extends Entity {
	private int size;
	
	public Asteroid(int size){
		setSize(size);
	}
	
	public void setSize(int size) {
		this.size = size;
		setRadius(size * 2);
	}
	
	public int getSize() {
		return size;
	}
	
	public int getScale(){
		return size * 2;
	}
}
