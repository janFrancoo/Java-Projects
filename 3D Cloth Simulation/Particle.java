import processing.core.PApplet;
import toxi.physics.VerletParticle;

public class Particle extends VerletParticle {

	PApplet parent;
	
	public Particle(float x, float y, float z, PApplet p) {
		super(x, y, z);
		this.parent = p;
	}
	
	public void display() {
		parent.fill(255);
		parent.ellipse(x, y, 10, 10);
	}
	
}
