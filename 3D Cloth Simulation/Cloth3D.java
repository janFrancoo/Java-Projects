import java.util.ArrayList;
import processing.core.PApplet;
import toxi.geom.Vec3D;
import toxi.physics.VerletPhysics;
import toxi.physics.behaviors.GravityBehavior;

public class Cloth3D extends PApplet {

	int cols = 40, rows = 40;
	ArrayList<Spring> springs;
	float w = 10;
	VerletPhysics physics;
	Particle[][] particles;
	
	public static void main(String[] args) {
		PApplet.main("Cloth3D");
	}
	
	public void setup() {
		particles = new Particle[cols][rows];
		springs = new ArrayList<>();
		physics = new VerletPhysics();
		Vec3D gravity = new Vec3D(0, 1, 0);
		GravityBehavior gb = new GravityBehavior(gravity);
		physics.addBehavior(gb);
		
		float x = 100, y = 10, w = 10;
		for(int i=0; i<cols; i++) {
			y = 10;
			for(int j=0; j<rows; j++) {
				particles[i][j] = new Particle(x, y, 0, this);
				physics.addParticle(particles[i][j]);
				y += w;
			}
			x += w;
		}
		
		for(int i=0; i<cols-1; i++) {
			for(int j=0; j<rows-1; j++) {
				Particle a = particles[i][j];
				Particle b1 = particles[i+1][j];
				Particle b2 = particles[i][j+1];
				Spring s1 = new Spring(a, b1, w, 0.5f, this);
				springs.add(s1);
				physics.addSpring(s1);
				Spring s2 = new Spring(a, b2, w, 0.5f, this);
				springs.add(s2);
				physics.addSpring(s2);
			}
		}
		
		particles[0][0].lock();
		particles[cols-1][0].lock();
	}
	
	public void settings() {
		size(600, 600, P3D);
	}
	
	public void draw() {
		background(51);
		
		physics = physics.update();
		
		for(int i=0; i<cols; i++) {
			for(int j=0; j<rows; j++) {
				//particles[i][j].display();
			}
		}
		
		for(Spring spring : springs)
			spring.display();
	}
	
}
