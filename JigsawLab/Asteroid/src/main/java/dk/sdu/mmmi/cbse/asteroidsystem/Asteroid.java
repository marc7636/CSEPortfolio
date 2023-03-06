package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;

public class Asteroid extends Entity {
	private float size;
	
	public Asteroid(float size){
		setSize(size);
	}
	
	public void setSize(float size) {
		this.size = size;
		setRadius(size * 2);
	}
	
	public float getSize() {
		return size;
	}
	
	public float getScale(){
		return size * 2;
	}
}
