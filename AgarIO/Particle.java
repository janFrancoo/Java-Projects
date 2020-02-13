import processing.core.PApplet;
import processing.core.PVector;

public class Particle {
	
	private PApplet p;
	private float radius;
	private PVector pos, vel;
	private float maxSpeed = 2;
	
	public Particle(float x, float y, PApplet parent) {
		this.p = parent;
		this.pos = new PVector(x, y);
		this.vel = new PVector(0, 0);
		this.radius = parent.random(5, 15);
	}
	
	public void update(float x, float y) {
		vel.x = x;
		vel.y = y;
		vel.setMag(maxSpeed);
		pos.add(vel);
	}
	
	public void show() {
		p.fill(255);
		p.ellipse(pos.x, pos.y, radius, radius);
	}
	
	public PVector getPos() {
		return this.pos;
	}
	
	public float getRadius() {
		return this.radius;
	}
	
	public void setNewPos() {
		pos.x = p.random(-p.width * 3, p.width * 3);
		pos.y = p.random(-p.height * 3, p.height * 3);
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}
	
}
