package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;

public class Asteroid extends Entity {
	private double size;
	
	public Asteroid(double size){
		this.size = size;
	}
	
	public void setSize(double size) {
		this.size = size;
	}
	
	public double getSize() {
		return size;
	}
	
	public double getScale(){
		return size * 2;
	}
}
