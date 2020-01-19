import processing.core.PApplet;
import toxi.physics.VerletSpring;

public class Spring extends VerletSpring {
	
	PApplet parent;
	
	Spring(Particle a, Particle b, float w, float str, PApplet p) {
		super(a, b, 10, 0.5f);
		this.parent = p;
	}
	
	void display() {
		parent.stroke(255);
		parent.strokeWeight(2);
		parent.line(a.x, a.y, a.z, b.x, b.y, b.z);
	}

}
