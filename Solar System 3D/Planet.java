import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class Planet {
	
	PVector v;
	PApplet parent;
	Planet[] moons;
	float radius, angle, distance, speed;
	
	public Planet(PApplet p, float r, float d, float s) {
		parent = p;
		radius = r;
		distance = d;
		speed = s;
		v = PVector.random3D();
		v.mult(distance);
		angle = parent.random(PConstants.TWO_PI);
	}
	
	public void spawnMoons(int total, int level) {
		moons = new Planet[total];
		for(int i=0; i<total; i++) {
			float r = (float) (radius / (level * 2)), d = parent.random((radius + r), (radius + r) * 2), 
					s = parent.random((float) 0.01, (float) 0.03);
			moons[i] = new Planet(parent, r, d/level, s);
			if(level < 2) {
				int num = (int) (parent.random(0, 3));
				moons[i].spawnMoons(num, level+1);
			}
		}
	}
	
	public void show() {
		parent.pushMatrix();
		parent.noStroke();
		parent.fill(255);
		PVector v2 = new PVector(1, 0, 1);
		PVector p = v.cross(v2);
		parent.rotate(angle, p.x, p.y, p.z);
		parent.translate(v.x, v.y, v.z);
		parent.sphere(radius);
		if(moons != null) {
			for(int i=0; i<moons.length; i++) {
				moons[i].show();
			}
		}
		parent.popMatrix();
	}
	
	public void update() {
		angle += speed;
		if(moons != null) {
			for(int i=0; i<moons.length; i++) {
				moons[i].update();
			}
		}
	}
	
}
