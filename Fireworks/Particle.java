import processing.core.PApplet;
import processing.core.PVector;

public class Particle {

	PApplet p;
	PVector pos, vel, acc, gravity;
	
	public Particle(float x, float y, PVector vel, PApplet parent) {
		this.p = parent;
		gravity = new PVector(0, 0.2f);
		pos = new PVector(x, y);
		this.vel = vel;
		acc = new PVector(0, 0);
	}
	
	public void applyForce() {
		acc.add(gravity);
	}
	
	public void update() {
		applyForce();
		vel.add(acc);
		pos.add(vel);
		acc.mult(0);
	}
	
	public void show() {
		p.point(pos.x, pos.y);
	}
	
}
