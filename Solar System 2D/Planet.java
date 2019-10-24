import processing.core.PApplet;
import processing.core.PConstants;

public class Planet {
	
	PApplet parent;
	Planet[] moons;
	float radius, angle, distance, speed;
	
	public Planet(PApplet p, float r, float d, float s) {
		parent = p;
		radius = r;
		distance = d;
		speed = s;
		angle = parent.random(PConstants.TWO_PI);
	}
	
	public void spawnMoons(int total, int level) {
		moons = new Planet[total];
		for(int i=0; i<total; i++) {
			float r = (float) (radius / (level * 1.8)), d = parent.random(75, 200), s = parent.random((float) 0.01, (float) 0.03);
			moons[i] = new Planet(parent, r, d/level, s);
			if(level < 2) {
				int num = (int) (parent.random(0, 3));
				moons[i].spawnMoons(num, level+1);
			}
		}
	}
	
	public void show() {
		parent.pushMatrix();
		parent.fill(255, 100);
		parent.rotate(angle);
		parent.translate(distance, 0);
		parent.circle(0, 0, radius);
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
