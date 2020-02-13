import java.util.ArrayList;
import processing.core.PApplet;

public class Board extends PApplet {

	private final int MAP_SIZE = 1, N_PARTICLE = 100;
	
	private float x = 0, y = 0, r = 100;
	private ArrayList<Particle> particles;
	
	public static void main(String args[]) {
		PApplet.main("Board");
	}
	
	public void settings() {
		size(600, 600);
	}

	public void setup() {
		particles = new ArrayList<>();
		for (int i=1; i<=N_PARTICLE; i++)
			particles.add(new Particle(random(-width * MAP_SIZE, width * MAP_SIZE), random(-height * MAP_SIZE, height * MAP_SIZE), this));
		x = width / 2;
		y = height / 2;
	}
	
	public void draw() {
		background(0);
		ellipse(x, y, r, r);
		for(Particle particle : particles) {
			if (checkIntersection(x, y, r * 0.5f, particle.getPos().x, particle.getPos().y, particle.getRadius())) {
				r += (particle.getRadius() * 0.3);
				ellipse(x, y, r, r);
				particle.setNewPos();
			}
			particle.update(x - mouseX, y - mouseY);
			particle.show();
		}
	}
	
	public boolean checkIntersection(float x1, float y1, float r1, float x2, float y2, float r2) {
		float z = ((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
		if(((r1 - r2) * (r1 - r2)) <= z && z <= ((r1 + r2) * (r1 + r2)))
			return true;
		return false;
	}
	
}
